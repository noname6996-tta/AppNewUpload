package com.example.btl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl.Model.Store;
import com.example.btl.Model.StoreModel;
import com.example.btl.Model.User;
import com.example.btl.R;
import com.example.btl.View.StoreActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> implements Filterable {
    private List<Store> mStore;
    private List<Store> mStoreold;
    private Context mContext;

    public StoreAdapter(List<Store> mStore, Context mContext) {
        this.mStore = mStore;
        this.mContext = mContext;
        this.mStoreold = mStore;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_store,parent,false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = mStore.get(position);
        if (store == null){
            return;
        }
        StoreModel.readRattingForUpdateStore(mContext,store.getId_store());
        holder.tv_NameStore.setText(store.getName_store());
        Glide.with(mContext).load(store.getImage_store()).error(R.drawable.ic_launcher_background).into(holder.img_store);
        holder.tv_AddressStore.setText("Địa chỉ: "+store.getAddress_store());
        holder.tv_StarStore.setText(store.getStar_store()+" sao");
        holder.items_card_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("store",store);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mStore != null){
            return mStore.size();
        }
        return 0;
    }



    public class StoreViewHolder extends RecyclerView.ViewHolder{
        private CardView items_card_store;
        private ImageView img_store;
        private TextView tv_NameStore,tv_AddressStore,tv_StarStore;
        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            items_card_store = itemView.findViewById(R.id.items_card_store);
            img_store = itemView.findViewById(R.id.img_store);
            tv_NameStore = itemView.findViewById(R.id.tv_NameStore);
            tv_AddressStore = itemView.findViewById(R.id.tv_AddressStore);
            tv_StarStore = itemView.findViewById(R.id.tv_StarStore);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String srcSearch = charSequence.toString();
                if (srcSearch.isEmpty()){
                    mStore = mStoreold;
                } else {
                    List<Store> list = new ArrayList<>();
                    for(Store store : mStoreold){
                        if (store.getName_store().toLowerCase().contains(srcSearch.toLowerCase())){
                            list.add(store);
                        }
                    }
                    mStore = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mStore;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mStore = (List<Store>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
