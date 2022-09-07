package com.example.btl.Presenter;

import android.os.Bundle;

import com.example.btl.Model.Photo;

public class PhotoFragmentPresenter {
    public void getData(Photo photo,Bundle bundle){
        photo = (Photo) bundle.get("object_phote");
    }
}
