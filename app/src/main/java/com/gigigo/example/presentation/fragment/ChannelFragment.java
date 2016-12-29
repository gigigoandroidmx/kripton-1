package com.gigigo.example.presentation.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.kripton.mvp.presentation.IKCommandContract;
import com.kripton.mvp.presentation.fragment.KFragment;
import com.kripton.mvp.presentation.presenter.KPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment
        extends KFragment<ChannelItem, KPresenter<ChannelItem>> {

    private ChannelAdapter mAdapter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_channel, container, false);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2, //number of grid columns
                GridLayoutManager.VERTICAL);

        RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(mAdapter);

        return root;
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

    }

    @Override
    public void showDataNotAvailable(String message) {
        showErrorView(message);
    }

    @Override
    public void showData(ChannelItem data) {
        if(data.hasItems()) {
            mAdapter.add(data.mItems);
        }
    }

    @Override
    public void showError(Throwable exception) {
        showErrorView(exception);
    }

    @Override
    public void setProgressIndicator(boolean active) {
        showProgressBar(active);
    }
}
