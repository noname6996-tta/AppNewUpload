package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.AddMenuList;
import com.example.btl.model.MenuModel;

public class StoreInformationPresenter {
    private Context context;
    private AddMenuList store_info_callBack;

    public StoreInformationPresenter(Context context, AddMenuList store_info_callBack) {
        this.context = context;
        this.store_info_callBack = store_info_callBack;
    }
    public void addToMenuList(Integer id){
        MenuModel.readMenuList(id,context,store_info_callBack);
    }
}
