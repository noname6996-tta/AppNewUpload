package com.example.btl.Presenter;

import android.content.Context;

import com.example.btl.Interface.LoginInterface;
import com.example.btl.Model.User;
import com.example.btl.Model.UserModel;
import com.example.btl.View.MainActivity;

public class LoginPeresenter {
    public static final String URL_LOGIN = "http://"+MainActivity.IPLOCALHOST+"/AndroidBTL/btl/user/login.php";
    private Context context;
    LoginInterface loginInterface;

    public LoginPeresenter(Context context, LoginInterface loginInterface) {
        this.context = context;
        this.loginInterface = loginInterface;
    }
    public void loginAccount(final String email1, final String password1) {
        if (loginInterface.checkEditText(email1) && loginInterface.checkEditText(password1)) {
            UserModel.checkLogin(email1,password1,context,loginInterface);
        }
    }
}
