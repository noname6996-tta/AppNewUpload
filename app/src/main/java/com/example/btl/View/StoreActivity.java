package com.example.btl.View;

import static com.example.btl.R.drawable.dotted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.btl.Adapter.MenuAdapter;
import com.example.btl.Adapter.MyStoreViewAdapter;
import com.example.btl.Adapter.MyViewPageAdapter;
import com.example.btl.Adapter.PhotoAdapter;
import com.example.btl.Fragment.StoreInformationFragment;
import com.example.btl.Fragment.StoreLocationFragment;
import com.example.btl.Fragment.StorerattingFragment;
import com.example.btl.Interface.CallBack.StoreAcitivyCallBack;
import com.example.btl.Interface.CallBack.StoreCallback;
import com.example.btl.Interface.StoreAcitivyInterface;
import com.example.btl.Model.Category;
import com.example.btl.Model.Farvorite;
import com.example.btl.Model.Menu;
import com.example.btl.Model.Photo;
import com.example.btl.Model.Ratting;
import com.example.btl.Model.Store;
import com.example.btl.Model.User;
import com.example.btl.Presenter.StorePresenter;
import com.example.btl.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator3;

public class StoreActivity extends AppCompatActivity implements StoreAcitivyInterface , StoreAcitivyCallBack {
    private CheckBox chkLove;
    private ViewPager2 mviewPager2;
    private CircleIndicator3 mcircleIndicator3;
    private List<Photo> mListphoto;
    private Store store;
    private Toolbar toolBar_store;
    private TextView store_name,store_star;
    public static Integer id_love_need;
    private ImageView img_StoreBack;
    protected BottomNavigationView bottomNavigationView_store;
    private StorePresenter storePresenter;
    public static Integer IDSTORE;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mviewPager2.getCurrentItem() == mListphoto.size()-1){
                mviewPager2.setCurrentItem(0);
            } else {
                mviewPager2.setCurrentItem(mviewPager2.getCurrentItem()+1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        initUI();

    }
    public Store getStore() {
        return store;
    }

    public void initUI(){
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        store = (Store) bundle.get("store");
        storePresenter = new StorePresenter(StoreActivity.this,this,this);
        storePresenter.updateStarStore_store(store.getId_store());
        storePresenter.getStartCount(store.getId_store());
        IDSTORE = store.getId_store();
        store_name = findViewById(R.id.store_name);
        store_name.setText(store.getName_store());
        toolBar_store = findViewById(R.id.toolBar_store);
        toolBar_store.setTitle(store.getName_store());
        img_StoreBack = findViewById(R.id.img_StoreBack);
        store_star = findViewById(R.id.store_star);
        store_star.setText(store.getStar_store()+"");

        img_StoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,new StoreInformationFragment());
        fragmentTransaction.commit();

        //
        mviewPager2 = findViewById(R.id.view_paeger_store);
        mcircleIndicator3 = findViewById(R.id.circle_indercator3_store);

        PhotoAdapter photoAdapter = new PhotoAdapter(this,getListPhoto());

        mviewPager2.setAdapter(photoAdapter);
        mcircleIndicator3.setViewPager(mviewPager2);

        mviewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });
        bottomNavigationView_store = findViewById(R.id.bottomNavigationView_store);
        bottomNavigationView_store.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_store_info){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame,new StoreInformationFragment());
                    fragmentTransaction.commit();

                }else if (id == R.id.bottom_store_ratting){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame,new StorerattingFragment());
                    fragmentTransaction.commit();

                }
                else if (id == R.id.bottom_store_location){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame,new StoreLocationFragment());
                    fragmentTransaction.commit();

                }

                return true;
            }
        });

        chkLove = findViewById(R.id.chkLove);
        storePresenter.readForfavorite(store,chkLove);
        storePresenter.insertForfavorite(store,chkLove);
    }


    private List<Photo> getListPhoto() {
        mListphoto = new ArrayList<>();
        mListphoto.add(new Photo(1,"https://cdn.lauphan.com/photo-storage/myFile-1643523231113.jpeg",1));
        mListphoto.add(new Photo(1,"https://cdn.lauphan.com/photo-storage/myFile-1643523231113.jpeg",1));
        mListphoto.add(new Photo(1,"https://cdn.lauphan.com/photo-storage/myFile-1643523231113.jpeg",1));
        return mListphoto;
    }
    @Override
    public void addString(String text) {
        store_star.setText(text+"");
    }

    @Override
    public void deleteSuccess() {
        Log.e("StoreAcitivy-bug","Gia tri rong");
    }

    @Override
    public void addSuccess() {
        Log.e("StoreAcitivy-bug","Thanh cong");
    }

    @Override
    public void failRespon(String e) {
        Log.e("StoreAcitivy-bug",e);
    }

    @Override
    public void setCheckBox(Integer id_love) {
        StoreActivity.id_love_need = id_love;
        chkLove.setChecked(true);
    }
}