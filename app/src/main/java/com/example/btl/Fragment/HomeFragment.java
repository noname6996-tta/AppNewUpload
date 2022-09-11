package com.example.btl.Fragment;

import static android.content.Context.LOCATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.btl.Adapter.CategoryAdapter;
import com.example.btl.Adapter.PhotoAdapter;
import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Interface.CallBack.HomeFragmentcallBack;
import com.example.btl.Interface.HomeFragmentInterface;
import com.example.btl.Model.Category;
import com.example.btl.Model.Photo;
import com.example.btl.Model.Store;
import com.example.btl.Presenter.HomeFragmentPersenter;
import com.example.btl.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment implements HomeFragmentInterface, HomeFragmentcallBack, LocationListener {
    private LocationManager locationManager;
    private ProgressDialog progressDialog;
    private ViewPager2 mviewPager2;
    private CircleIndicator3 mcircleIndicator3;
    private List<Photo> mListphoto;
    private List<Category> categoryList;
    private List<Store> mListStore;
    private RecyclerView rec_category;
    private RecyclerView rec_Store;
    private StoreAdapter storeAdapter;
    private CategoryAdapter categoryAdapter;
    private View view;
    private HomeFragmentPersenter homeFragmentPersenter;
    private TextView tv_location_home;
    private PhotoAdapter photoAdapter;
    private Toolbar toolBar_main;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initUi();
        initStore();
        return view;
    }

    private void initUi() {
        homeFragmentPersenter = new HomeFragmentPersenter(getContext(), this, this);
        toolBar_main = view.findViewById(R.id.toolBar_main);
        mviewPager2 = view.findViewById(R.id.view_paeger2);
        mcircleIndicator3 = view.findViewById(R.id.circle_indercator3);
        photoAdapter = new PhotoAdapter(this.getContext(), getListPhoto());
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
        tv_location_home = view.findViewById(R.id.tv_location_home);

    }

    private void initStore() {
        // inti recycle view show store
        rec_Store = view.findViewById(R.id.rec_Store);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_Store.setLayoutManager(linearLayoutManager);
        mListStore = new ArrayList<>();
        storeAdapter = new StoreAdapter(mListStore, this.getContext());
        homeFragmentPersenter.addStore(mListStore);
        rec_Store.setAdapter(storeAdapter);

        // inti recycle view show category
        rec_category = view.findViewById(R.id.rec_category);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rec_category.setLayoutManager(gridLayoutManager);
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryList, this.getContext());
        homeFragmentPersenter.addCategory(categoryList);
        rec_category.setAdapter(categoryAdapter);
    }

    private List<Photo> getListPhoto() {
        mListphoto = new ArrayList<>();
        mListphoto.add(new Photo(1, "https://thefoodstorecafe.com/wp-content/uploads/2021/01/foodstore-logo-black-1-1.png?w=600", 1));
        mListphoto.add(new Photo(1, "https://thefoodstorecafe.files.wordpress.com/2021/01/foodstore-cafe_28-11_001-1.jpg", 1));
        mListphoto.add(new Photo(1, "https://thefoodstorecafe.files.wordpress.com/2021/01/foodstore-cafe_5-11_025.jpg", 1));
        mListphoto.add(new Photo(1, "https://thefoodstorecafe.files.wordpress.com/2021/01/foodstore_11-3_002.jpg", 1));
        return mListphoto;
    }


    @Override
    public void noInterNetConnect() {
        Toast.makeText(getContext(), "Vui lòng bật mạng để dùng ứng dụng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String e) {
        Log.d("LoadinHome", e.toString());
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            tv_location_home.setText(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProcessDialog() {
        progressDialog = new ProgressDialog(this.getContext(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Hệ thống đang tải dữ liệu");
        progressDialog.setMessage("Vui lòng chờ");
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        setProcessDialog();
        try {
            locationManager = (LocationManager) getContext().getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, (LocationListener) getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addToCategory(Category category) {
        categoryList.add(category);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToStore(Store store) {
        mListStore.add(store);
        storeAdapter.notifyDataSetChanged();
    }
}
