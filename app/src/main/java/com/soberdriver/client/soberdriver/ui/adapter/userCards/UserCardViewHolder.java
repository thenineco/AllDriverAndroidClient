package com.soberdriver.client.soberdriver.ui.adapter.userCards;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.mvp.models.UserCard;
import com.tubb.smrv.SwipeMenuLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zest .
 */

public class UserCardViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView mCircleImageView;
    private AppCompatTextView mCardNameTextView;

    public UserCardViewHolder(View itemView) {
        super(itemView);
        mCircleImageView = (CircleImageView) itemView.findViewById(
                R.id.user_card_item_circle_image_view);
        mCardNameTextView = (AppCompatTextView) itemView.findViewById(
                R.id.user_card_item_card_name_text_view);
        ((SwipeMenuLayout)itemView).setSwipeEnable(true);
    }

    public void bindViewHolder(UserCard userCard) {
        mCardNameTextView.setText(userCard.getCardName());
    }
}
