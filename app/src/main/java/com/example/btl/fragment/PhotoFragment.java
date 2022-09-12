package com.example.btl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.btl.model.Photo;
import com.example.btl.R;

public class PhotoFragment extends Fragment {
    private Photo photo;
    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_photo, container, false);
        Bundle bundle = getArguments();
        photo = (Photo) bundle.get("object_phote");
        ImageView imageView = mview.findViewById(R.id.imgPhoto);
        Glide.with(this).load(photo.getResoureId()).error(R.drawable.ic_launcher_background).into(imageView);
        return mview;
    }
}
