package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.CheckLocationStore;
import com.example.btl.model.Store;

public class StoreLocationPresenter {
    private Context context;
    private CheckLocationStore checkLocationStore;


    public StoreLocationPresenter(Context context, CheckLocationStore checkLocationStore) {
        this.context = context;
        this.checkLocationStore = checkLocationStore;
    }

    public void Check(Store store) {
        if (store == null) {
            checkLocationStore.onNullItems();
        }
    }
}
