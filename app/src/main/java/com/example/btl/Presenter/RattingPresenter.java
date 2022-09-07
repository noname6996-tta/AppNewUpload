package com.example.btl.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Interface.RattingInterface;
import com.example.btl.Model.Ratting;
import com.example.btl.Model.RattingModel;
import com.example.btl.Model.StoreModel;
import com.example.btl.View.LoginActivity;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RattingPresenter {
    private Context context;
    private RattingInterface rattingInterface;

    public RattingPresenter(Context context, RattingInterface rattingInterface) {
        this.context = context;
        this.rattingInterface = rattingInterface;
    }
    public void addToRattingList(Ratting ratting){
        RattingModel.addRatting(ratting,context,rattingInterface);
    }
    public void check(Bundle bundle){
        if (bundle == null){
            rattingInterface.onNullItem();
            return;
        }
    }
    public void updateStarStore(Integer idstore){
        StoreModel.readRattingForUpdateStore(context,idstore);
    }

}
