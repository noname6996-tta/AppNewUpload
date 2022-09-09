package com.example.btl.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.btl.Adapter.MyViewPageAdapter;
import com.example.btl.Fragment.AccountFragment;
import com.example.btl.Fragment.FavoriteFragment;
import com.example.btl.Fragment.HomeFragment;
import com.example.btl.Fragment.NewsFragment;
import com.example.btl.Fragment.SearchFragment;
import com.example.btl.Presenter.MainAcitivyPresenter;
import com.example.btl.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    public static String IP_LOCALHOST = "192.168.43.207";
    protected Toolbar toolBar;
    private BottomNavigationView bottomNavigationView;
    private MainAcitivyPresenter mainAcitivyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        setFragmentView();
    }

    private void mapping() {
        mainAcitivyPresenter = new MainAcitivyPresenter(MainActivity.this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        mainAcitivyPresenter.getNotifacationFavorite();
        toolBar = findViewById(R.id.toolBar);
    }

    // set fragment transaction with menu, add on click bottomNavigationView
    private void setFragmentView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, new HomeFragment());
        fragmentTransaction.commit();
        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(this);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame, new HomeFragment());
                    fragmentTransaction.commit();
                } else if (id == R.id.bottom_search) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame, new SearchFragment());
                    fragmentTransaction.commit();
                } else if (id == R.id.bottom_heart) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame, new FavoriteFragment());
                    fragmentTransaction.commit();
                } else if (id == R.id.bottom_News) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame, new NewsFragment());
                    fragmentTransaction.commit();
                } else if (id == R.id.bottom_account) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame, new AccountFragment());
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        openFeedBackDialog(Gravity.CENTER);
    }

    private void openFeedBackDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_close_app);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);
        Button btn_dont_logout = dialog.findViewById(R.id.btn_dont_logout);
        btn_dont_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button bnt_logout = dialog.findViewById(R.id.bnt_logout);
        bnt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dialog.show();
    }
}
