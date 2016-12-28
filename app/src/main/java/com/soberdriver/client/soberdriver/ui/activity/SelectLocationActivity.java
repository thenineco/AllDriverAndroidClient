package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.SelectStartLocationPresenter;
import com.soberdriver.client.soberdriver.presentation.view.SelectStartLocationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;

public class SelectLocationActivity extends BaseAppActivity implements
        SelectStartLocationView {

    public static final String TAG = "SelectLocationActivity";

    public static final String LOCATION = "location";


    @InjectPresenter
    SelectStartLocationPresenter mSelectStartLocationPresenter;
    @BindView(R.id.select_location_back_btn)
    AppCompatImageView mBackBtn;
    @BindView(R.id.select_location_location_search_edit_text)
    AppCompatEditText mLocationSearchEditText;
    @BindView(R.id.select_location_microphone_btn)
    AppCompatImageView mMicrophoneBtn;
    @BindView(R.id.select_location_map_view)
    MapView mMapView;

    private MapController mMapController;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, SelectLocationActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_start_location);
        ButterKnife.bind(this);
        mMapController = mMapView.getMapController();
    }

    @OnClick({R.id.select_location_back_btn, R.id.select_location_microphone_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_location_back_btn:
                backToPreviousActivity();
                break;
            case R.id.select_location_microphone_btn:
                break;
        }
    }

    private void backToPreviousActivity() {
        Intent intent = new Intent();
        String selectedAddress = mLocationSearchEditText.getText().toString();
        intent.putExtra(LOCATION, selectedAddress);
        setResult(RESULT_OK, intent);
        finish();
    }
}
