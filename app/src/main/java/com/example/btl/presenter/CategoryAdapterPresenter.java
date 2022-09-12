package com.example.btl.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.btl.myinterface.CheckAddCategory;
import com.example.btl.model.Category;
import com.example.btl.view.SearchCategoryActivity;

public class CategoryAdapterPresenter {
    private Context context;
    CheckAddCategory checkAddCategory;

    public CategoryAdapterPresenter(Context mContext) {
        this.context = mContext;
    }

    public void loadData(Category category, Context mContext) {
        Intent intent = new Intent(mContext, SearchCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", category);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }
}
