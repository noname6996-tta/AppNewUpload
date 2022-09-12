package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.SearchRragmentCallback;
import com.example.btl.myinterface.SearchFragmentInterface;
import com.example.btl.model.StoreModel;

public class SearchFragmentPresenter {
    private Context mContext;
    private SearchFragmentInterface searchFragmentInterface;
    private SearchRragmentCallback searchRragmentCallback;

    public SearchFragmentPresenter(Context mContext, SearchFragmentInterface searchFragmentInterface, SearchRragmentCallback searchRragmentCallback) {
        this.mContext = mContext;
        this.searchFragmentInterface = searchFragmentInterface;
        this.searchRragmentCallback = searchRragmentCallback;
    }

    public void addToListSearch() {
        StoreModel.addSearchStoreList(mContext, searchFragmentInterface, searchRragmentCallback);
    }
}
