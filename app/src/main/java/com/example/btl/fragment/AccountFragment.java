package com.example.btl.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.btl.myinterface.callback.CheckAccountView;
import com.example.btl.model.User;
import com.example.btl.presenter.AccountFragmentPresenter;
import com.example.btl.R;
import com.example.btl.view.ChangeAccountActivity;
import com.example.btl.view.LoginActivity;
import com.example.btl.view.YouTubeActivity;


public class AccountFragment extends Fragment implements CheckAccountView {
    private ImageView img_account;
    private TextView tv_name_account, tv_date_account, tv_address_account, tv_email_account, tv_phone_account;
    private TextView btn_change_account, btn_logout, btn_open_youtube;
    private AccountFragmentPresenter accountFragmentPresenter;
    private View view;
    private String srcImage, name, date, address, email, phone, pass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        mapping();
        initUI();

        return view;
    }

    private void mapping() {
        img_account = view.findViewById(R.id.img_account);
        tv_name_account = view.findViewById(R.id.tv_name_account);
        tv_date_account = view.findViewById(R.id.tv_date_account);
        tv_address_account = view.findViewById(R.id.tv_address_account);
        tv_email_account = view.findViewById(R.id.tv_email_account);
        tv_phone_account = view.findViewById(R.id.tv_phone_account);
        btn_change_account = view.findViewById(R.id.btn_change_account);
        btn_open_youtube = view.findViewById(R.id.btn_open_youtube);
        btn_logout = view.findViewById(R.id.btn_logout);
    }

    private void initUI() {
        accountFragmentPresenter = new AccountFragmentPresenter(getContext(), this);
        accountFragmentPresenter.addInfoAccount();
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Hệ thống");
                builder.setMessage("Bạn có muốn đăng xuất?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        btn_change_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeedBackDialog(getContext(), Gravity.CENTER);
                showDialog();
            }
        });
        btn_open_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), YouTubeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setDataAccount(Integer id, User user) {
        if (id.equals(LoginActivity.id_user)) {
            srcImage = user.getImage();
            name = user.getUsername();
            date = user.getDate();
            phone = user.getPhone();
            email = user.getEmail();
            address = user.getAddress();
            pass = user.getPassword();
            Glide.with(view.getContext()).load(srcImage).error(R.drawable.logoapp).into(img_account);
            tv_name_account.setText("Họ và tên: " + user.getUsername());
            tv_date_account.setText("Ngày sinh: " + user.getDate());
            tv_address_account.setText("Địa chỉ: " + user.getAddress());
            tv_email_account.setText("Email: " + user.getEmail());
            tv_phone_account.setText("Số điện thoại: " + user.getPhone());
        }
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Nhập mật khẩu đăng nhập");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                if (m_Text.trim().equalsIgnoreCase(LoginActivity.passUser)) {
                    nextScreenAfterDialog();
                } else {
                    Toast.makeText(getContext(), "Bạn vui lòng nhập đúng mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void openFeedBackDialog(Context context, int gravity) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_update_account);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);
        EditText editText = dialog.findViewById(R.id.edt_check_update_pass);
        Button button = dialog.findViewById(R.id.btn_after_enteraccount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m_Text = editText.getText().toString();
                if (m_Text.trim().equalsIgnoreCase(LoginActivity.passUser)) {
                    Intent intent = new Intent(getContext(), ChangeAccountActivity.class);
                    nextScreenAfterFeedBack(intent);
                    dialog.dismiss();
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Bạn vui lòng nhập đúng mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button button2 = dialog.findViewById(R.id.btn_after_canel_update);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void nextScreenAfterFeedBack(Intent intent) {
        intent = new Intent(getContext(), ChangeAccountActivity.class);
        intent.putExtra("imageaccount", srcImage);
        intent.putExtra("nameaccount", name);
        intent.putExtra("dateaccount", date);
        intent.putExtra("addressaccount", address);
        intent.putExtra("emailaccount", email);
        intent.putExtra("phoneaccount", phone);
        intent.putExtra("passaccount", pass);
    }

    public void nextScreenAfterDialog() {
        Intent intent = new Intent(getContext(), ChangeAccountActivity.class);
        intent.putExtra("imageaccount", srcImage);
        intent.putExtra("nameaccount", name);
        intent.putExtra("dateaccount", date);
        intent.putExtra("addressaccount", address);
        intent.putExtra("emailaccount", email);
        intent.putExtra("phoneaccount", phone);
        intent.putExtra("passaccount", pass);
        startActivity(intent);
    }
}
