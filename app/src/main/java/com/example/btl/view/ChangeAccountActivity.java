
package com.example.btl.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.btl.myinterface.ChangeAccountInterface;
import com.example.btl.model.RealPathUtil;
import com.example.btl.model.User;
import com.example.btl.presenter.ChangeAccountPresenter;
import com.example.btl.R;

import java.io.IOException;

public class ChangeAccountActivity extends AppCompatActivity implements ChangeAccountInterface {
    private ImageView img_change_account, btnSelectImage, btnBack_onChange;
    private EditText tv_change_nameaccount, tv_change_dateaccount, tv_change_addressaccount, tv_change_emailaccount, tv_change_phoneaccount, tv_change_pass;
    private Button btn_update_Account;
    private ChangeAccountPresenter changeAccountPresenter;
    public static final int MY_REQUESR_CODE = 10;
    private Uri url;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent == null) {
                            return;
                        }
                        Uri imageUri = intent.getData();
                        url = imageUri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            img_change_account.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account);
        mapping();
        initUI();
    }

    private void mapping() {
        btnSelectImage = findViewById(R.id.btnSelectImage);
        img_change_account = findViewById(R.id.img_change_account);
        tv_change_nameaccount = findViewById(R.id.tv_change_nameaccount);
        tv_change_dateaccount = findViewById(R.id.tv_change_dateaccount);
        tv_change_addressaccount = findViewById(R.id.tv_change_addressaccount);
        tv_change_emailaccount = findViewById(R.id.tv_change_emailaccount);
        tv_change_phoneaccount = findViewById(R.id.tv_change_phoneaccount);
        btn_update_Account = findViewById(R.id.btn_update_Account);
        btnBack_onChange = findViewById(R.id.btnBack_onChange);
        tv_change_pass = findViewById(R.id.tv_change_pass);
    }

    private void initUI() {
        changeAccountPresenter = new ChangeAccountPresenter(ChangeAccountActivity.this, this);
        setData();
        btn_update_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBtnUpdate();
            }
        });
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });
        btnBack_onChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Roongx", Toast.LENGTH_SHORT).show();
            return;
        }
        Glide.with(this).load(intent.getStringExtra("imageaccount")).error(R.drawable.ic_launcher_background).into(img_change_account);
        tv_change_nameaccount.setText(intent.getStringExtra("nameaccount"));
        tv_change_dateaccount.setText(intent.getStringExtra("dateaccount"));
        tv_change_addressaccount.setText(intent.getStringExtra("addressaccount"));
        tv_change_emailaccount.setText(intent.getStringExtra("emailaccount"));
        tv_change_phoneaccount.setText(intent.getStringExtra("phoneaccount"));
        tv_change_pass.setText(intent.getStringExtra("passaccount"));
    }

    private void onClickBtnUpdate() {
        Integer id = LoginActivity.id_user;
        String name = tv_change_nameaccount.getText().toString().trim();
        String date = tv_change_dateaccount.getText().toString().trim();
        String address = tv_change_addressaccount.getText().toString().trim();
        String email = tv_change_emailaccount.getText().toString().trim();
        String phone = tv_change_phoneaccount.getText().toString().trim();
        String pass = tv_change_pass.getText().toString().trim();
        changeAccountPresenter.onClickCheck(tv_change_nameaccount);
        changeAccountPresenter.onClickCheck(tv_change_dateaccount);
        changeAccountPresenter.onClickCheck(tv_change_addressaccount);
        changeAccountPresenter.onClickCheck(tv_change_emailaccount);
        changeAccountPresenter.onClickCheck(tv_change_phoneaccount);
        changeAccountPresenter.onClickCheck(tv_change_pass);
        String strRealImage = RealPathUtil.getRealPath(ChangeAccountActivity.this, url);
        String filename = strRealImage.substring(strRealImage.lastIndexOf("/") + 1);
        String finalPath = "http://" + MainActivity.IP_LOCALHOST + "/noxFile/Download/ImageShare/" + filename;
        User user = new User(id, name, date, address, email, phone, finalPath, pass);
        changeAccountPresenter.onClickUpdateUser(user);
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intentActivityResultLauncher.launch(Intent.createChooser(intent, "select Picture"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUESR_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(this, "Vui lòng cho phép mở thư viện ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailtoUpdate() {
        Toast.makeText(this, "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFail() {
    }
}