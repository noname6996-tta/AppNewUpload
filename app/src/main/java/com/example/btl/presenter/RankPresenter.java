package com.example.btl.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.btl.myinterface.CheckAddRank;
import com.example.btl.model.Rank;
import com.example.btl.model.RankModel;
import com.example.btl.model.StoreModel;

public class RankPresenter {
    private Context context;
    private CheckAddRank checkAddRank;

    public RankPresenter(Context context, CheckAddRank checkAddRank) {
        this.context = context;
        this.checkAddRank = checkAddRank;
    }

    public void addToRattingList(Rank rank) {
        RankModel.addRatting(rank, context, checkAddRank);
    }

    public void check(Bundle bundle) {
        if (bundle == null) {
            checkAddRank.onNullItem();
            return;
        }
    }

    public void updateStarStore(Integer idstore) {
        StoreModel.readRattingForUpdateStore(context, idstore);
    }

}
