package com.example.btl.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl.Fragment.Photo_fragment;
import com.example.btl.Model.Photo;
import com.example.btl.View.StoreActivity;

import java.util.List;

public class PhotoAdapter extends FragmentStateAdapter {
    private List<Photo> mlistphoto;


    public PhotoAdapter(StoreActivity storeActivity, List<Photo> listPhoto) {
        super(storeActivity);
        mlistphoto = listPhoto;
    }

    public PhotoAdapter(Context context, List<Photo> listPhoto) {
        super((FragmentActivity) context);
        mlistphoto = listPhoto;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Photo photo = mlistphoto.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_phote", photo);
        Photo_fragment photo_fragment = new Photo_fragment();
        photo_fragment.setArguments(bundle);
        return photo_fragment;
    }

    @Override
    public int getItemCount() {
        if (mlistphoto != null) {
            return mlistphoto.size();
        }
        return 0;
    }
}
