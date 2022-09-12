package com.example.btl.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.myinterface.callback.AddDataHome;
import com.example.btl.myinterface.CheckConnectHome;
import com.example.btl.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CategoryModel {
    public static void readCategoryList(List<Category> categoryList, Context context, CheckConnectHome checkConnectHome, AddDataHome addDataHome) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/category/selectUser.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id_category = Integer.valueOf(jsonObject.getString("id_category"));
                                String type = jsonObject.getString("type");
                                String image_category = jsonObject.getString("image_category");
                                Category category = new Category(id_category, type, image_category);
                                addDataHome.addToCategory(category);
                            }
                        } catch (Exception exception) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        checkConnectHome.noInterNetConnect();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}
