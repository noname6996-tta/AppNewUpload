package com.example.btl.Presenter;

import android.content.Context;

import com.example.btl.Interface.StoreLocationInterface;
import com.example.btl.Model.Store;

public class StoreLocationPresenter {
    private Context context;
    private StoreLocationInterface storeLocationInterface;


    public StoreLocationPresenter(Context context, StoreLocationInterface storeLocationInterface) {
        this.context = context;
        this.storeLocationInterface = storeLocationInterface;
    }

    public void Check(Store store){
        if (store == null){
            storeLocationInterface.onNullItems();
        }
    }
}
