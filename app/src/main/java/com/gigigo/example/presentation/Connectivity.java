package com.gigigo.example.presentation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gigigo.example.retrofit.IConnectivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Define la clase para ...
 *
 * @author Juan Godínez Vera - 1/3/2017.
 */
public class Connectivity
        extends BroadcastReceiver
        implements IConnectivity {

    public static final int TYPE_NONE = -1;

    private Context mContext;

    public Connectivity(Context context) {
        mContext = context;
    }

    private NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo();
    }

    @Override
    public boolean isConnected() {
        NetworkInfo activeNetwork = getNetworkInfo(mContext);
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public int connectivityType() {
        NetworkInfo activeNetwork = getNetworkInfo(mContext);
        return activeNetwork != null ?
                activeNetwork.getType() :
                TYPE_NONE;
    }

    @Override
    public boolean isReachable(String host, int timeout) {
        if(!isConnected()) return false;

        boolean reachable;

        try {
            reachable = InetAddress.getByName(host).isReachable(timeout);
        } catch (UnknownHostException hostException) {
            hostException.printStackTrace();
            reachable = false;
        } catch (IOException exception) {
            exception.printStackTrace();
            reachable = false;
        }

        return reachable;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;

    }
}
