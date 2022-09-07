package com.example.btl.Model;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Interface.CallBack.AccountFragmentCallBack;
import com.example.btl.Interface.ChangeAccountInterface;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountModel {
    public static void readUserList(Context mContext, AccountFragmentCallBack accountFragmentCallBack) {
        String url = "http://"+ MainActivity.IPLOCALHOST+"/AndroidBTL/btl/user/selectUser.php";
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
                                String username = jsonObject.getString("username");
                                String date = jsonObject.getString("date");
                                String address = jsonObject.getString("address");
                                String email = jsonObject.getString("email");
                                String phone = jsonObject.getString("phone");
                                String password = jsonObject.getString("password");
                                String image = jsonObject.getString("image");
                                //User(Integer id, String username, String date, String address, String email, String phone, String image, String password)
                                User user = new User(id,username,date,address,email,phone,image,password);
                                accountFragmentCallBack.callBackAccoutn(id,user);
                            }
                        }
                        catch (Exception exception){
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Vui lòng bật mạng để sử dụng ứng dụng", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
    public static void updateUser(Context mContext, User user, ChangeAccountInterface changeAccountInterface){
        String url = "http://"+ MainActivity.IPLOCALHOST+"/AndroidBTL/btl/user/update.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                changeAccountInterface.onFail();
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > map = new HashMap<>();
                map.put("id",user.getId().toString());
                map.put("username",user.getUsername());
                map.put("date",user.getDate());
                map.put("address",user.getAddress());
                map.put("email",user.getEmail());
                map.put("phone",user.getPhone());
                map.put("password",user.getPassword());
                map.put("image",user.getImage());
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
}
