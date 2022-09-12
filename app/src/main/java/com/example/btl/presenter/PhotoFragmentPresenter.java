package com.example.btl.presenter;

import android.os.Bundle;

import com.example.btl.model.Photo;

public class PhotoFragmentPresenter {
    public void getData(Photo photo, Bundle bundle) {
        photo = (Photo) bundle.get("object_phote");
    }
}
