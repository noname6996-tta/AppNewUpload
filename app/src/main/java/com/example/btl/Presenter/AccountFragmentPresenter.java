package com.example.btl.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Interface.CallBack.AccountFragmentCallBack;
import com.example.btl.Model.AccountModel;
import com.example.btl.Model.User;
import com.example.btl.View.LoginActivity;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class AccountFragmentPresenter {
    private Context mContext;
    private AccountFragmentCallBack accountFragmentCallBack;

    public AccountFragmentPresenter(Context mContext, AccountFragmentCallBack accountFragmentCallBack) {
        this.mContext = mContext;
        this.accountFragmentCallBack = accountFragmentCallBack;
    }
    public void addInfoAccount(){
        AccountModel.readUserList(mContext,accountFragmentCallBack);
    }

}
