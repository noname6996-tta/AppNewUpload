package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.model.VideoYoutube;
import com.example.btl.R;


import java.util.List;

public class VideoYoutubeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<VideoYoutube> videoYoutubeList;

    public VideoYoutubeAdapter(Context context, int layout, List<VideoYoutube> videoYoutubeList) {
        this.context = context;
        this.layout = layout;
        this.videoYoutubeList = videoYoutubeList;
    }

    @Override
    public int getCount() {
        return videoYoutubeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView img_thumbnail;
        TextView txt_view_title;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_view_title = view.findViewById(R.id.txt_view_title);
            holder.img_thumbnail = view.findViewById(R.id.img_thumbnail);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        VideoYoutube video = videoYoutubeList.get(i);
        holder.txt_view_title.setText(video.getTitle());
        Glide.with(context).load(video.getThumbnail()).error(R.drawable.ic_launcher_background).into(holder.img_thumbnail);
        return view;
    }
}
