package com.example.btl.Model;

import static android.content.ContentValues.TAG;
import static com.example.btl.Presenter.LoginPeresenter.URL_LOGIN;
import static com.example.btl.View.LoginActivity.KEY_PASSWORD;
import static com.example.btl.View.LoginActivity.KEY_USERNAME;
import static com.example.btl.View.LoginActivity.id_user;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Interface.ForGotPassInterface;
import com.example.btl.Interface.LoginInterface;
import com.example.btl.Interface.RegesterInterface;
import com.example.btl.View.LoginActivity;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserModel {
    public static void readUserList(String a, Context context) {
        String url = "http://"+ MainActivity.IPLOCALHOST+"/AndroidBTL/btl/user/selectUser.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                                User user = new User(id,username,date,address,email,phone,image,password);
                                if (email.equals(a)){
                                    LoginActivity.id_user = user.getId();
                                    LoginActivity.userName = user.getUsername();
                                    LoginActivity.passUser = user.getPassword();
                                }
                            }
                        }
                        catch (Exception exception){
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Vui lòng bật mạng để sử dụng ứng dụng", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
    public static void checkLogin(String email1, String password1, Context context, LoginInterface login){
        StringRequest requestLogin = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String message = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("success") == 1) {
                                User user = new User();
                                user.setEmail(jsonObject.getString("email"));
                                message = jsonObject.getString("message");
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                String email= jsonObject.getString("email");
                                readUserList(email,context);
                                login.loginSuccess();
                            } else {
                                message = jsonObject.getString("message");
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                                login.loginFail();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }) {
            /**
             * set paramater
             * */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_USERNAME, email1);
                params.put(KEY_PASSWORD, password1);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(requestLogin);
    }
    public static void regesterAccount(final String email1, final String password1, RegesterInterface regesterInterface, Context mContext) {
        if (regesterInterface.checkEditText(email1) && regesterInterface.checkEditText(password1)) {
            StringRequest requestLogin = new StringRequest(Request.Method.POST, "http://"+MainActivity.IPLOCALHOST+"/AndroidBTL/btl/user/checkuser.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {
                                    User user = new User();
                                    user.setEmail(jsonObject.getString("email"));
                                    String email= jsonObject.getString("email");
                                    regesterInterface.onfail(email);

                                } else {
                                    message = jsonObject.getString("message");
                                    Log.d("CheckRes",message.toString());
                                    addUser(email1,password1,mContext);
                                    regesterInterface.onSuccess();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email1);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(mContext);
            queue.add(requestLogin);
        }
    }
    private static void addUser(String email,String pass,Context mContext){
        String url = "http://"+MainActivity.IPLOCALHOST+"/AndroidBTL/btl/user/insertuser.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > map = new HashMap<>();
                map.put("email",email);
                map.put("password",pass);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    public static void updateUser(String emailChange, String passChange, Context context, ForGotPassInterface forGotPassInterface){
        String url = "http://"+MainActivity.IPLOCALHOST+"/AndroidBTL/btl/user/updatePassWord.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        forGotPassInterface.onSuccessChangePass();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                forGotPassInterface.haveBug(error.toString());
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > map = new HashMap<>();
                map.put("email",emailChange);
                map.put("password",passChange);
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
}
