package com.example.btl.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Interface.CallBack.FavoriteFragmentCallBack;
import com.example.btl.Interface.FavoriteFragmentInterface;
import com.example.btl.Model.Store;
import com.example.btl.Presenter.FravoriteFragmentPresenter;
import com.example.btl.R;
import com.example.btl.View.LoginActivity;
import com.example.btl.View.MainActivity;
import com.example.btl.View.StoreActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements FavoriteFragmentCallBack, FavoriteFragmentInterface {
    private FravoriteFragmentPresenter fravoriteFragmentPresenter;
    private RecyclerView rec_favorite;
    private List<Store> mListStore;
    private StoreAdapter storeAdapter;
    private Integer iduser;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        iduser = LoginActivity.id_user;
        initUI();
        return view;
    }

    private void initUI() {
        rec_favorite = view.findViewById(R.id.rec_favorite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_favorite.setLayoutManager(linearLayoutManager);
        mListStore = new ArrayList<>();
        storeAdapter = new StoreAdapter(mListStore, this.getContext());
        fravoriteFragmentPresenter = new FravoriteFragmentPresenter(getContext(), this, this);
        fravoriteFragmentPresenter.addtofaList();
        rec_favorite.setAdapter(storeAdapter);
    }

    @Override
    public void addStoretoList(Store store) {
        mListStore.add(store);
        storeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail() {
        Toast.makeText(getContext(), "Vui lòng mở mạng khi chạy ứng dụng", Toast.LENGTH_SHORT).show();
    }
}
