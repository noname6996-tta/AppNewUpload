package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.model.FarvoriteModel;

public class MainAcitivyPresenter {
    private Context context;

    public MainAcitivyPresenter(Context context) {
        this.context = context;
    }

    public void getNotifacationFavorite() {
        FarvoriteModel.readFavoritesListToAddMain(context);
    }
}
