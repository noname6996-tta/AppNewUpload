package com.example.btl.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Interface.CallBack.StoreAcitivyCallBack;
import com.example.btl.Interface.CallBack.StoreCallback;
import com.example.btl.Interface.StoreAcitivyInterface;
import com.example.btl.Model.Farvorite;
import com.example.btl.Model.FarvoriteModel;
import com.example.btl.Model.Ratting;
import com.example.btl.Model.RattingModel;
import com.example.btl.Model.Store;
import com.example.btl.Model.StoreModel;
import com.example.btl.View.LoginActivity;
import com.example.btl.View.MainActivity;
import com.example.btl.View.StoreActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class StorePresenter {
    private Context context;
    private StoreAcitivyInterface storeAcitivyInterface;
    private StoreAcitivyCallBack storeAcitivyCallBack;


    public StorePresenter(Context context, StoreAcitivyInterface storeAcitivyInterface, StoreAcitivyCallBack storeAcitivyCallBack) {
        this.context = context;
        this.storeAcitivyInterface = storeAcitivyInterface;
        this.storeAcitivyCallBack = storeAcitivyCallBack;
    }
    public void updateStarStore_store(Integer idstore){
        StoreModel.readRattingForUpdateStore(context,idstore);
    }
    public void readForfavorite(Store store, CheckBox checkBox){
        FarvoriteModel.readFarvoiteListStoreAcitivy(store,context,checkBox,storeAcitivyCallBack);
    }
    public void insertForfavorite(Store store,CheckBox checkBox){
        FarvoriteModel.insertFavorite(checkBox,store,context,storeAcitivyCallBack);
    }
    public void getStartCount(int id){
        RattingModel.readRattingList(context, id,this);
    }
    public void checkStore(int idstore, Ratting ratting){
        int count  = 0;
        Float starcount = null;
        if (idstore == ratting.getId_store()){
            starcount = starcount + Float.valueOf(ratting.getRatting());
            count = count+1;
            if (count !=0){
                Float finalstar = starcount / count;
                storeAcitivyInterface.addString(finalstar.toString());
                storeAcitivyInterface.addSuccess();
            }
            else {
                storeAcitivyInterface.addString("0");
            }
            storeAcitivyInterface.deleteSuccess();
        }
        else {

        }
    }
}
