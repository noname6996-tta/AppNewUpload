package com.example.btl.presenter;

import android.content.Context;
import android.widget.EditText;

import com.example.btl.myinterface.ChangeAccountInterface;
import com.example.btl.model.AccountModel;
import com.example.btl.model.User;


public class ChangeAccountPresenter {
    private Context mContext;
    private ChangeAccountInterface changeAccountInterface;

    public ChangeAccountPresenter(Context mContext, ChangeAccountInterface changeAccountInterface) {
        this.mContext = mContext;
        this.changeAccountInterface = changeAccountInterface;
    }

    public void onClickUpdateUser(User user){
        AccountModel.upDateUser(mContext,user,changeAccountInterface);
        changeAccountInterface.onSuccess();
    }
    public void onClickCheck(EditText editText){
        if (editText.getText().toString().trim().length()==0){
            changeAccountInterface.onFailtoUpdate();
        }
    }

}
