package com.example.btl.Fragment;

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

import com.example.btl.Adapter.RattingAdapter;
import com.example.btl.Interface.CallBack.SotreRattingCallback;
import com.example.btl.Interface.StoreRattingInterface;
import com.example.btl.Model.Ratting;
import com.example.btl.Model.Store;
import com.example.btl.Presenter.StoreRattingPresenter;
import com.example.btl.R;
import com.example.btl.View.LoginActivity;
import com.example.btl.View.StoreActivity;

import java.util.ArrayList;
import java.util.List;

public class StorerattingFragment extends Fragment implements StoreRattingInterface, SotreRattingCallback {
    private StoreActivity storeActivity;
    private View view;
    private RecyclerView rec_comment;
    private Button btn_ranking;
    private List<Ratting> rattingList;
    private Store store;
    private RattingAdapter rattingAdapter;
    public static Integer ID_STORE_FIX = 0;
    private StoreRattingPresenter storeRattingPresenter;
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
        storeRattingPresenter = new StoreRattingPresenter(getContext(), this, this);
        rattingList = new ArrayList<>();
        rattingAdapter = new RattingAdapter(rattingList, getContext());
        storeRattingPresenter.insertRattingtoRatting();
        rec_comment.setAdapter(rattingAdapter);
        btn_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeRattingPresenter.checkCanComment(ID_STORE_FIX, LoginActivity.id_user);
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
    public void checkId_store(Integer id, Ratting ratting) {
        if (id.equals(ID_STORE_FIX)) {
            rattingList.add(ratting);
            rattingAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void checkId_user(Integer id) {

    }
}
