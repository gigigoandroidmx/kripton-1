package com.gigigo.example.presentation.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.kripton.mvp.presentation.activity.KActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Juan Godínez Vera - 3/13/2017.
 */
public abstract class KBaseActivity
        extends KActivity {

    protected Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(getLayoutResourceId());
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
