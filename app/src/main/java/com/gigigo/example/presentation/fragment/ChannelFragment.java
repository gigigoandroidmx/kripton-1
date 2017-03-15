package com.gigigo.example.presentation.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.gigigo.example.R;
import com.gigigo.example.data.entities.ChannelItem;
import com.gigigo.example.data.entities.Item;
import com.gigigo.example.domain.interactor.YoutubeInteractor;
import com.gigigo.example.presentation.adapter.ChannelAdapter;
import com.gigigo.example.presentation.base.KBaseFragment;
import com.gigigo.example.presentation.presenter.KPresenterBase;
import com.gigigo.example.presentation.view.IKViewBaseDEP;
import com.kripton.mvp.domain.interactor.IKInteractor;
import com.kripton.mvp.presentation.command.IKActionCommand;
import com.kripton.mvp.presentation.presenter.IKPresenter;
import com.kripton.mvp.presentation.presenter.KPresenter;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment
        extends KBaseFragment implements IKViewBaseDEP<ChannelItem> {

    @BindView(R.id.swipe_refresh_view)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ChannelAdapter mAdapter;
    private boolean mIsRefreshing;

    private KPresenter mChannelPresenter;

    public static ChannelFragment newInstance() {
        return new ChannelFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ChannelAdapter(actionCommand);
    }

    private IKActionCommand<Item> actionCommand = new IKActionCommand<Item>() {
        @Override
        public void onExecute(@NonNull Item item) {

        }
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if(!mIsRefreshing) {
                mIsRefreshing = true;
                mChannelPresenter.loadData(true);
            }
        }
    };

    private void onSwipeRefreshComplete() {
        if(mIsRefreshing) {
            mIsRefreshing = false;

            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mChannelPresenter.addParam("hello");
        mChannelPresenter.addParam(15);
        mChannelPresenter.loadData(false);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_channel;
    }

    @Override
    protected void initializeComponent() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2, //number of grid columns
                GridLayoutManager.VERTICAL);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(refreshListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void initializePresenter() {
        IKInteractor<ChannelItem> channelInteractor = new YoutubeInteractor();
        mChannelPresenter = new KPresenterBase<ChannelItem>(channelInteractor);
        mChannelPresenter.attachView(this);
    }

    @Override
    protected void dispose() {
        if(mChannelPresenter != null) {
            mChannelPresenter.detachView();
        }
    }

    //region IKViewBaseDEP members

    @Override
    public void showDataEmpty() {
        onSwipeRefreshComplete();
    }

    @Override
    public void showDataNotAvailable(String message) {
        showErrorView(message);
        onSwipeRefreshComplete();
    }

    @Override
    public void showData(ChannelItem data) {
        hideErrorView();
        if(data.hasItems()) {
            mAdapter.clear();
            mAdapter.add(data.mItems);
        }
        onSwipeRefreshComplete();
    }

    @Override
    public void showError(Throwable exception) {
        showErrorView(exception);
        onSwipeRefreshComplete();
    }

    @Override
    public void showProgressIndicator(boolean active) {
        showProgressView(active);
        onSwipeRefreshComplete();
    }

    //endregion

}
