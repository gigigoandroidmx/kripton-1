package com.kripton.mvp.view.ui;

import android.support.v4.app.Fragment;
import com.kripton.mvp.presenter.KPresenter;


/**
 * Created by Daniel on 30/06/2016.
 */
public abstract class KBaseFragment extends Fragment{

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
