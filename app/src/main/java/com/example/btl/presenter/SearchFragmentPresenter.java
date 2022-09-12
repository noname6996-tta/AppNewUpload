package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.Searching;
import com.example.btl.myinterface.CheckSearchItems;
import com.example.btl.model.StoreModel;

public class SearchFragmentPresenter {
    private Context mContext;
    private CheckSearchItems checkSearchItems;
    private Searching searchRragmentCallback;

    public SearchFragmentPresenter(Context mContext, CheckSearchItems checkSearchItems, Searching searchRragmentCallback) {
        this.mContext = mContext;
        this.checkSearchItems = checkSearchItems;
        this.searchRragmentCallback = searchRragmentCallback;
    }

    public void addToListSearch() {
        StoreModel.addSearchStoreList(mContext, checkSearchItems, searchRragmentCallback);
    }
}
