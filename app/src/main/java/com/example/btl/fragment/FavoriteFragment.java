package com.example.btl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.adapter.StoreAdapter;
import com.example.btl.myinterface.callback.AddFavoriteView;
import com.example.btl.myinterface.CheckAddFavorite;
import com.example.btl.model.Store;
import com.example.btl.presenter.FravoriteFragmentPresenter;
import com.example.btl.R;
import com.example.btl.view.LoginActivity;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements AddFavoriteView, CheckAddFavorite {
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
