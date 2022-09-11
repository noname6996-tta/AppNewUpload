package com.example.btl.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.btl.Model.Ratting;
import com.example.btl.Model.RattingModel;
import com.example.btl.R;
import com.example.btl.View.LoginActivity;
import com.example.btl.View.MainActivity;
import com.example.btl.View.UpdateRattingActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RattingAdapter extends RecyclerView.Adapter<RattingAdapter.RattingViewHolder> {
    private List<Ratting> mListRatting;
    private Context mContext;
    private Ratting ratting;

    public RattingAdapter(List<Ratting> mListRatting, Context mContext) {
        this.mListRatting = mListRatting;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RattingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ratting, parent, false);
        return new RattingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RattingViewHolder holder, int position) {
        ratting = mListRatting.get(position);
        if (ratting == null) {
            return;
        }
        String url = "http://" + MainActivity.IP_LOCALHOST + "/AndroidBTL/btl/user/selectUser.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        try {
                            addDataRank(jsonObject, response, holder);
                        } catch (Exception exception) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Vui lòng bật mạng để sử dụng ứng dụng", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

        holder.textViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mContext, holder.textViewOptions);
                popup.inflate(R.menu.menu_ratting);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                showDialogMenu1();
                                break;
                            case R.id.menu2:
                                showDialogMenu2();
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
        if (LoginActivity.id_user != ratting.getId_user()) {
            holder.textViewOptions.setVisibility(View.INVISIBLE);

        } else {
            holder.textViewOptions.setVisibility(View.VISIBLE);
        }
    }

    public void addDataRank(JSONObject jsonObject, JSONArray response, RattingViewHolder holder) throws JSONException {
        for (int i = 0; i < response.length(); i++) {
            jsonObject = response.getJSONObject(i);
            Integer id = Integer.valueOf(jsonObject.getString("id"));
            String username = jsonObject.getString("username");
            String date = jsonObject.getString("date");
            String address = jsonObject.getString("address");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");
            String password = jsonObject.getString("password");
            String image = jsonObject.getString("image");
            if (ratting.getId_user() == id) {
                holder.tv_ratting_comment.setText(ratting.getComment());
                holder.tv_ratting_user_name.setText(username);
                holder.tv_ratting_user_ratting.setText(ratting.getRatting() + "");
                Glide.with(mContext).load(image).error(R.drawable.ic_launcher_background).into(holder.tv_ratting_user_image);
            }
        }
    }

    public void showDialogMenu2() {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_delete_ratting);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);
        Button button = dialog.findViewById(R.id.btn_delete_ratting);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RattingModel.deleteRatting(mContext, ratting.getId_rating());
                Toast.makeText(mContext, "Xóa bình luận thành công", Toast.LENGTH_SHORT).show();
            }
        });
        Button button2 = dialog.findViewById(R.id.btn_cancel_delete_ratting);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showDialogMenu1() {
        Intent intent = new Intent(mContext, UpdateRattingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id_update", ratting.getId_rating());
        bundle.putFloat("ratting_update", ratting.getRatting());
        bundle.putString("comment_update", ratting.getComment());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mListRatting != null) {
            return mListRatting.size();
        }
        return 0;
    }

    public static class RattingViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout_ratting;
        private ImageView tv_ratting_user_image;
        private TextView tv_ratting_user_name, tv_ratting_user_ratting, tv_ratting_comment;
        private TextView textViewOptions;

        public RattingViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_ratting = itemView.findViewById(R.id.layout_ratting);
            tv_ratting_user_image = itemView.findViewById(R.id.tv_ratting_user_image);
            tv_ratting_user_name = itemView.findViewById(R.id.tv_ratting_user_name);
            tv_ratting_user_ratting = itemView.findViewById(R.id.tv_ratting_user_ratting);
            tv_ratting_comment = itemView.findViewById(R.id.tv_ratting_comment);
            textViewOptions = itemView.findViewById(R.id.textViewOptions);

        }
    }
}
