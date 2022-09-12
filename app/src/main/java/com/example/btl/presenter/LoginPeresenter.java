package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.CheckLogin;
import com.example.btl.model.UserModel;
import com.example.btl.view.MainActivity;

public class LoginPeresenter {
    public static final String URL_LOGIN = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/user/login.php";
    private Context context;
    CheckLogin checkLogin;

    public LoginPeresenter(Context context, CheckLogin checkLogin) {
        this.context = context;
        this.checkLogin = checkLogin;
    }

    public void loginAccount(final String email1, final String password1) {
        if (checkLogin.checkEditText(email1) && checkLogin.checkEditText(password1)) {
            UserModel.checkLogin(email1, password1, context, checkLogin);
        }
    }
}
