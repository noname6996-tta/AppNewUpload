package com.example.btl.presenter;


import android.content.Context;

import com.example.btl.myinterface.RegesterInterface;
import com.example.btl.model.UserModel;

public class RegisterPresenter {
    private Context mContext;
    private RegesterInterface regesterInterface;

    public RegisterPresenter(Context mContext, RegesterInterface regesterInterface) {
        this.mContext = mContext;
        this.regesterInterface = regesterInterface;
    }

    public void regesterCheck(final String email1, final String password1) {
        if (regesterInterface.checkEditText(email1) && regesterInterface.checkEditText(password1)) {
            UserModel.regesterAccount(email1, password1, regesterInterface, mContext);
        }
    }
}
