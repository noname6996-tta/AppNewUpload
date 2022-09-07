package com.example.btl.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.RattingAdapter;
import com.example.btl.Interface.CallBack.SotreRattingCallback;
import com.example.btl.Interface.StoreRattingInterface;
import com.example.btl.Model.Ratting;
import com.example.btl.Model.RattingModel;
import com.example.btl.View.MainActivity;
import com.example.btl.View.RattingActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class StoreRattingPresenter {
    public Context context;
    public StoreRattingInterface storeRattingInterface;
    private SotreRattingCallback sotreRattingCallback;

    public StoreRattingPresenter(Context context, StoreRattingInterface storeRattingInterface, SotreRattingCallback sotreRattingCallback) {
        this.context = context;
        this.storeRattingInterface = storeRattingInterface;
        this.sotreRattingCallback = sotreRattingCallback;
    }

    public void insertRattingtoRatting(){
        RattingModel.readRattingListinStoreRatting(context,sotreRattingCallback);
    }
    public void checkCanComment(Integer idstore,Integer iduser){
        RattingModel.checkCanComment(idstore,iduser,storeRattingInterface,context);
    }
}
