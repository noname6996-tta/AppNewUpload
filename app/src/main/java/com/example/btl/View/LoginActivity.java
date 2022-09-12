package com.example.btl.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.Interface.LoginInterface;
import com.example.btl.Presenter.LoginPeresenter;
import com.example.btl.R;

public class LoginActivity extends AppCompatActivity implements LoginInterface {
    private EditText edt_Email;
    private EditText edt_password;
    private Button btn_Login;
    private TextView tv_loginfail, tv_forgotpass;
    private LinearLayout layout_singUp;
    private LoginPeresenter loginPeresenter;
    public static Integer id_user;
    public static String userName;
    public static String passUser;

    public static final String KEY_USERNAME = "email";
    public static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUi();
    }

    private void initUi() {
        loginPeresenter = new LoginPeresenter(LoginActivity.this, this);
        layout_singUp = findViewById(R.id.layout_singUp);
        edt_Email = findViewById(R.id.edt_Email);
        edt_password = findViewById(R.id.edt_password);
        tv_loginfail = findViewById(R.id.tv_loginfail);
        tv_forgotpass = findViewById(R.id.tv_forgotpass);
        btn_Login = findViewById(R.id.btn_Login);
        onCLiclItems();
    }

    public void onCLiclItems() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_Email.getText().toString().trim();
                String pass = edt_password.getText().toString().trim();
                loginPeresenter.loginAccount(email, pass);
            }
        });
        layout_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegesterActivity.class);
                startActivity(intent);
            }
        });
        tv_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFail() {
        tv_loginfail.setVisibility(View.VISIBLE);
        tv_loginfail.setText("Đăng nhập không thành công");
    }

    @Override
    public boolean checkEditText(String editText) {
        if (editText.trim().length() > 0)
            return true;
        else {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}