package com.sematec.learningproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Farnoosh on 2/28/2016.
 */
public class SplashActivity extends Activity implements View.OnClickListener{

    private PublicMethods publicMethods;
    private TextView title;
    private Button btn_calc;
    private Button btn_login;
    private Button btn_weather;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        publicMethods = new PublicMethods(this);

        title = (TextView) findViewById(R.id.title);
        title.setTypeface(publicMethods.getTypeFace());

        btn_calc = (Button) findViewById(R.id.btn_calc);
        btn_calc.setOnClickListener(this);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_weather = (Button) findViewById(R.id.btn_weather);
        btn_weather.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_calc){
            Intent calc_intent = new Intent(SplashActivity.this, CalcActivity.class);
            startActivity(calc_intent);
            //finish();
        }
        if(v.getId() == R.id.btn_login){
            Intent login_intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(login_intent);
            //finish();
        }
        if(v.getId() == R.id.btn_weather){
            Intent weather_intent = new Intent(SplashActivity.this, WeatherActivity.class);
            startActivity(weather_intent);
            //finish();
        }

    }
}
