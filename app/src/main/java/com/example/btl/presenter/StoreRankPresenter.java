package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.SotreRattingCallback;
import com.example.btl.myinterface.StoreRattingInterface;
import com.example.btl.model.RankModel;

public class StoreRankPresenter {
    public Context context;
    public StoreRattingInterface storeRattingInterface;
    private SotreRattingCallback sotreRattingCallback;

    public StoreRankPresenter(Context context, StoreRattingInterface storeRattingInterface, SotreRattingCallback sotreRattingCallback) {
        this.context = context;
        this.storeRattingInterface = storeRattingInterface;
        this.sotreRattingCallback = sotreRattingCallback;
    }

    public void insertRattingtoRatting() {
        RankModel.readRattingListinStoreRatting(context, sotreRattingCallback);
    }

    public void checkCanComment(Integer idstore, Integer iduser) {
        RankModel.checkCanComment(idstore, iduser, storeRattingInterface, context);
    }
}
