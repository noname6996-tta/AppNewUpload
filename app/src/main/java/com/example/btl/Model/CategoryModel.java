package com.example.btl.Model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.CategoryAdapter;
import com.example.btl.Fragment.HomeFragment;
import com.example.btl.Interface.CallBack.HomeFragmentcallBack;
import com.example.btl.Interface.HomeFragmentInterface;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    public CategoryModel() {
    }

    public static void readCategoryList(List<Category> categoryList, Context context, HomeFragmentInterface homeFragmentInterface, HomeFragmentcallBack homeFragmentcallBack) {
        String url = "http://"+ MainActivity.IPLOCALHOST+"/AndroidBTL/btl/category/selectUser.php";
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
                                Category category = new Category(id_category,type,image_category);
                                homeFragmentcallBack.addToCategory(category);
                            }
                        }
                        catch (Exception exception){
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        homeFragmentInterface.noInterNetConnect();
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }
}
