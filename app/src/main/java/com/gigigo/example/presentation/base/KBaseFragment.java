package com.gigigo.example.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kripton.mvp.presentation.fragment.KFragment;
import com.kripton.mvp.presentation.presenter.IKPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Define la clase para ...
 *
 * @author Juan Godínez Vera - 1/3/2017.
 */
public abstract class KBaseFragment
        extends KFragment {

    protected Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(getLayoutResourceId(), container, false);
        mUnbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
