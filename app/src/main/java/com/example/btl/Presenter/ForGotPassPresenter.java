package com.example.btl.Presenter;

import android.content.Context;

import com.example.btl.Interface.ForGotPassInterface;
import com.example.btl.Model.UserModel;

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
