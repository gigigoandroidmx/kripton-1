package kripton.extensions.retrofit;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jgodinez on 9/02/2016.
 */
public class Connectivity {

    public static String ACCESS_NETWORK_ERROR = "No hay conexión de red, intenta nuevamente";

    public static int TYPE_NOT_CONNECTED = 0;
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;

    private Context context;

    public Connectivity() {
        Application applicationContext = null;
        try {
            applicationContext = getApplicationUsingRelfection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if(applicationContext != null) {

            this.context = applicationContext;
        }
    }

    public Application getApplicationUsingRelfection()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Application applicationContext = (Application) getClass().forName("android.app.ActivityThread")
                .getMethod("currentApplication").invoke(null, (Object[])null);

        return applicationContext;
    }

    public ConnectivityManager getConnectivityManager() {
        if(this.context == null) return  null;
        return (ConnectivityManager) this.context.getSystemService(this.context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = getConnectivityManager();

        if(connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }

        return false;
    }

    public int getConnectivityStatus() {
        ConnectivityManager connectivityManager = getConnectivityManager();
        if(connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if(networkInfo != null) {
                if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                    return TYPE_WIFI;

                if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                    return TYPE_MOBILE;
            }
        }

        return TYPE_NOT_CONNECTED;
    }

    public boolean isReachable(String host, int msTimeout) {
        if(!isConnected())
            return false;

        boolean reachable;

        try {
            reachable = InetAddress.getByName(host).isReachable(msTimeout);
        } catch (UnknownHostException hostException) {
            hostException.printStackTrace();
            reachable = false;
        } catch (IOException exception) {
            exception.printStackTrace();
            reachable = false;
        }

        return reachable;
    }
}
