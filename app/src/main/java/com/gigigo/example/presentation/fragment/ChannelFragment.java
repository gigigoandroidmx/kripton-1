package com.gigigo.example.presentation.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigigo.example.R;
import com.gigigo.example.data.entities.ChannelItem;
import com.gigigo.example.data.entities.Item;
import com.gigigo.example.domain.interactor.YoutubeInteractor;
import com.gigigo.example.presentation.adapter.ChannelAdapter;
import com.gigigo.example.presentation.base.KBaseFragment;
import com.kripton.mvp.presentation.IKCommandContract;
import com.kripton.mvp.presentation.fragment.KFragment;
import com.kripton.mvp.presentation.presenter.KPresenter;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment
        extends KBaseFragment<ChannelItem, KPresenter<ChannelItem>> {

    @BindView(R.id.swipe_refresh_view)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ChannelAdapter mAdapter;
    private boolean mIsRefreshing;

    public static ChannelFragment newInstance() {
        return new ChannelFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ChannelAdapter(actionCommand);
    }

    private IKCommandContract.IActionCommand<Item> actionCommand = new IKCommandContract.IActionCommand<Item>() {
        @Override
        public void onExecute(@NonNull Item item) {

        }
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if(!mIsRefreshing) {
                mIsRefreshing = true;
                mPpresenter.loadData(true);
            }
        }
    };

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
    }

    @Override
    protected KPresenter<ChannelItem> createPresenter() {
        YoutubeInteractor interactor = new YoutubeInteractor();
        return new KPresenter<>(interactor);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPpresenter.loadData(false);
    }

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
        if(data.hasItems()) {
            mAdapter.clear();
            mAdapter.add(data.mItems);
        }
        onSwipeRefreshComplete();
    }

    private void onSwipeRefreshComplete() {
        if(mIsRefreshing) {
            mIsRefreshing = false;

            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void showError(Throwable exception) {
        showErrorView(exception);
        onSwipeRefreshComplete();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        showProgressBar(active);
        onSwipeRefreshComplete();
    }
}
