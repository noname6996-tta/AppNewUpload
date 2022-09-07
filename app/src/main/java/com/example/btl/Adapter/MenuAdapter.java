package com.example.btl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl.Model.Menu;
import com.example.btl.R;


import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    private List<Menu> mMenu;
    private Context mContext;

    public MenuAdapter(List<Menu> mMenu, Context mContext) {
        this.mMenu = mMenu;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_menu,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = mMenu.get(position);
        if (menu == null){
            return;
        }
        holder.tv_nameDish.setText("Tên món: "+menu.getNameDish());
        holder.tv_namePrice.setText("Thành tiền: "+menu.getPriceDish());
        Glide.with(mContext).load(menu.getImageDish()).error(R.drawable.ic_launcher_background).into(holder.img_Dish);

    }

    @Override
    public int getItemCount() {
        if (mMenu != null){
            return mMenu.size();
        }
        return 0;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nameDish,tv_namePrice;
        private ImageView img_Dish;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namePrice = itemView.findViewById(R.id.tv_namePrice);
            tv_nameDish = itemView.findViewById(R.id.tv_nameDish);
            img_Dish = itemView.findViewById(R.id.img_Dish);
        }
    }
}
