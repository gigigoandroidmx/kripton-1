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

package com.kripton.mvp.domain;

/**
 * Define la clase para manipulación de estatus de un response
 *
 * @author Juan Godínez Vera - 12/29/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public class EntryState
        extends Throwable {

    private int mStatusCode;

    public EntryState(String message) {
        super(message);
    }

    public EntryState(String message, int statusCode) {
        this(message);
        this.setStatusCode(statusCode);
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        mStatusCode = statusCode;
    }

    public String getEntryMessage() {
        return String.format("%1$d %2$s", getStatusCode(), getMessage());
    }
}
