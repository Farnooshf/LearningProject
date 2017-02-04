package com.sematec.learningproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Farnoosh on 2/28/2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    //private final String username = "farnoosh";
    //private final String password = "123456";
    private TextView title;
    private EditText username_edtxt;
    private EditText password_edtxt;
    private PublicMethods publicMethods;

    UserDetails userDetails;


    public void goToMain() {
        Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
        intentMain.putExtra("user", PreferenceManager.getDefaultSharedPreferences(this).getString("username", username_edtxt.getText().toString()));
        startActivity(intentMain);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        publicMethods = new PublicMethods(this);

      /*  String saved_username = PreferenceManager.getDefaultSharedPreferences(this).getString("username", "guest");

        if (!saved_username.equals("guest")) {
            goToMain();
        } */

        title = (TextView) findViewById(R.id.title_login_form);
        username_edtxt = (EditText) findViewById(R.id.username);
        password_edtxt = (EditText) findViewById(R.id.password);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);

        title.setTypeface(publicMethods.getTypeFace());
        username_edtxt.setTypeface(publicMethods.getTypeFace());
        password_edtxt.setTypeface(publicMethods.getTypeFace());
        btn_login.setTypeface(publicMethods.getTypeFace());
        btn_register.setTypeface(publicMethods.getTypeFace());


        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        userDetails = new UserDetails(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            String username_value = username_edtxt.getText().toString();
            String password_value = password_edtxt.getText().toString();

            User user = new User(username_value,password_value);


            if (username_value.length() == 0 || password_value.length() == 0) {
                publicMethods.showToast(getResources().getString(R.string.please_fill_form_full));
            } else {
                if (username_value.equals(userDetails.getUserLoginDetail().username) && password_value.equals(userDetails.getUserLoginDetail().password)) {
                    publicMethods.showToast(getResources().getString(R.string.login_successful));

                  /*  PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putString("username", userDetail.username).commit();

                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putString("password", userDetail.password).commit(); */

                    goToMain();

                } else {
                    publicMethods.showToast(getResources().getString(R.string.login_failed));
                }
            }

        }
        if(v.getId() == R.id.btn_register){
            Intent reg_intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(reg_intent);
        }
    }

}
