package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.HomeFragmentcallBack;
import com.example.btl.myinterface.HomeFragmentInterface;
import com.example.btl.model.Category;
import com.example.btl.model.CategoryModel;
import com.example.btl.model.Store;
import com.example.btl.model.StoreModel;

import java.util.List;

public class HomeFragmentPersenter {
    private Context context;
    private HomeFragmentInterface homeFragment;
    private HomeFragmentcallBack homeFragmentcallBack;

    public HomeFragmentPersenter(Context context, HomeFragmentInterface homeFragment, HomeFragmentcallBack homeFragmentcallBack) {
        this.context = context;
        this.homeFragment = homeFragment;
        this.homeFragmentcallBack = homeFragmentcallBack;
    }

    public void addCategory(List<Category> categoryList) {
        CategoryModel.readCategoryList(categoryList, context, homeFragment, homeFragmentcallBack);
    }

    public void addStore(List<Store> storeList) {
        StoreModel.readStoreList(storeList, context, homeFragment, homeFragmentcallBack);
    }
}
