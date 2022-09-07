package com.example.btl.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.btl.Model.Photo;
import com.example.btl.Presenter.PhotoFragmentPresenter;
import com.example.btl.R;

public class Photo_fragment extends Fragment {
    private PhotoFragmentPresenter photoFragmentPresenter;
    private Photo photo;
    private View mview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_photo,container,false);

        Bundle bundle = getArguments();
        photo = (Photo) bundle.get("object_phote");
//        Bundle bundle = getArguments();
//        photoFragmentPresenter.getData(photo,bundle);

        ImageView imageView = mview.findViewById(R.id.imgPhoto);
        Glide.with(this).load(photo.getResoureId()).error(R.drawable.ic_launcher_background).into(imageView);
        return mview;
    }
}
