package com.armatour.aramtour.view.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.armatour.aramtour.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class PlaceActivity extends AppCompatActivity{

    public static final String TITLE = "place_title";
    public static final String DESCRIPTION = "place_description";

    private ViewPager slider;
    private CircleIndicator indicator;
    private TextView placeTitle;
    private TextView placeDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        placeTitle = (TextView) findViewById(R.id.place_title);
        placeDescription = (TextView) findViewById(R.id.place_description);
        slider = (ViewPager) findViewById(R.id.slider);
        indicator = (CircleIndicator) findViewById(R.id.circle_indicator);

        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            View page = inflater.inflate(R.layout.item_slider, null);
            String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/%D4%B3%D5%A1%D5%BC%D5%B6%D5%AB%D5%AB_%D5%8F%D5%A1%D5%B3%D5%A1%D6%80_23.jpg/350px-%D4%B3%D5%A1%D5%BC%D5%B6%D5%AB%D5%AB_%D5%8F%D5%A1%D5%B3%D5%A1%D6%80_23.jpg";
            Glide.with(this).load(url).into((ImageView) page);
            pages.add(page);
        }

        ImageSliderAdapter adapter = new ImageSliderAdapter(pages);
        slider.setAdapter(adapter);
        indicator.setViewPager(slider);

        placeTitle.setText(getIntent().getStringExtra(TITLE));

        StringBuilder builder = new StringBuilder("");
        String desc = getIntent().getStringExtra(DESCRIPTION);
        for (int i = 0; i < 100; i++){
            builder.append(desc);
            builder.append(" ");
        }
        placeDescription.setText(builder.toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
