package com.example.btl.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.CategoryAdapter;
import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Fragment.HomeFragment;
import com.example.btl.Interface.CallBack.HomeFragmentcallBack;
import com.example.btl.Interface.HomeFragmentInterface;
import com.example.btl.Model.Category;
import com.example.btl.Model.CategoryModel;
import com.example.btl.Model.Photo;
import com.example.btl.Model.Store;
import com.example.btl.Model.StoreModel;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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
