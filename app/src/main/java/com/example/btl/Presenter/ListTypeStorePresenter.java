package com.example.btl.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Interface.ListTypeStoreInterface;
import com.example.btl.Model.Category;
import com.example.btl.Model.Store;
import com.example.btl.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ListTypeStorePresenter {
    private Context context;
    private ListTypeStoreInterface listTypeStoreInterface;
    private Store store;

    public ListTypeStorePresenter(Context context, ListTypeStoreInterface listTypeStoreInterface) {
        this.context = context;
        this.listTypeStoreInterface = listTypeStoreInterface;
    }

    public void readStoreList(Category category, List<Store> mListStore, StoreAdapter storeAdapter) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/store/selectStore.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                addDataListStore(jsonObject);
                                if (category.getType().equalsIgnoreCase(jsonObject.getString("type"))) {
                                    mListStore.add(store);
                                    storeAdapter.notifyDataSetChanged();
                                } else {
                                    storeAdapter.notifyDataSetChanged();
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
                        listTypeStoreInterface.failRespon();
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }

    public void addDataListStore(JSONObject jsonObject) throws JSONException {
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

    public void CheckItem(Category category) {
        if (category == null) {
            listTypeStoreInterface.onNullItems();
            return;
        }
    }
}
