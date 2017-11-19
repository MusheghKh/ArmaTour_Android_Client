package com.armatour.aramtour.view.tours;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.armatour.aramtour.R;
import com.armatour.aramtour.view.map.MapActivity;
import com.armatour.aramtour.view.places.PlaceActivity;
import com.bumptech.glide.Glide;

public class TourActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TITLE = "tour_title";
    public static final String DESCRIPTION = "tour_description";

    private ImageView image;
    private TextView title;
    private TextView description;
    private Button buttonMap;
    private Button buttonTarget;
    private Button buttonContact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        image = (ImageView) findViewById(R.id.tour_image);
        title = (TextView) findViewById(R.id.tour_company_title);
        description = (TextView) findViewById(R.id.tour_description);
        buttonMap = (Button) findViewById(R.id.button_map);
        buttonContact = (Button) findViewById(R.id.button_contact);
        buttonTarget = (Button) findViewById(R.id.button_target);

        buttonMap.setOnClickListener(this);
        buttonContact.setOnClickListener(this);
        buttonTarget.setOnClickListener(this);

        title.setText(getIntent().getStringExtra(TITLE));

        StringBuilder builder = new StringBuilder("");
        String desc = getIntent().getStringExtra(DESCRIPTION);
        for (int i = 0; i < 100; i++){
            builder.append(desc);
            builder.append(" ");
        }
        description.setText(builder.toString());

        String url = "http://www.freetourssydney.com.au/images/i-am-free-tours-guide.jpg";
        Glide.with(this).load(url).into(image);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.button_map:
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
            case R.id.button_target:
                intent = new Intent(this, PlaceActivity.class);
                intent.putExtra(PlaceActivity.TITLE, "sometitle");
                intent.putExtra(PlaceActivity.DESCRIPTION, "somedescription");
                startActivity(intent);
                break;
            case R.id.button_contact:
                break;
        }
    }
}
