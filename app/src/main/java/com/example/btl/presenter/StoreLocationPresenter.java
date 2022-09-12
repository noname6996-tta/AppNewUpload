package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.StoreLocationInterface;
import com.example.btl.model.Store;

public class StoreLocationPresenter {
    private Context context;
    private StoreLocationInterface storeLocationInterface;


    public StoreLocationPresenter(Context context, StoreLocationInterface storeLocationInterface) {
        this.context = context;
        this.storeLocationInterface = storeLocationInterface;
    }

    public void Check(Store store) {
        if (store == null) {
            storeLocationInterface.onNullItems();
        }
    }
}
