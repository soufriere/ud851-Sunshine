package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // COMPLETE (2) Display the weather forecast that was passed from MainActivity

        String weatherString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                weatherString= null;
            } else {
                weatherString= extras.getString("weatherData");
            }
        } else {
            weatherString= (String) savedInstanceState.getSerializable("weatherData");
        }

        Log.d("string_retrieved", weatherString);


        TextView weatherDetailTextView = (TextView)findViewById(R.id.forecast_detail_text);
        weatherDetailTextView.setText(weatherString);


    }

}