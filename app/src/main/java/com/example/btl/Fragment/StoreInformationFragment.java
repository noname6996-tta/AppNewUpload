package com.example.btl.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.MenuAdapter;
import com.example.btl.Interface.CallBack.Store_Info_CallBack;
import com.example.btl.Model.Menu;
import com.example.btl.Model.Store;
import com.example.btl.Presenter.StoreInformationPresenter;
import com.example.btl.R;
import com.example.btl.View.MainActivity;
import com.example.btl.View.StoreActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StoreInformationFragment extends Fragment implements Store_Info_CallBack {

    private View view;
    private TextView store_timeopen,store_phone,store_web,store_address;
    private RecyclerView rec_Menu;
    private List<Menu> mListMenu;
    private MenuAdapter menuAdapter;
    private StoreActivity storeActivity;
    private Store store;
    private ImageView btn_image_call,btn_gotoWeb;
    private StoreInformationPresenter storeInformationPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store_infomation,container,false);

        storeActivity = (StoreActivity) getActivity();
        store = storeActivity.getStore();

        store_timeopen = view.findViewById(R.id.store_timeopen);
        store_phone = view.findViewById(R.id.store_phone);
        store_web = view.findViewById(R.id.store_web);
        btn_image_call = view.findViewById(R.id.btn_image_call);
        btn_gotoWeb = view.findViewById(R.id.btn_gotoWeb);
        store_timeopen.setText(store.gettimeopen());
        store_web.setText(store.getLinkWeb());
        store_phone.setText(store.getPhone());
        setListMenu();
        return view;
    }

    private void setListMenu() {
        storeInformationPresenter = new StoreInformationPresenter(getContext(),this);
        rec_Menu = view.findViewById(R.id.rec_Menu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        rec_Menu.setLayoutManager(linearLayoutManager);

        mListMenu = new ArrayList<>();
        menuAdapter = new MenuAdapter(mListMenu,this.getContext());

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
                } catch (Exception exception){
                    Toast.makeText(getContext(), "Trang web hiện đang bị lỗi", Toast.LENGTH_SHORT).show();
                }
            }});
    }


    @Override
    public void setMenuList(Menu menu) {
        mListMenu.add(menu);
        menuAdapter.notifyDataSetChanged();
    }
}
