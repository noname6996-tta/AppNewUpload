package com.example.btl.Presenter;

import android.Manifest;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;
import com.example.btl.Interface.ChangeAccountInterface;
import com.example.btl.Model.AccountModel;
import com.example.btl.Model.User;
import java.util.List;


public class ChangeAccountPresenter {
    private Context mContext;
    private ChangeAccountInterface changeAccountInterface;

    public ChangeAccountPresenter(Context mContext, ChangeAccountInterface changeAccountInterface) {
        this.mContext = mContext;
        this.changeAccountInterface = changeAccountInterface;
    }

    public void onClickUpdateUser(User user){
        AccountModel.updateUser(mContext,user,changeAccountInterface);
        changeAccountInterface.onSuccess();
    }
    public void onClickCheck(EditText editText){
        if (editText.getText().toString().trim().length()==0){
            changeAccountInterface.onFailtoUpdate();
        }
    }

}
