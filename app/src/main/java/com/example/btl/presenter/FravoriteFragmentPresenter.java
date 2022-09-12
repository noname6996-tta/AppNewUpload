package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.AddFavoriteView;
import com.example.btl.myinterface.FavoriteFragmentInterface;
import com.example.btl.model.FarvoriteModel;

public class FravoriteFragmentPresenter {
    private Context mContext;
    private AddFavoriteView addFavoriteView;
    private FavoriteFragmentInterface favoriteFragmentInterface;

    public FravoriteFragmentPresenter(Context mContext, AddFavoriteView addFavoriteView, FavoriteFragmentInterface favoriteFragmentInterface) {
        this.mContext = mContext;
        this.addFavoriteView = addFavoriteView;
        this.favoriteFragmentInterface = favoriteFragmentInterface;
    }

    public void addtofaList() {
        FarvoriteModel.readFarvoiteList(mContext, addFavoriteView, favoriteFragmentInterface);
    }

}
