package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.CheckAccountView;
import com.example.btl.model.AccountModel;

public class AccountFragmentPresenter {
    private Context mContext;
    private CheckAccountView checkAccountView;

    public AccountFragmentPresenter(Context mContext, CheckAccountView checkAccountView) {
        this.mContext = mContext;
        this.checkAccountView = checkAccountView;
    }

    public void addInfoAccount() {
        AccountModel.readUserList(mContext, checkAccountView);
    }

}
