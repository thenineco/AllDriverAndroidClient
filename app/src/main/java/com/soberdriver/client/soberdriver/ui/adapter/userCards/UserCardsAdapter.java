package com.soberdriver.client.soberdriver.ui.adapter.userCards;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.mvp.models.UserCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zest .
 */

public class UserCardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<UserCard> mUserCards = new ArrayList<>();

    public UserCardsAdapter(Context context) {
        mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
         View view = layoutInflater.inflate(R.layout.user_card_item_container, parent, false);
        return new UserCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserCard userCard = mUserCards.get(position);
        ((UserCardViewHolder) holder).bindViewHolder(userCard);
    }

    public void setUserCards(List<UserCard> userCards) {
        mUserCards = userCards;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUserCards.size();
    }
}
