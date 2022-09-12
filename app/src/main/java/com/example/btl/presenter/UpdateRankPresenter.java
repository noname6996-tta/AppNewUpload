package com.example.btl.presenter;

import android.content.Context;

import com.example.btl.myinterface.callback.UpdateRatingInterface;
import com.example.btl.model.RankModel;

public class UpdateRankPresenter {
    private Context context;
    private UpdateRatingInterface updateRatingInterface;

    public UpdateRankPresenter(Context context, UpdateRatingInterface updateRatingInterface) {
        this.context = context;
        this.updateRatingInterface = updateRatingInterface;
    }

    public void updateRatting_Store(Integer id_ratting, Float star_rating, String comment_sating) {
        RankModel.updateRatting(context, id_ratting, star_rating, comment_sating, updateRatingInterface);
    }
}
