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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kripton.mvp.presentation.command.IKActionCommand;

/**
 * Clase base para el viewholder del recycler
 *
 * @param <T> Modelo de datos que utilizará el viewholder
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.2
 * @since 1.0.0
 */
public abstract class KViewHolder<T>
        extends RecyclerView.ViewHolder {

    private T mItem;
    private Context mContext;

    public KViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    public void setOnExecuteCommand(final IKActionCommand<T> actionCommand) {
        if(actionCommand != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionCommand.onExecute(getItem());
                }
            });
        }
    }

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
