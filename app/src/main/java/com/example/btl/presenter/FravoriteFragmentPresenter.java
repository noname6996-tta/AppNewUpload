package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.AddFavoriteView;
import com.example.btl.myinterface.CheckAddFavorite;
import com.example.btl.model.FarvoriteModel;

public class FravoriteFragmentPresenter {
    private Context mContext;
    private AddFavoriteView addFavoriteView;
    private CheckAddFavorite checkAddFavorite;

    public FravoriteFragmentPresenter(Context mContext, AddFavoriteView addFavoriteView, CheckAddFavorite checkAddFavorite) {
        this.mContext = mContext;
        this.addFavoriteView = addFavoriteView;
        this.checkAddFavorite = checkAddFavorite;
    }

    public void addtofaList() {
        FarvoriteModel.readFarvoiteList(mContext, addFavoriteView, checkAddFavorite);
    }

}
