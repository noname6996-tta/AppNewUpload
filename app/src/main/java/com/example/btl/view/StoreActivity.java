package com.example.btl.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.adapter.PhotoAdapter;
import com.example.btl.fragment.StoreInformationFragment;
import com.example.btl.fragment.StoreLocationFragment;
import com.example.btl.fragment.StoreRankFragment;
import com.example.btl.myinterface.callback.CheckFavorite;
import com.example.btl.myinterface.CheckAddStoreItems;
import com.example.btl.model.Photo;
import com.example.btl.model.Store;
import com.example.btl.presenter.StorePresenter;
import com.example.btl.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class StoreActivity extends AppCompatActivity implements CheckAddStoreItems, CheckFavorite {
    private CheckBox chk_love;
    private ViewPager2 mviewPager2;
    private CircleIndicator3 mcircleIndicator3;
    private List<Photo> mListphoto;
    private Store store;
    private Toolbar toolBar_store;
    private TextView store_name, store_star;
    public static Integer ID_LOVE_NEED;
    private ImageView img_StoreBack;
    protected BottomNavigationView bottomNavigationView_store;
    private StorePresenter storePresenter;
    public static Integer IDSTORE;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mviewPager2.getCurrentItem() == mListphoto.size() - 1) {
                mviewPager2.setCurrentItem(0);
            } else {
                mviewPager2.setCurrentItem(mviewPager2.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        mapping();
        initUI();

    }

    public Store getStore() {
        return store;
    }

    public void initUI() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        store = (Store) bundle.get("store");
        storePresenter = new StorePresenter(StoreActivity.this, this, this);
        storePresenter.updateStarStore_store(store.getId_store());
        storePresenter.getStartCount(store.getId_store());
        IDSTORE = store.getId_store();
        store_name.setText(store.getName_store());
        toolBar_store.setTitle(store.getName_store());
        store_star.setText(store.getStar_store() + "");
        img_StoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setFragment();
        storePresenter.readForfavorite(store, chk_love);
        storePresenter.insertForfavorite(store, chk_love);
    }

    public void setFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new StoreInformationFragment());
        fragmentTransaction.commit();
        PhotoAdapter photoAdapter = new PhotoAdapter(this, getListPhoto());
        mviewPager2.setAdapter(photoAdapter);
        mcircleIndicator3.setViewPager(mviewPager2);
        mviewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
        bottomNavigationView_store = findViewById(R.id.bottomNavigationView_store);
        bottomNavigationView_store.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_store_info) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, new StoreInformationFragment());
                    fragmentTransaction.commit();

                } else if (id == R.id.bottom_store_ratting) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, new StoreRankFragment());
                    fragmentTransaction.commit();

                } else if (id == R.id.bottom_store_location) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, new StoreLocationFragment());
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }

    public void mapping() {
        store_name = findViewById(R.id.store_name);
        store_star = findViewById(R.id.store_star);
        chk_love = findViewById(R.id.chk_love);
        mviewPager2 = findViewById(R.id.view_paeger_store);
        mcircleIndicator3 = findViewById(R.id.circle_indercator3_store);
        img_StoreBack = findViewById(R.id.img_StoreBack);
        toolBar_store = findViewById(R.id.toolBar_store);
    }

    private List<Photo> getListPhoto() {
        mListphoto = new ArrayList<>();
        mListphoto.add(new Photo(1, "https://cdn.lauphan.com/photo-storage/myFile-1643523231113.jpeg", 1));
        mListphoto.add(new Photo(1, "https://cdn.lauphan.com/photo-storage/myFile-1643523231113.jpeg", 1));
        mListphoto.add(new Photo(1, "https://cdn.lauphan.com/photo-storage/myFile-1643523231113.jpeg", 1));
        return mListphoto;
    }

    @Override
    public void addString(String text) {
        store_star.setText(text + "");
    }

    @Override
    public void deleteSuccess() {
        Log.e("StoreAcitivy-bug", "Gia tri rong");
    }

    @Override
    public void addSuccess() {
        Log.e("StoreAcitivy-bug", "Thanh cong");
    }

    @Override
    public void failRespon(String e) {
        Log.e("StoreAcitivy-bug", e);
    }

    @Override
    public void setCheckBox(Integer id_love) {
        StoreActivity.ID_LOVE_NEED = id_love;
        chk_love.setChecked(true);
    }
}