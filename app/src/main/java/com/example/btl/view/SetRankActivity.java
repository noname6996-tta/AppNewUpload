package com.example.btl.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.myinterface.CheckAddRank;
import com.example.btl.model.Rank;
import com.example.btl.presenter.RankPresenter;
import com.example.btl.R;

public class SetRankActivity extends AppCompatActivity implements CheckAddRank {
    private ImageView img_RattingBack;
    private TextView tv_your_current_rating;
    private RatingBar rat_ratingBar;
    private EditText edt_comment_ratting;
    private Button btn_sent_ratting;
    private RankPresenter rankPresenter;

    Float star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratting);
        initUI();
    }

    private void initUI() {
        rankPresenter = new RankPresenter(this.getApplicationContext(), this);
        tv_your_current_rating = findViewById(R.id.tv_your_current_rating);
        rat_ratingBar = findViewById(R.id.rat_ratingBar);
        edt_comment_ratting = findViewById(R.id.edt_comment_ratting);
        btn_sent_ratting = findViewById(R.id.btn_sent_ratting);
        img_RattingBack = findViewById(R.id.img_RattingBack);
        img_RattingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        rat_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tv_your_current_rating.setText("Bạn đánh giá: " + v + " sao");
                star = v;
            }
        });
        Bundle bundle = getIntent().getExtras();
        rankPresenter.check(bundle);
        int a = bundle.getInt("a");
        btn_sent_ratting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRatting(a);
            }
        });
    }

    public void onClickRatting(Integer a1) {
        Rank rank = new Rank(0, a1, LoginActivity.id_user, star, edt_comment_ratting.getText().toString().trim());
        rankPresenter.addToRattingList(rank);
        rankPresenter.updateStarStore(a1);
    }

    @Override
    public void onSuccess() {
        openFeedBackDialog(Gravity.BOTTOM);
    }

    @Override
    public void failRespon(String a) {
        Toast.makeText(this, "Fail and Bug" + a, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNullItem() {
        Toast.makeText(this, "Dữ liệu trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void callBack() {

    }

    @Override
    public void canRatting() {

    }

    @Override
    public void cannotRatting() {

    }

    private void openFeedBackDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_feedback);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(true);

        Button button = dialog.findViewById(R.id.btn_after_feedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onBackPressed();
            }
        });

        dialog.show();
    }
}