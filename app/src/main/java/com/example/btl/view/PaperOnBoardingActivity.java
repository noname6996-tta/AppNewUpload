package com.example.btl.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.btl.R;
import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class PaperOnBoardingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ramotion.paperonboarding.R.layout.onboarding_main_layout);
        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(com.ramotion.paperonboarding.R.id.onboardingRootView),
                getPaperOnboardingPages(), this);
        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                Intent intent = new Intent(PaperOnBoardingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<PaperOnboardingPage> getPaperOnboardingPages() {
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Nhóm 8",
                "Chào mừng bạn đến với bài tập của nhóm 8",
                Color.parseColor("#9999ff"), R.drawable.logoapp, R.drawable.ic_baseline_search_24);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("StoreFoodApp",
                "Ứng dụng chia sẽ những địa điểm ăn uống thú vị, thông qua các đánh giá, giá cả hợp lý",
                Color.parseColor("#8080ff"), R.drawable.logo, R.drawable.ic_baseline_local_fire_department_24);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Bắt đầu thôi",
                "Vuốt tiếp để khám phá khu vườn ẩm thực không giới hạn",
                Color.parseColor("#6666ff"), R.drawable.img_2, R.drawable.ic_baseline_navigate_next_24);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }
}