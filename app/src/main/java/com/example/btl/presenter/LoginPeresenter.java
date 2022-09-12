package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.LoginInterface;
import com.example.btl.model.UserModel;
import com.example.btl.view.MainActivity;

public class LoginPeresenter {
    public static final String URL_LOGIN = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/user/login.php";
    private Context context;
    LoginInterface loginInterface;

    public LoginPeresenter(Context context, LoginInterface loginInterface) {
        this.context = context;
        this.loginInterface = loginInterface;
    }

    public void loginAccount(final String email1, final String password1) {
        if (loginInterface.checkEditText(email1) && loginInterface.checkEditText(password1)) {
            UserModel.checkLogin(email1, password1, context, loginInterface);
        }
    }
}
