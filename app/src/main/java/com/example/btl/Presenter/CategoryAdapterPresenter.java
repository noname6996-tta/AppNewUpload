package com.example.btl.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.btl.Interface.CategoryAdaInterface;
import com.example.btl.Model.Category;
import com.example.btl.View.CategoryStoreActivity;

public class CategoryAdapterPresenter {
    private Context context;
    CategoryAdaInterface categoryAdaInterface;

    public CategoryAdapterPresenter(Context mContext) {
        this.context = mContext;
    }

    public void loadData(Category category, Context mContext) {
        Intent intent = new Intent(mContext, CategoryStoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", category);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }
}
