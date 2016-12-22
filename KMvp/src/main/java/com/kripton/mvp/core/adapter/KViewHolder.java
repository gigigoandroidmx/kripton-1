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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kripton.mvp.core.view.IKViewContract;

/**
 * Clase base para el viewholder del recycler
 *
 * @param <T> Modelo de datos que utilizará el viewholder
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class KViewHolder<T>
        extends RecyclerView.ViewHolder {

    protected Context mContext;

    private T mItem;
    private IKViewContract.IActionCommand<T> mActionCommand;

    public KViewHolder(View itemView) {
        this(itemView, null);
    }

    public KViewHolder(View itemView, IKViewContract.IActionCommand<T> actionCommand) {
        super(itemView);
        this.mContext = itemView.getContext();

        if(actionCommand != null) {
            mActionCommand = actionCommand;
            itemView.setOnClickListener(itemViewClickListener);
        }
    }

    View.OnClickListener itemViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mActionCommand.onExecute(getItem());
        }
    };

    public void onBindViewHolder(@NonNull T item) {
        this.mItem = item;
    }

    public T getItem() {
        return this.mItem;
    }

    public Context getContext() {
        return this.mContext;
    }
}
