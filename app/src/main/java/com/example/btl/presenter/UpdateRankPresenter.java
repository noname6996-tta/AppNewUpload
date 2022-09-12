package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.IUpdateRating;
import com.example.btl.model.RankModel;

public class UpdateRankPresenter {
    private Context context;
    private IUpdateRating IUpdateRating;

    public UpdateRankPresenter(Context context, IUpdateRating IUpdateRating) {
        this.context = context;
        this.IUpdateRating = IUpdateRating;
    }

    public void updateRatting_Store(Integer id_ratting, Float star_rating, String comment_sating) {
        RankModel.updateRatting(context, id_ratting, star_rating, comment_sating, IUpdateRating);
    }
}
