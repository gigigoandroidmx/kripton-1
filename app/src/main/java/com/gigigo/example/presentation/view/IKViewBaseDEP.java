package com.gigigo.example.presentation.view;

import com.kripton.mvp.presentation.view.dep.IKViewDEP;

/**
 * @author Juan Godínez Vera - 2/22/2017.
 */
public interface IKViewBaseDEP<T>
        extends IKViewDEP<T> {

    void showDataEmpty();
    void showDataNotAvailable(String message);
}
