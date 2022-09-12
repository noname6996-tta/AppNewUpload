package com.example.btl.presenter;


import android.content.Context;

import com.example.btl.myinterface.CheckRegister;
import com.example.btl.model.UserModel;

public class RegisterPresenter {
    private Context mContext;
    private CheckRegister checkRegister;

    public RegisterPresenter(Context mContext, CheckRegister checkRegister) {
        this.mContext = mContext;
        this.checkRegister = checkRegister;
    }

    public void regesterCheck(final String email1, final String password1) {
        if (checkRegister.checkEditText(email1) && checkRegister.checkEditText(password1)) {
            UserModel.regesterAccount(email1, password1, checkRegister, mContext);
        }
    }
}
