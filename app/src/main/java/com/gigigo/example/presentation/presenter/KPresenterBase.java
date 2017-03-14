package com.gigigo.example.presentation.presenter;

import android.support.annotation.NonNull;

import com.gigigo.example.presentation.view.IKViewBaseDEP;
import com.kripton.mvp.domain.EntryState;
import com.kripton.mvp.domain.callback.OnViewCallback;
import com.kripton.mvp.domain.interactor.IKInteractor;
import com.kripton.mvp.presentation.presenter.KPresenter;

/**
 * @author Juan Godínez Vera - 2/22/2017.
 */
public class KPresenterBase<T>
        extends KPresenter<T, IKViewBaseDEP<T>> {

    private static final String DATA_NOT_AVAILABLE = "Data not available";

    public KPresenterBase(@NonNull IKInteractor<T> interactor) {
        super(interactor);
    }

    @Override
    public void loadData(boolean forceUpdate) {
        if(!isViewAttached()) return;

        getView().showProgressIndicator(true);

        if(forceUpdate) {
            mInteractor.refreshData();
        }

        mInteractor.setParams(getParams());
        mInteractor.setOperationType(getOperationType());
        mInteractor.execute(new OnViewCallback<T>() {
            @Override
            public void onSuccess(T data, Object... params) {
                if (!isViewAttached()) return;

                getView().showProgressIndicator(false);
                getView().showData(data);
            }

            @Override
            public void onError(Throwable exception) {
                if (!isViewAttached()) return;

                getView().showProgressIndicator(false);
                getView().showError(exception);
            }

            @Override
            public void onDataEmpty() {
                if (!isViewAttached()) return;

                getView().showProgressIndicator(false);
                getView().showDataEmpty();
            }

            @Override
            public void onDataNotAvailable(EntryState entryState) {
                if (!isViewAttached()) return;

                String dataNotAvailable = entryState != null ?
                        !entryState.getEntryMessage().isEmpty() ? entryState.getEntryMessage() : DATA_NOT_AVAILABLE :
                        DATA_NOT_AVAILABLE;

                getView().showProgressIndicator(false);
                getView().showDataNotAvailable(dataNotAvailable);
            }
        });
    }
}
