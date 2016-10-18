package com.kripton.mvp.view.ui;

import android.support.v4.app.Fragment;

import com.kripton.mvp.presenter.KPresenter;
import com.kripton.mvp.view.IView;


/**
 * Created by Daniel on 30/06/2016.
 */
public abstract class KFragment extends Fragment implements IView{

   protected   KPresenter mPresenter;

   public abstract void createPresenter();

   public abstract int getLayoutId();

   @Override
   public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }

   }
}
