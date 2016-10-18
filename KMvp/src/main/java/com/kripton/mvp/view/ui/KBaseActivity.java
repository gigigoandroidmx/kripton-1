package com.kripton.mvp.view.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.kripton.mvp.presenter.KPresenter;


/**
 * Created by Daniel on 30/06/2016.
 */
public abstract class KBaseActivity extends AppCompatActivity {

    protected   KPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
    }


    public abstract void createPresenter();


    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }

    }


}
