package com.sematec.learningproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    private String username = "guest";
    private TextView title;
    private PublicMethods publicMethods;
    private ListView action_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PublicMethods publicMethods = new PublicMethods(this);

        if (getIntent().hasExtra("user"))
            username = getIntent().getStringExtra("user");

        title = (TextView) findViewById(R.id.titleusername);
        title.setTypeface(publicMethods.getTypeFace());
        title.setText("Hello " + username);

        ////ListView

        final String[] titles = {
                getResources().getString(R.string.home),
                getResources().getString(R.string.login),
                getResources().getString(R.string.calculator),
                getResources().getString(R.string.logout)
        };

        int[] icons = {
                R.mipmap.home,
                R.mipmap.login,
                R.mipmap.calculator_icon,
                R.mipmap.logout

        };

        action_list = (ListView) findViewById(R.id.action_list);

        AdapterNavigationDrawerItems adapterNavigationDrawerItems = new AdapterNavigationDrawerItems(
                MainActivity.this,
                titles,
                icons
        );
        action_list.setAdapter(adapterNavigationDrawerItems);

        action_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                publicMethods.showToast(titles[position]);
                if(titles[position].equals(getResources().getString(R.string.calculator))){
                    Intent intent = new Intent(MainActivity.this, CalcActivity.class);
                    startActivity(intent);
                }
                if(titles[position].equals(getResources().getString(R.string.login))){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                if(titles[position].equals(getResources().getString(R.string.home))){
                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                    startActivity(intent);
                }
                if(titles[position].equals(getResources().getString(R.string.logout))){
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    sharedPreferences.edit().clear().commit();
                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                    startActivity(intent);
                }


            }
        });
    }


    @Override
    public void onClick(View v) {

    }
}
