package com.gigigo.example.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
