package com.example.btl.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.adapter.RankAdapter;
import com.example.btl.myinterface.callback.SotreRattingCallback;
import com.example.btl.myinterface.StoreRattingInterface;
import com.example.btl.model.Rank;
import com.example.btl.model.Store;
import com.example.btl.presenter.StoreRankPresenter;
import com.example.btl.R;
import com.example.btl.view.LoginActivity;
import com.example.btl.view.StoreActivity;

import java.util.ArrayList;
import java.util.List;

public class StoreRankFragment extends Fragment implements StoreRattingInterface, SotreRattingCallback {
    private StoreActivity storeActivity;
    private View view;
    private RecyclerView rec_comment;
    private Button btn_ranking;
    private List<Rank> rankList;
    private Store store;
    private RankAdapter rankAdapter;
    public static Integer ID_STORE_FIX = 0;
    private StoreRankPresenter storeRankPresenter;
    public static boolean checkCanComment = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store_ratting, container, false);
        storeActivity = (StoreActivity) getActivity();
        store = storeActivity.getStore();
        ID_STORE_FIX = store.getId_store();
        initUi();
        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void initUi() {
        rec_comment = view.findViewById(R.id.rec_comment);
        btn_ranking = view.findViewById(R.id.btn_ranking);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(rec_comment.getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(new ColorDrawable(R.color.black));
        rec_comment.addItemDecoration(itemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_comment.setLayoutManager(linearLayoutManager);
        storeRankPresenter = new StoreRankPresenter(getContext(), this, this);
        rankList = new ArrayList<>();
        rankAdapter = new RankAdapter(rankList, getContext());
        storeRankPresenter.insertRattingtoRatting();
        rec_comment.setAdapter(rankAdapter);
        btn_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeRankPresenter.checkCanComment(ID_STORE_FIX, LoginActivity.id_user);
            }
        });
    }


    @Override
    public void noConnectInternet() {
        Toast.makeText(getContext(), "Vui lòng bật mạng để sử dụng ứng dụng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail() {
        Toast.makeText(getContext(), "Lỗi hệ thống vui lòng khởi động lại ứng dụng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCannotRattong() {
        Toast.makeText(getContext(), "Bạn đã bình luận rồi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkId_store(Integer id, Rank rank) {
        if (id.equals(ID_STORE_FIX)) {
            rankList.add(rank);
            rankAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void checkId_user(Integer id) {

    }
}
