package com.example.btl.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.myinterface.RegesterInterface;
import com.example.btl.presenter.RegisterPresenter;
import com.example.btl.R;

public class RegisterActivity extends AppCompatActivity implements RegesterInterface {
    private EditText edt_reger_email, edt_reges_pass;
    private Button btn_reges;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);
        initUI();
    }

    private void initUI() {
        edt_reger_email = findViewById(R.id.edt_reger_email);
        edt_reges_pass = findViewById(R.id.edt_reges_pass);
        btn_reges = findViewById(R.id.btn_reges);
        registerPresenter = new RegisterPresenter(getApplicationContext(), this);
        btn_reges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_reger_email.getText().toString().trim();
                String pass = edt_reges_pass.getText().toString().trim();
                registerPresenter.regesterCheck(email, pass);
            }
        });
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

    @Override
    public void onSuccess() {
        Toast.makeText(RegisterActivity.this, "Đăng ký thành công, đăng nhập lại để sử dụng", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
    }

    @Override
    public void onfail(String email) {
        Toast.makeText(this, "Tài khoản: " + email + " đã tồn tại", Toast.LENGTH_SHORT).show();
    }
}