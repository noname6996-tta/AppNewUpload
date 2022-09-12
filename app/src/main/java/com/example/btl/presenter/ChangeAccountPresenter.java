package com.example.btl.presenter;

import android.content.Context;
import android.widget.EditText;

import com.example.btl.myinterface.CheckAccountUpdate;
import com.example.btl.model.AccountModel;
import com.example.btl.model.User;


public class ChangeAccountPresenter {
    private Context mContext;
    private CheckAccountUpdate checkAccountUpdate;

    public ChangeAccountPresenter(Context mContext, CheckAccountUpdate checkAccountUpdate) {
        this.mContext = mContext;
        this.checkAccountUpdate = checkAccountUpdate;
    }

    public void onClickUpdateUser(User user){
        AccountModel.upDateUser(mContext,user, checkAccountUpdate);
        checkAccountUpdate.onSuccess();
    }
    public void onClickCheck(EditText editText){
        if (editText.getText().toString().trim().length()==0){
            checkAccountUpdate.onFailtoUpdate();
        }
    }

}
