package com.example.btl.presenter;

import android.content.Context;
import android.widget.CheckBox;

import com.example.btl.myinterface.callback.CheckFavorite;
import com.example.btl.myinterface.CheckAddStoreItems;
import com.example.btl.model.FarvoriteModel;
import com.example.btl.model.Rank;
import com.example.btl.model.RankModel;
import com.example.btl.model.Store;
import com.example.btl.model.StoreModel;

public class StorePresenter {
    private Context context;
    private CheckAddStoreItems checkAddStoreItems;
    private CheckFavorite checkFavorite;


    public StorePresenter(Context context, CheckAddStoreItems checkAddStoreItems, CheckFavorite checkFavorite) {
        this.context = context;
        this.checkAddStoreItems = checkAddStoreItems;
        this.checkFavorite = checkFavorite;
    }

    public void updateStarStore_store(Integer idstore) {
        StoreModel.readRattingForUpdateStore(context, idstore);
    }

    public void readForfavorite(Store store, CheckBox checkBox) {
        FarvoriteModel.readFarvoiteListStoreAcitivy(store, context, checkBox, checkFavorite);
    }

    public void insertForfavorite(Store store, CheckBox checkBox) {
        FarvoriteModel.insertFavorite(checkBox, store, context, checkFavorite);
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
                checkAddStoreItems.addString(finalstar.toString());
                checkAddStoreItems.addSuccess();
            } else {
                checkAddStoreItems.addString("0");
            }
            checkAddStoreItems.deleteSuccess();
        } else {

        }
    }
}
