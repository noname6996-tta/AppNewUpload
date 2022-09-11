package com.example.btl.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Interface.CallBack.SearchRragmentCallback;
import com.example.btl.Interface.SearchFragmentInterface;
import com.example.btl.Model.Store;
import com.example.btl.Model.StoreModel;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class SearchFragmentPresenter {
    private Context mContext;
    private SearchFragmentInterface searchFragmentInterface;
    private SearchRragmentCallback searchRragmentCallback;

    public SearchFragmentPresenter(Context mContext, SearchFragmentInterface searchFragmentInterface, SearchRragmentCallback searchRragmentCallback) {
        this.mContext = mContext;
        this.searchFragmentInterface = searchFragmentInterface;
        this.searchRragmentCallback = searchRragmentCallback;
    }

    public void addToListSearch() {
        StoreModel.addSearchStoreList(mContext, searchFragmentInterface, searchRragmentCallback);
    }
}
