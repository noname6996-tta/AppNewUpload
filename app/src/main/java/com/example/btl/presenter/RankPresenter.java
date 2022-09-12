package com.example.btl.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.btl.myinterface.RattingInterface;
import com.example.btl.model.Rank;
import com.example.btl.model.RankModel;
import com.example.btl.model.StoreModel;

public class RankPresenter {
    private Context context;
    private RattingInterface rattingInterface;

    public RankPresenter(Context context, RattingInterface rattingInterface) {
        this.context = context;
        this.rattingInterface = rattingInterface;
    }

    public void addToRattingList(Rank rank) {
        RankModel.addRatting(rank, context, rattingInterface);
    }

    public void check(Bundle bundle) {
        if (bundle == null) {
            rattingInterface.onNullItem();
            return;
        }
    }

    public void updateStarStore(Integer idstore) {
        StoreModel.readRattingForUpdateStore(context, idstore);
    }

}
