package com.example.btl.model;

import android.content.Context;
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
import com.example.btl.myinterface.callback.HomeFragmentcallBack;
import com.example.btl.myinterface.callback.SearchRragmentCallback;
import com.example.btl.myinterface.HomeFragmentInterface;
import com.example.btl.myinterface.SearchFragmentInterface;
import com.example.btl.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreModel {
    public static Store store;
    public static Store store_search;

    public static void readStoreList(List<Store> storeList, Context context, HomeFragmentInterface homeFragmentInterface, HomeFragmentcallBack homeFragmentcallBack) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/store/selectStore.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            for (int i = 0; i < 9; i++) {
                                jsonObject = response.getJSONObject(i);
                                addDataToReadStore(jsonObject);
                                homeFragmentcallBack.addToStore(store);
                            }

                        } catch (Exception exception) {
                            Log.e("EXXXXX", exception.toString());
                            homeFragmentInterface.onFail(exception.toString());
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

    public static void addDataToReadStore(JSONObject jsonObject) throws JSONException {
        Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
        String name_store = jsonObject.getString("name_store");
        String address_store = jsonObject.getString("address_store");
        String type = jsonObject.getString("type");
        String timeopen = jsonObject.getString("timeopen");
        String phone = jsonObject.getString("phone");
        String lat = jsonObject.getString("lat");
        String lot = jsonObject.getString("lot");
        String linkWeb = jsonObject.getString("linkWeb");
        String image_store = jsonObject.getString("image_store");
        Float star_store = Float.valueOf(jsonObject.getString("star_store"));
        store = new Store(id_store, name_store, address_store, type, timeopen, phone, lat, lot, linkWeb, image_store, star_store);

    }

    public static void addSearchStoreList(Context mContext, SearchFragmentInterface searchFragmentInterface, SearchRragmentCallback searchRragmentCallback) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/store/selectStore.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                addDatatoSearch(jsonObject);
                                searchRragmentCallback.callBack(store_search);
                            }

                        } catch (Exception exception) {
                            Log.e("EXXXXX", exception.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        searchFragmentInterface.onFail();
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }

    public static void addDatatoSearch(JSONObject jsonObject) throws JSONException {
        Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
        String name_store = jsonObject.getString("name_store");
        String address_store = jsonObject.getString("address_store");
        String type = jsonObject.getString("type");
        String timeopen = jsonObject.getString("timeopen");
        String phone = jsonObject.getString("phone");
        String lat = jsonObject.getString("lat");
        String lot = jsonObject.getString("lot");
        String linkWeb = jsonObject.getString("linkWeb");
        String image_store = jsonObject.getString("image_store");
        Float star_store = Float.valueOf(jsonObject.getString("star_store"));
        store_search = new Store(id_store, name_store, address_store, type, timeopen, phone, lat, lot, linkWeb, image_store, star_store);

    }

    public static void updateRatting(Context context, Integer idstore, Float star) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/store/update_star.php";
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
                map.put("id_store", String.valueOf(idstore));
                map.put("star_store", String.valueOf(star));
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public static void readRattingForUpdateStore(Context mContext, Integer idstore) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/ratting/selectratting.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            Float sumStar = Float.valueOf(0);
                            Integer starCount = 0;
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id_rating = Integer.valueOf(jsonObject.getString("id_rating"));
                                Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
                                Integer id_user = Integer.valueOf(jsonObject.getString("id_user"));
                                Float ratting = Float.valueOf(jsonObject.getString("ratting"));
                                String comment = jsonObject.getString("comment");
                                Rank rank1 = new Rank(id_rating, id_store, id_user, ratting, comment);
                                if (id_store.equals(idstore)) {
                                    sumStar = sumStar + ratting;
                                    starCount++;
                                }

                            }
                            Float finalStar = Float.valueOf(sumStar / starCount);
                            updateRatting(mContext, idstore, finalStar);
                        } catch (Exception exception) {
                            Log.e("Fail", exception.toString());
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
}
