package com.example.btl.Presenter;

import android.content.Context;

import com.example.btl.Model.FarvoriteModel;

public class MainAcitivyPresenter {
    private Context context;

    public MainAcitivyPresenter(Context context) {
        this.context = context;
    }

    public void getNotiFa(){
        FarvoriteModel.readFarvoiteList_toAddMain(context);
    }
}
