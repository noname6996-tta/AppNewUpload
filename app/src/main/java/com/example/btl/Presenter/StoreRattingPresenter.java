package com.example.btl.Presenter;

import android.content.Context;

import com.example.btl.Interface.CallBack.SotreRattingCallback;
import com.example.btl.Interface.StoreRattingInterface;
import com.example.btl.Model.RattingModel;

public class StoreRattingPresenter {
    public Context context;
    public StoreRattingInterface storeRattingInterface;
    private SotreRattingCallback sotreRattingCallback;

    public StoreRattingPresenter(Context context, StoreRattingInterface storeRattingInterface, SotreRattingCallback sotreRattingCallback) {
        this.context = context;
        this.storeRattingInterface = storeRattingInterface;
        this.sotreRattingCallback = sotreRattingCallback;
    }

    public void insertRattingtoRatting() {
        RattingModel.readRattingListinStoreRatting(context, sotreRattingCallback);
    }

    public void checkCanComment(Integer idstore, Integer iduser) {
        RattingModel.checkCanComment(idstore, iduser, storeRattingInterface, context);
    }
}
