package com.example.btl.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.Interface.ForGotPassInterface;
import com.example.btl.Mail.JavaMailAPI;
import com.example.btl.Presenter.ForGotPassPresenter;
import com.example.btl.R;

import java.util.Random;

public class ForgotPassword extends AppCompatActivity implements ForGotPassInterface {
    private EditText email;
    private Button button;
    private ForGotPassPresenter forGotPassPresenter;
    int pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mapping();
    }

    public void mapping() {
        forGotPassPresenter = new ForGotPassPresenter(ForgotPassword.this, this);
        email = findViewById(R.id.email);
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senEmail();
            }
        });
    }

    private void senEmail() {
        Random random = new Random();
        pass = random.nextInt(999999);
        String mEmail = email.getText().toString();
        forGotPassPresenter.sendMailAndChangePass(mEmail, String.valueOf(pass));
        String mSubject = "Gửi mật khẩu mới cho tài khoản " + mEmail;
        String mMessage = "mật khẩu mới của bạn là: " + pass + " \n Vui lòng thay đổi mật khẩu sau khi đăng nhập";
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);
        javaMailAPI.execute();
    }

    @Override
    public void haveBug(String e) {
        Toast.makeText(this, "Lỗi:" + e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessChangePass() {
        Toast.makeText(this, "Change Success", Toast.LENGTH_SHORT).show();
    }
}