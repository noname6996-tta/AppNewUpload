package com.example.btl.model;

import static android.content.ContentValues.TAG;
import static com.example.btl.presenter.LoginPeresenter.URL_LOGIN;
import static com.example.btl.view.LoginActivity.KEY_PASSWORD;
import static com.example.btl.view.LoginActivity.KEY_USERNAME;

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
import com.example.btl.myinterface.ForGotPass;
import com.example.btl.myinterface.CheckLogin;
import com.example.btl.myinterface.CheckRegister;
import com.example.btl.view.LoginActivity;
import com.example.btl.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserModel {
    public static void readUserList(String a, Context context) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/user/selectUser.php";
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
                                User user = new User(id, username, date, address, email, phone, image, password);
                                if (email.equals(a)) {
                                    LoginActivity.id_user = user.getId();
                                    LoginActivity.userName = user.getUsername();
                                    LoginActivity.passUser = user.getPassword();
                                }
                            }
                        } catch (Exception exception) {
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

    public static void checkLogin(String email1, String password1, Context context, CheckLogin login) {
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
                                String email = jsonObject.getString("email");
                                readUserList(email, context);
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

    public static void regesterAccount(final String email1, final String password1, CheckRegister checkRegister, Context mContext) {
        if (checkRegister.checkEditText(email1) && checkRegister.checkEditText(password1)) {
            StringRequest requestLogin = new StringRequest(Request.Method.POST, "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/user/checkuser.php",
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
                                    String email = jsonObject.getString("email");
                                    checkRegister.onfail(email);

                                } else {
                                    message = jsonObject.getString("message");
                                    Log.d("CheckRes", message.toString());
                                    addUser(email1, password1, mContext);
                                    checkRegister.onSuccess();
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

    private static void addUser(String email, String pass, Context mContext) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/user/insertuser.php";
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
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("password", pass);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void updateUser(String emailChange, String passChange, Context context, ForGotPass forGotPassInterface) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/user/updatePassWord.php";
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
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", emailChange);
                map.put("password", passChange);
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
}
