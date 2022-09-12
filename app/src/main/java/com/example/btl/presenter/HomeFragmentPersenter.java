package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.AddDataHome;
import com.example.btl.myinterface.CheckConnectHome;
import com.example.btl.model.Category;
import com.example.btl.model.CategoryModel;
import com.example.btl.model.Store;
import com.example.btl.model.StoreModel;

import java.util.List;

public class HomeFragmentPersenter {
    private Context context;
    private CheckConnectHome homeFragment;
    private AddDataHome addDataHome;

    public HomeFragmentPersenter(Context context, CheckConnectHome homeFragment, AddDataHome addDataHome) {
        this.context = context;
        this.homeFragment = homeFragment;
        this.addDataHome = addDataHome;
    }

    public void addCategory(List<Category> categoryList) {
        CategoryModel.readCategoryList(categoryList, context, homeFragment, addDataHome);
    }

    public void addStore(List<Store> storeList) {
        StoreModel.readStoreList(storeList, context, homeFragment, addDataHome);
    }
}
