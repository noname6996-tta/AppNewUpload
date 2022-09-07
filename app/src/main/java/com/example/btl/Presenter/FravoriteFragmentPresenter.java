package com.example.btl.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Interface.CallBack.FavoriteFragmentCallBack;
import com.example.btl.Interface.FavoriteFragmentInterface;
import com.example.btl.Model.FarvoriteModel;
import com.example.btl.Model.Store;
import com.example.btl.View.LoginActivity;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class FravoriteFragmentPresenter {
    private Context mContext;
    private FavoriteFragmentCallBack favoriteFragmentCallBack;
    private FavoriteFragmentInterface favoriteFragmentInterface;

    public FravoriteFragmentPresenter(Context mContext, FavoriteFragmentCallBack favoriteFragmentCallBack, FavoriteFragmentInterface favoriteFragmentInterface) {
        this.mContext = mContext;
        this.favoriteFragmentCallBack = favoriteFragmentCallBack;
        this.favoriteFragmentInterface = favoriteFragmentInterface;
    }

    public void addtofaList(){
        FarvoriteModel.readFarvoiteList(mContext,favoriteFragmentCallBack,favoriteFragmentInterface);
    }

}
