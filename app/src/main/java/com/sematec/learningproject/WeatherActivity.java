package com.sematec.learningproject;

import android.app.Activity;
import android.os.Bundle;
/**/import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
/*

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
*/

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends Activity {
    private EditText txt_search;
    private Button btn_search;
    private Thread thread;
    private PublicMethods pm;
    private String web_result;
    private String city;
    private String region;
    private String url;
    private TextView result;
    private TextView value_city;
    private TextView title_city;
    private TextView city_value;
    private TextView city_title;
    private TextView country_value;
    private TextView country_title;
    private String temprature_value;
    private TextView high_value;
    private TextView high_title;
    private TextView low_value;
    private TextView low_title;
    private TextView text_value;
    private TextView text_title;
    private List forecastHigh = new ArrayList();
    private List forecastLow = new ArrayList();
    private List text = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        pm = new PublicMethods(this);
        txt_search = (EditText) findViewById(R.id.txt_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        //result = (TextView) findViewById(R.id.result);
        // value_city = (TextView) findViewById(R.id.value_city);
        //title_city = (TextView) findViewById(R.id.title_city);
        city_value = (TextView) findViewById(R.id.city_value);
        city_title = (TextView) findViewById(R.id.city_title);
        country_value = (TextView) findViewById(R.id.country_value);
        country_title = (TextView) findViewById(R.id.country_title);
        high_value = (TextView) findViewById(R.id.high_value);
        high_title = (TextView) findViewById(R.id.high_title);
        low_value = (TextView) findViewById(R.id.low_value);
        low_title = (TextView) findViewById(R.id.low_title);
        text_value = (TextView) findViewById(R.id.text_value);
        text_title = (TextView) findViewById(R.id.text_title);

        txt_search.setTypeface(pm.getTypeFace());
        btn_search.setTypeface(pm.getTypeFace());
        city_value.setTypeface(pm.getTypeFace());
        city_title.setTypeface(pm.getTypeFace());
        country_value.setTypeface(pm.getTypeFace());
        country_title.setTypeface(pm.getTypeFace());
        high_value.setTypeface(pm.getTypeFace());
        high_title.setTypeface(pm.getTypeFace());
        low_value.setTypeface(pm.getTypeFace());
        low_title.setTypeface(pm.getTypeFace());
        text_value.setTypeface(pm.getTypeFace());
        text_title.setTypeface(pm.getTypeFace());



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                city = txt_search.getText().toString();
                if (city.length() == 0) {
                    pm.showToast(getResources().getString(R.string.error_no_city_selected));
                } else {
                    url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + city + "%2C%20" + region + "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
                    pm.L("111111" + city);
                    pm.L("333" + url);
                    //getDataAsync(url);
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            web_result = getDataFromWeb(url);
                            pm.L("2222" + url);
                            pm.L("5555" + web_result);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                parseJson(web_result);
                                    parseJsonWithGson(web_result);
                                    //result.setText(web_result);
                                }
                            });

                        }
                    });
                    thread.start();

                }
            }
        });

    }


    public void parseJson(String str) {
        try {
            JSONObject all_obj = new JSONObject(str);


            //String count_str = all_obj.getString("count");

            // if(!count_str.equals("0")){

            String query_str = all_obj.getString("query");
            JSONObject query_obj = new JSONObject(query_str);

            String results_str = query_obj.getString("results");
            JSONObject results_obj = new JSONObject(results_str);

            String channel_str = results_obj.getString("channel");
            JSONObject channel_obj = new JSONObject(channel_str);

            String location_str = channel_obj.getString("location");
            JSONObject location_obj = new JSONObject(location_str);

            String city_str = location_obj.getString("city");
            value_city.setText(city);

            pm.L("212151 " + location_obj);


            //   }else {
            //       pm.showToast("No city is selected.");
            //   }

            // pm.showToast(results_str);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void parseJsonWithGson(String str) {
        forecastLow.clear();
        forecastHigh.clear();
        text.clear();
        Gson gson = new Gson();
        pm.L("000" + str);
        Weather weather = gson.fromJson(str, Weather.class);
        pm.L("454545" + weather.getQuery().getResults());
        if (weather.getQuery().getResults() == null) {
            pm.showToast(getResources().getString(R.string.error_no_city_founded));
        } else {
            city_value.setText(weather.getQuery().getResults().getChannel().getLocation().getCity());
            country_value.setText(weather.getQuery().getResults().getChannel().getLocation().getCountry());
            temprature_value = weather.getQuery().getResults().getChannel().getUnits().getTemperature();
            int size = weather.getQuery().getResults().getChannel().getItem().getForecast().size();
            for (int i = 0; i < size; i++) {
                forecastHigh.add(weather.getQuery().getResults().getChannel().getItem().getForecast().get(i).getHigh());
                forecastLow.add(weather.getQuery().getResults().getChannel().getItem().getForecast().get(i).getLow());
                text.add(weather.getQuery().getResults().getChannel().getItem().getForecast().get(i).getText());
            }
            high_value.setText(forecastHigh.get(0).toString() + temprature_value);
            low_value.setText(forecastLow.get(0).toString() + temprature_value);
            text_value.setText(text.get(0).toString());
            pm.L("high" + forecastHigh.get(0));
            pm.L("low" + forecastLow.get(0));
        }


    }

/*    public void getDataAsync(String url) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                pm.L("async_response fail :" + throwable);
            }

            @Override
            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                parseJson(response.toString());
                pm.L("async_response suscces :" + response);
            }
        });


    }*/

    public String getDataFromWeb(String GET_URL) {
        try {
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            pm.L(" 12345 responseCode    " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response.toString());
                return response.toString();
            } else {
                pm.L("12345 webserver_exception not work   ");
            }
        } catch (Exception e) {
            pm.L("12345 webserver_exception : " + e);
        }

        return "empty";
    }

}
