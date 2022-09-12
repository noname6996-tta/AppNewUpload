package com.example.btl.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.myinterface.callback.StoreInfoCallBack;
import com.example.btl.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class MenuModel {
    public static void readMenuList(Integer id1, Context mContext, StoreInfoCallBack store_info_callBack) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/menu/selectmenu.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id = Integer.valueOf(jsonObject.getString("id"));
                                Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
                                String imageDish = jsonObject.getString("imageDish");
                                String nameDish = jsonObject.getString("nameDish");
                                String priceDish = jsonObject.getString("priceDish");
                                Menu menu = new Menu(id, id_store, imageDish, nameDish, priceDish);
                                if (id1.equals(id_store)) {
                                    store_info_callBack.setMenuList(menu);
                                }
                            }
                        } catch (Exception exception) {
                            Log.e("EXXXXX", exception.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}
