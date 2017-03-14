package com.gigigo.example.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.gigigo.example.R;
import com.gigigo.example.presentation.fragment.ChannelFragment;

public class MainActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = ChannelFragment.newInstance();

        if(fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }



}
