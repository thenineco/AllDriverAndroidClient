package com.soberdriver.client.soberdriver.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.mvp.models.UserCard;
import com.soberdriver.client.soberdriver.presentation.presenter.UserCardsPresenter;
import com.soberdriver.client.soberdriver.presentation.view.UserCardsView;
import com.soberdriver.client.soberdriver.ui.activity.AddNewCardActivity;
import com.soberdriver.client.soberdriver.ui.adapter.userCards.UserCardsAdapter;
import com.tubb.smrv.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserCardsFragment extends BaseAppFragment implements UserCardsView {
    public static final String TAG = "UserCardsFragment";
    @InjectPresenter
    UserCardsPresenter mUserCardsPresenter;
    @BindView(R.id.user_cards_recycler_view)
    SwipeMenuRecyclerView mCardsRecyclerView;


    public static UserCardsFragment newInstance() {
        UserCardsFragment fragment = new UserCardsFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_cards, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUserCardsRecyclerView();

        setStartParams();

    }

    private void setStartParams() {
        List<UserCard> userCards = new ArrayList<>();
        userCards.add(new UserCard("VISA **6565"));
        userCards.add(new UserCard("Master Card **6395"));
        userCards.add(new UserCard("VISA **8575"));
        ((UserCardsAdapter) mCardsRecyclerView.getAdapter()).setUserCards(userCards);
    }

    private void setUserCardsRecyclerView() {
        mCardsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCardsRecyclerView.setAdapter(new UserCardsAdapter(getContext()));
    }

    @OnClick(R.id.user_cards_add_new_card_btn)
    public void onClick() {
        startActivity(AddNewCardActivity.getIntent(getContext()));
    }
}
