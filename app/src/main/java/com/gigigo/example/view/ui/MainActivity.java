package com.gigigo.example.view.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.gigigo.example.R;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment playListFragment = new PlayListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, playListFragment)
                .addToBackStack(playListFragment.toString())
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }



}
