package com.example.btl.model;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.myinterface.callback.AddFavoriteView;
import com.example.btl.myinterface.callback.StoreAcitivyCallBack;
import com.example.btl.myinterface.FavoriteFragmentInterface;
import com.example.btl.R;
import com.example.btl.view.LoginActivity;
import com.example.btl.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FarvoriteModel {
    private static Store store_read;

    public static void readFarvoiteList(Context mContext, AddFavoriteView addFavoriteView, FavoriteFragmentInterface favoriteFragmentInterface) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/favorite/selectfa.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id_love = Integer.valueOf(jsonObject.getString("id_love"));
                                Integer id_user = Integer.valueOf(jsonObject.getString("id_user"));
                                Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
                                if (LoginActivity.id_user.equals(id_user)) {
                                    readStoreList(id_store, mContext, addFavoriteView);
                                }
                            }

                        } catch (Exception exception) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        favoriteFragmentInterface.onFail();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public static void readStoreList(Integer iduser, Context mContext, AddFavoriteView addFavoriteView) {
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
                                addToStoreList(jsonObject);
                                if (iduser.equals(Integer.valueOf(jsonObject.getString("id_store")))) {
                                    addFavoriteView.addStoretoList(store_read);
                                }
                            }

                        } catch (Exception exception) {
                            Log.e("FavoriteModel", exception.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FavoriteModel", error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }

    public static void addToStoreList(JSONObject jsonObject) throws JSONException {
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
        store_read = new Store(id_store, name_store, address_store, type, timeopen, phone, lat, lot, linkWeb, image_store, star_store);
    }

    public static void insertFavorite(CheckBox chkLove, Store store, Context mContext, StoreAcitivyCallBack storeAcitivyCallBack) {
        chkLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkLove.isChecked() == true) {
                    int iduser = LoginActivity.id_user;
                    int idstore = store.getId_store();
                    Farvorite farvorite = new Farvorite(0, iduser, idstore);
                    addFavorite(farvorite, mContext);
                } else if (chkLove.isChecked() == false) {
                    final Dialog dialog = new Dialog(mContext);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_dialog_delete_fa);

                    Window window = dialog.getWindow();
                    if (window == null) {
                        return;
                    }

                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    WindowManager.LayoutParams windowAttributes = window.getAttributes();
                    windowAttributes.gravity = Gravity.CENTER;
                    window.setAttributes(windowAttributes);

                    dialog.setCancelable(false);
                    EditText editText = dialog.findViewById(R.id.edt_check_update_pass);
                    Button button = dialog.findViewById(R.id.btn_accept_delete_fa);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int iduser = LoginActivity.id_user;
                            int idstore = store.getId_store();
                            Farvorite farvorite = new Farvorite(0, iduser, idstore);
                            deleteFavorite(farvorite, mContext);
                            readFarvoiteListStoreAcitivy(store, mContext, chkLove, storeAcitivyCallBack);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = dialog.findViewById(R.id.btn_dont_accept_delete);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });
    }

    public static void addFavorite(Farvorite farvorite, Context mContext) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/favorite/insertFa.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("InserRatting", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_user", String.valueOf(farvorite.getId_user()));
                map.put("id_store", String.valueOf(farvorite.getId_store()));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void deleteFavorite(Farvorite farvorite, Context mContext) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/favorite/delete.php";
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
                map.put("id_user", String.valueOf(farvorite.getId_user()));
                map.put("id_store", String.valueOf(farvorite.getId_store()));
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public static void readFarvoiteListStoreAcitivy(Store store, Context mContext, CheckBox chkLove, StoreAcitivyCallBack storeAcitivyCallBack) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/favorite/selectfa.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id_love = Integer.valueOf(jsonObject.getString("id_love"));
                                Integer id_user = Integer.valueOf(jsonObject.getString("id_user"));
                                Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
                                int iduser1 = LoginActivity.id_user;
                                int idstore2 = store.getId_store();
                                if (id_user.equals(iduser1) && id_store.equals(idstore2)) {
                                    storeAcitivyCallBack.setCheckBox(id_love);
                                }
                            }
                        } catch (Exception exception) {
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

    public void readPhotoList(List<Photo> storeList, Store store, Context mContext) {
        String url = "http://100.85.96.56/AndroidBTL/btl/photo/selectphoto.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id_photo = Integer.valueOf(jsonObject.getString("id_photo"));
                                String resoureId = jsonObject.getString("resoureId");
                                Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
                                Photo photo = new Photo(id_photo, resoureId, id_store);
                                if (photo.getResoureId().equals(store.getId_store())) {
                                    storeList.add(photo);
                                }

                            }

                        } catch (Exception exception) {

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

    public static void readFavoritesListToAddMain(Context mContext) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/favorite/selectfa.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
                                Integer id_love = Integer.valueOf(jsonObject.getString("id_love"));
                                Integer id_user = Integer.valueOf(jsonObject.getString("id_user"));
                                Integer id_store = Integer.valueOf(jsonObject.getString("id_store"));
                                if (LoginActivity.id_user.equals(id_user)) {
                                    readStoreList_toAddMain(id_store, mContext);
                                }
                            }
                        } catch (Exception exception) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("readFavouriteListToAddMain", error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public static void readStoreList_toAddMain(Integer iduser, Context mContext) {
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/store/selectStore.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;

                        try {
                            List<Store> storeList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                jsonObject = response.getJSONObject(i);
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
                                Store store = new Store(id_store, name_store, address_store, type, timeopen, phone, lat, lot, linkWeb, image_store, star_store);
                            }
                        } catch (Exception exception) {
                            Log.e("FavoriteModel", exception.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FavoriteModel", error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}
