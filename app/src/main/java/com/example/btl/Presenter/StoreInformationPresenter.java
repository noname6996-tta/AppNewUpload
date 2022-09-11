package com.example.btl.Presenter;

import android.content.Context;

import com.example.btl.Interface.CallBack.StoreInfoCallBack;
import com.example.btl.Model.MenuModel;

public class StoreInformationPresenter {
    private Context context;
    private StoreInfoCallBack store_info_callBack;

    public StoreInformationPresenter(Context context, StoreInfoCallBack store_info_callBack) {
        this.context = context;
        this.store_info_callBack = store_info_callBack;
    }
    public void addToMenuList(Integer id){
        MenuModel.readMenuList(id,context,store_info_callBack);
    }
}
