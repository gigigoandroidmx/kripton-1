package com.gigigo.example.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.gigigo.example.R;
import com.gigigo.example.presentation.base.KBaseActivity;
import com.gigigo.example.presentation.fragment.ChannelFragment;

public class MainActivity
        extends KBaseActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }

    }
*/
    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initializeComponent() {

        Fragment fragment = ChannelFragment.newInstance();

        if(fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();
        }
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void dispose() {

    }
}
