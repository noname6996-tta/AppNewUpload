package com.example.btl.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.adapter.MenuAdapter;
import com.example.btl.myinterface.callback.AddMenuList;
import com.example.btl.model.Menu;
import com.example.btl.model.Store;
import com.example.btl.presenter.StoreInformationPresenter;
import com.example.btl.R;
import com.example.btl.view.StoreActivity;

import java.util.ArrayList;
import java.util.List;

public class StoreInformationFragment extends Fragment implements AddMenuList {
    private View view;
    private TextView tv_store_timeopen, tv_store_phone, tv_store_web, tv_store_address;
    private RecyclerView rec_Menu;
    private List<Menu> mListMenu;
    private MenuAdapter menuAdapter;
    private StoreActivity storeActivity;
    private Store store;
    private ImageView btn_image_call, btn_gotoWeb;
    private StoreInformationPresenter storeInformationPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store_infomation, container, false);
        initUi();
        return view;
    }

    private void initUi(){
        storeActivity = (StoreActivity) getActivity();
        store = storeActivity.getStore();
        tv_store_timeopen = view.findViewById(R.id.tv_store_timeopen);
        tv_store_phone = view.findViewById(R.id.tv_store_phone);
        tv_store_web = view.findViewById(R.id.tv_store_web);
        btn_image_call = view.findViewById(R.id.btn_image_call);
        btn_gotoWeb = view.findViewById(R.id.btn_gotoWeb);
        tv_store_timeopen.setText(store.gettimeopen());
        tv_store_web.setText(store.getLinkWeb());
        tv_store_phone.setText(store.getPhone());
        setListMenu();
    }
    
    private void setListMenu() {
        storeInformationPresenter = new StoreInformationPresenter(getContext(), this);
        rec_Menu = view.findViewById(R.id.rec_Menu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rec_Menu.setLayoutManager(linearLayoutManager);
        mListMenu = new ArrayList<>();
        menuAdapter = new MenuAdapter(mListMenu, this.getContext());
        rec_Menu.setAdapter(menuAdapter);
        storeInformationPresenter.addToMenuList(store.getId_store());
        btn_image_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + store.getPhone()));
                startActivity(callIntent);
            }
        });
        btn_gotoWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = store.getLinkWeb();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (Exception exception) {
                    Toast.makeText(getContext(), "Trang web hiện đang bị lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setMenuList(Menu menu) {
        mListMenu.add(menu);
        menuAdapter.notifyDataSetChanged();
    }
}
