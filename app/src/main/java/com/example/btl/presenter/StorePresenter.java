package com.example.btl.presenter;

import android.content.Context;
import android.widget.CheckBox;

import com.example.btl.myinterface.callback.StoreAcitivyCallBack;
import com.example.btl.myinterface.StoreAcitivyInterface;
import com.example.btl.model.FarvoriteModel;
import com.example.btl.model.Rank;
import com.example.btl.model.RankModel;
import com.example.btl.model.Store;
import com.example.btl.model.StoreModel;

public class StorePresenter {
    private Context context;
    private StoreAcitivyInterface storeAcitivyInterface;
    private StoreAcitivyCallBack storeAcitivyCallBack;


    public StorePresenter(Context context, StoreAcitivyInterface storeAcitivyInterface, StoreAcitivyCallBack storeAcitivyCallBack) {
        this.context = context;
        this.storeAcitivyInterface = storeAcitivyInterface;
        this.storeAcitivyCallBack = storeAcitivyCallBack;
    }

    public void updateStarStore_store(Integer idstore) {
        StoreModel.readRattingForUpdateStore(context, idstore);
    }

    public void readForfavorite(Store store, CheckBox checkBox) {
        FarvoriteModel.readFarvoiteListStoreAcitivy(store, context, checkBox, storeAcitivyCallBack);
    }

    public void insertForfavorite(Store store, CheckBox checkBox) {
        FarvoriteModel.insertFavorite(checkBox, store, context, storeAcitivyCallBack);
    }

    public void getStartCount(int id) {
        RankModel.readRattingList(context, id, this);
    }

    public void checkStore(int idstore, Rank rank) {
        int count = 0;
        Float starcount = null;
        if (idstore == rank.getId_store()) {
            starcount = starcount + Float.valueOf(rank.getRatting());
            count = count + 1;
            if (count != 0) {
                Float finalstar = starcount / count;
                storeAcitivyInterface.addString(finalstar.toString());
                storeAcitivyInterface.addSuccess();
            } else {
                storeAcitivyInterface.addString("0");
            }
            storeAcitivyInterface.deleteSuccess();
        } else {

        }
    }
}
