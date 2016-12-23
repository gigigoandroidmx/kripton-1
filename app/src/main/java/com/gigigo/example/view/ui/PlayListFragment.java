package com.gigigo.example.view.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gigigo.example.BuildConfig;
import com.gigigo.example.R;
import com.gigigo.example.adapter.PlayListRecyclerAdapter;
import com.gigigo.example.interactor.PlayListInteractor;
import com.gigigo.example.model.Item;
import com.gigigo.example.model.PlayListItem;
import com.kripton.mvp.presenter.KPresenter;
import com.kripton.mvp.view.ui.adapter.ItemListener;

/**
 * Created by Daniel on 12/10/2016.
 */
public class PlayListFragment  extends KBaseFragment implements IView<PlayListItem>, ItemListener<Item> {

    private ProgressDialog mProgresDialog;
    private RecyclerView mRecyclerPlayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayoutId(), container, false);
        mPresenter.attachView(this);

        mProgresDialog = new ProgressDialog(getActivity());
        mProgresDialog.setMessage(getString(R.string.title_dialog_load));

        mRecyclerPlayList = (RecyclerView) view.findViewById(R.id.recycler_playlist);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerPlayList.setLayoutManager(mLinearLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadData(true);
    }



    @Override
    public void createPresenter() {
        PlayListInteractor  mInteractor= new PlayListInteractor();
        mPresenter = new KPresenter(mInteractor);
        mPresenter.setParams(new Object[]{BuildConfig.ID_USER,BuildConfig.KEY_GOOGLE});
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detachView();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if (active) mProgresDialog.show(); else mProgresDialog.dismiss();
    }

    @Override
    public void showData(PlayListItem data) {
        PlayListRecyclerAdapter mPlayListRecyclerAdapter = new PlayListRecyclerAdapter(getActivity(), data.items, this);
        mRecyclerPlayList.setAdapter(mPlayListRecyclerAdapter);
    }

    @Override
    public void showError(Throwable exception) {
        Toast.makeText(getContext(),exception.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFriendlyMessage(String response) {

    }

    @Override
    public void onItemClick(@NonNull Item item) {
        Toast.makeText(getActivity(), item.snippet.title, Toast.LENGTH_SHORT).show();
    }
}
