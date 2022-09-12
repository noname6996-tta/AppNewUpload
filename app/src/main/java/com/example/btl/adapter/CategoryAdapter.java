package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl.model.Category;
import com.example.btl.presenter.CategoryAdapterPresenter;
import com.example.btl.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> mCategoryList;
    private Context mContext;
    private CategoryAdapterPresenter categoryAdapterPresenter;

    public CategoryAdapter(List<Category> mCategoryList, Context mContext) {
        this.mCategoryList = mCategoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mCategoryList.get(position);
        if (category == null) {
            return;
        }
        holder.tv_category.setText(category.getType());
        Glide.with(mContext).load(category.getImage_category()).error(R.drawable.ic_launcher_background).into(holder.img_category);
        holder.liner_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryAdapterPresenter = new CategoryAdapterPresenter(mContext);
                categoryAdapterPresenter.loadData(category, mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mCategoryList != null) {
            return mCategoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout liner_category;
        private ImageView img_category;
        private TextView tv_category;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            liner_category = itemView.findViewById(R.id.liner_category);
            img_category = itemView.findViewById(R.id.img_category);
            tv_category = itemView.findViewById(R.id.tv_category);
        }
    }
}
