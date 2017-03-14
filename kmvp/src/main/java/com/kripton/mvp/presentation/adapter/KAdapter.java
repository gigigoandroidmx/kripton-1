/* Copyright (c) 2016 Gigigo Android Development Team México
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kripton.mvp.presentation.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kripton.mvp.data.IKRepository;

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
public abstract class KAdapter<T>
        extends RecyclerView.Adapter<KViewHolder<T>>
        implements IKRepository<T> {

    private List<T> mItemsSource = new ArrayList<>();

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
    public void clear() {
        if(isEmpty()) return;
        mItemsSource.clear();
    }

    @Override
    public List<T> query() {
        return mItemsSource;
    }

    //endregion
}
