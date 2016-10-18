package com.kripton.mvp.interactor;

import android.support.annotation.NonNull;

/**
 * Created by Daniel on 12/10/2016.
 */
public  abstract class KInteractor  implements IInteractor {

    protected int mOperationtype;
    protected Object[] mParams;

    @Override
    public void setOperationType(int operationType) {
        this.mOperationtype = operationType;
    }

    @Override
    public void setParams(Object... params) {
        this.mParams = params;
    }

    @Override
    public void getData(@NonNull ILoaderCallback callback) {

    }

    @Override
    public void refreshData() {

    }
}
