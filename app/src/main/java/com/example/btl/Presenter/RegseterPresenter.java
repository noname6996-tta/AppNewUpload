package com.example.btl.Presenter;


import android.content.Context;
import com.example.btl.Interface.RegesterInterface;
import com.example.btl.Model.User;
import com.example.btl.Model.UserModel;

public class RegseterPresenter {
    private Context mContext;
    private RegesterInterface regesterInterface;

    public RegseterPresenter(Context mContext, RegesterInterface regesterInterface) {
        this.mContext = mContext;
        this.regesterInterface = regesterInterface;
    }
    public void regesterCheck(final String email1, final String password1) {
        if (regesterInterface.checkEditText(email1) && regesterInterface.checkEditText(password1)) {
            UserModel.regesterAccount(email1,password1,regesterInterface,mContext);
        }
    }
}
