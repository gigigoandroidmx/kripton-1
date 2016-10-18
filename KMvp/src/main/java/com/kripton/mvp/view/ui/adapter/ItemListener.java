package com.kripton.mvp.view.ui.adapter;

import android.support.annotation.NonNull;

/**
 * Created by jgodinez on 7/11/2016.
 * Define la interfaz de ejecución de un elemento al realizar un clic sobre este.
 * @param <T>
 */
public interface ItemListener<T> {
    void onItemClick(@NonNull T item);
}