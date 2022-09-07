package com.example.btl.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.Interface.CallBack.UpdateRatingInterface;
import com.example.btl.Model.RattingModel;
import com.example.btl.Presenter.UpdateRattingPresenter;
import com.example.btl.R;

public class UpdateRatting extends AppCompatActivity implements UpdateRatingInterface {
    private RatingBar ratingBarYours_update;
    private TextView tv_update_ratting;
    private EditText edt_update_comment_ratting;
    private Button btn_update_Ratting;
    private int id_ratting;
    private Float star;
    private UpdateRattingPresenter updateRattingPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ratting);
        initUi();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null){
            return;
        }
        id_ratting = bundle.getInt("id_update");
        tv_update_ratting.setText("Bạn đánh giá:"+bundle.getFloat("ratting_update")+" sao");
        ratingBarYours_update.setRating(bundle.getFloat("ratting_update"));
        edt_update_comment_ratting.setText(bundle.getString("comment_update"));
    }

    private void initUi() {
        updateRattingPresenter = new UpdateRattingPresenter(UpdateRatting.this,this);
        ratingBarYours_update = findViewById(R.id.ratingBarYours_update);
        tv_update_ratting = findViewById(R.id.tv_update_ratting);
        edt_update_comment_ratting = findViewById(R.id.edt_update_comment_ratting);
        btn_update_Ratting = findViewById(R.id.btn_update_Ratting);
        ratingBarYours_update.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tv_update_ratting.setText("Bạn đánh giá: "+ v +" sao");
                star = v;
            }
        });
        btn_update_Ratting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRattingPresenter.updateRatting_Store(id_ratting,star,edt_update_comment_ratting.getText().toString().trim());
            }
        });
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Thay đổi đánh giá thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String e) {
        Toast.makeText(this, "Thay đổi đánh giá có lỗi", Toast.LENGTH_SHORT).show();
        Log.e("Update_ratting_bug",e.toString());
    }
}