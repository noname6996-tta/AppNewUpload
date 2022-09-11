package com.example.btl.Presenter;

import android.content.Context;

import com.example.btl.Interface.CallBack.UpdateRatingInterface;
import com.example.btl.Model.RattingModel;

public class UpdateRattingPresenter {
    private Context context;
    private UpdateRatingInterface updateRatingInterface;

    public UpdateRattingPresenter(Context context, UpdateRatingInterface updateRatingInterface) {
        this.context = context;
        this.updateRatingInterface = updateRatingInterface;
    }

    public void updateRatting_Store(Integer id_ratting, Float star_rating, String comment_sating) {
        RattingModel.updateRatting(context, id_ratting, star_rating, comment_sating, updateRatingInterface);
    }
}
