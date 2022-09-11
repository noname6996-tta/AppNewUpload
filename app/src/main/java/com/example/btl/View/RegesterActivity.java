package com.example.btl.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Interface.RegesterInterface;
import com.example.btl.Model.User;
import com.example.btl.Presenter.RegseterPresenter;
import com.example.btl.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegesterActivity extends AppCompatActivity implements RegesterInterface {
    private EditText edt_reger_email, edt_reges_pass;
    private Button btn_reges;
    private RegseterPresenter regseterPresenter;

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
        regseterPresenter = new RegseterPresenter(getApplicationContext(), this);
        btn_reges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_reger_email.getText().toString().trim();
                String pass = edt_reges_pass.getText().toString().trim();
                regseterPresenter.regesterCheck(email, pass);
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
        Toast.makeText(RegesterActivity.this, "Đăng ký thành công, đăng nhập lại để sử dụng", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegesterActivity.this, LoginActivity.class);
        RegesterActivity.this.startActivity(intent);
    }

    @Override
    public void onfail(String email) {
        Toast.makeText(this, "Tài khoản: " + email + " đã tồn tại", Toast.LENGTH_SHORT).show();
    }
}