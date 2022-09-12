package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.ForGotPass;
import com.example.btl.model.UserModel;

public class ForGotPassPresenter {
    private Context context;
    private ForGotPass forGotPassInterface;

    public ForGotPassPresenter(Context context, ForGotPass forGotPassInterface) {
        this.context = context;
        this.forGotPassInterface = forGotPassInterface;
    }

    public void sendMailAndChangePass(String emailneed, String passneed) {
        UserModel.updateUser(emailneed, passneed, context, forGotPassInterface);
    }
}
