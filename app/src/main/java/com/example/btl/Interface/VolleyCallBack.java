package com.example.btl.Interface;

import com.android.volley.VolleyError;
import com.example.btl.Model.User;

import org.json.JSONArray;

public interface VolleyCallBack {
    void CheckUser(User user,String a,String b);
}
