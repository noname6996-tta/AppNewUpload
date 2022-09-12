package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.Ratting;
import com.example.btl.myinterface.CheckAddRankStore;
import com.example.btl.model.RankModel;

public class StoreRankPresenter {
    public Context context;
    public CheckAddRankStore checkAddRankStore;
    private Ratting ratting;

    public StoreRankPresenter(Context context, CheckAddRankStore checkAddRankStore, Ratting ratting) {
        this.context = context;
        this.checkAddRankStore = checkAddRankStore;
        this.ratting = ratting;
    }

    public void insertRattingtoRatting() {
        RankModel.readRattingListinStoreRatting(context, ratting);
    }

    public void checkCanComment(Integer idstore, Integer iduser) {
        RankModel.checkCanComment(idstore, iduser, checkAddRankStore, context);
    }
}
