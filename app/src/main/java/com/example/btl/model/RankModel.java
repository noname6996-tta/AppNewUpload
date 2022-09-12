package com.example.btl.model;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.btl.myinterface.callback.SotreRattingCallback;
import com.example.btl.myinterface.callback.UpdateRatingInterface;
import com.example.btl.myinterface.RattingInterface;
import com.example.btl.myinterface.StoreRattingInterface;
import com.example.btl.presenter.StorePresenter;
import com.example.btl.view.MainActivity;
import com.example.btl.view.SetRankActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RankModel {
    public static void readRattingList(Context context, int id, StorePresenter storePresenter) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/ratting/selectratting.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id_rating = Integer.valueOf(jsonObject.getString("id_rating"));
                                Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
                                Integer id_user = Integer.valueOf(jsonObject.getString("id_user"));
                                Float ratting = Float.valueOf(jsonObject.getString("ratting"));
                                String comment = jsonObject.getString("comment");
                                Rank rank1 = new Rank(id_rating, id_store, id_user, ratting, comment);
                                storePresenter.checkStore(id, rank1);
                            }

                        } catch (Exception exception) {
                            Log.d("Fail in rattingModel", exception.toString());
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

    public static void readRattingListinStoreRatting(Context mContext, SotreRattingCallback sotreRattingCallback) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/ratting/selectratting.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id_rating = Integer.valueOf(jsonObject.getString("id_rating"));
                                Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
                                Integer id_user = Integer.valueOf(jsonObject.getString("id_user"));
                                Float ratting = Float.valueOf(jsonObject.getString("ratting"));
                                String comment = jsonObject.getString("comment");
                                Rank rank1 = new Rank(id_rating, id_store, id_user, ratting, comment);
                                sotreRattingCallback.checkId_store(id_store, rank1);

                            }
                        } catch (Exception exception) {
                            Log.d("Fail", exception.toString());
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

    public static void addRatting(Rank rank, Context mContext, RattingInterface rattingInterface) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/ratting/insertratting.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        rattingInterface.onSuccess();
                        rattingInterface.callBack();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                rattingInterface.failRespon(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_store", String.valueOf(rank.getId_store()));
                map.put("id_user", String.valueOf(rank.getId_user()));
                map.put("ratting", String.valueOf(rank.getRatting()));
                map.put("comment", rank.getComment());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void checkCanComment(Integer id_store, Integer id_user, StoreRattingInterface storeRattingInterface, Context mContext) {
        StringRequest requestLogin = new StringRequest(Request.Method.POST, "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/ratting/checkRatting.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        String message = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("success") == 1) {
                                Rank rank = new Rank();
                                rank.setId_user(Integer.valueOf(jsonObject.getString("id_user")));
                                rank.setId_store(Integer.valueOf(jsonObject.getString("id_store")));
                                String id_user = jsonObject.getString("id_user");
                                String id_store = jsonObject.getString("id_store");
                                storeRattingInterface.onCannotRattong();

                            } else {
                                message = jsonObject.getString("message");
                                Log.d("checkRatting", message.toString());
                                Intent intent = new Intent(mContext, SetRankActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("a", id_store);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);
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
                params.put("id_store", String.valueOf(id_store));
                params.put("id_user", String.valueOf(id_user));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(requestLogin);
    }

    public static void deleteRatting(Context context, Integer idRatting) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/ratting/delete.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                map.put("id_rating", String.valueOf(idRatting));
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public static void updateRatting(Context context, Integer idratting, Float ratting, String comment, UpdateRatingInterface updateRatingInterface) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/ratting/update.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        updateRatingInterface.onSuccess();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                updateRatingInterface.onFail(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_rating", String.valueOf(idratting));
                map.put("ratting", String.valueOf(ratting));
                map.put("comment", comment);
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
}
