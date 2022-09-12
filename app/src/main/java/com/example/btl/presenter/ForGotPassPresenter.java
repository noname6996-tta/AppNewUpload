package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.ForGotPassInterface;
import com.example.btl.model.UserModel;

public class ForGotPassPresenter {
    private Context context;
    private ForGotPassInterface forGotPassInterface;

    public ForGotPassPresenter(Context context, ForGotPassInterface forGotPassInterface) {
        this.context = context;
        this.forGotPassInterface = forGotPassInterface;
    }

    public void sendMailAndChangePass(String emailneed, String passneed) {
        UserModel.updateUser(emailneed, passneed, context, forGotPassInterface);
    }
}
