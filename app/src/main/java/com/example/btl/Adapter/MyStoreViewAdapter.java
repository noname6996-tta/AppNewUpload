package com.example.btl.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl.Fragment.StoreInformationFragment;
import com.example.btl.Fragment.StoreLocationFragment;
import com.example.btl.Fragment.StorerattingFragment;


public class MyStoreViewAdapter extends FragmentStateAdapter {


    public MyStoreViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new StoreInformationFragment();
            case 1:
                return new StorerattingFragment();
            case 2:
                return new StoreLocationFragment();
            default:
                return new StoreInformationFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
