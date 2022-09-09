package com.example.btl.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl.Fragment.AccountFragment;
import com.example.btl.Fragment.NewsFragment;
import com.example.btl.Fragment.FavoriteFragment;
import com.example.btl.Fragment.HomeFragment;
import com.example.btl.Fragment.SearchFragment;

public class MyViewPageAdapter extends FragmentStateAdapter {
    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new FavoriteFragment();
            case 3:
                return new NewsFragment();
            case 4:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
