/* MIT License
 *
 * Copyright (c) 2016 gigigo México
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.kripton.mvp.core.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase base para el adapter del recycler
 *
 * @param <T> Modelo de datos que utilizará el adapter
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class KAdapterViewHolder<T>
        extends RecyclerView.Adapter<KViewHolder<T>>
        implements IKRepository<T> {

    private List<T> mItemsSource;

    private KAdapterViewHolder() {
        mItemsSource = new ArrayList<>();
    }

    public View getView(ViewGroup parent, @LayoutRes int resourceId) {
        return LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
    }

    @Override
    public void onBindViewHolder(KViewHolder<T> holder, int position) {
        if(isEmpty()) return;
        holder.onBindViewHolder(mItemsSource.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemsSource != null ? mItemsSource.size() : 0;
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    //region IRepository members

    @Override
    public void add(T item) {
        add(Collections.singletonList(item));
    }

    @Override
    public void add(Iterable<T> items) {
        if(items == null) return;

        for (T item : items) {
            mItemsSource.add(item);
        }

        notifyDataSetChanged();
    }

    @Override
    public void update(T item) {
        if(isEmpty()) return;
        int position = mItemsSource.indexOf(item);
        if(position == -1) return;
        mItemsSource.set(position, item);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T item) {
        if(isEmpty()) return;
        int position = mItemsSource.indexOf(item);
        if(position == -1) return;
        mItemsSource.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public List<T> query() {
        return mItemsSource;
    }

    //endregion
}
