package com.sematec.learningproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Farnoosh on 3/7/2016.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    private TextView title;
    private EditText username_edtxt;
    private EditText password_edtxt;
    private EditText repeatPassword_edtxt;
    private PublicMethods publicMethods;

    UserDetails userDetails;

    public void goToLogin() {
        Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        //intentLogin.putExtra("user", PreferenceManager.getDefaultSharedPreferences(this).getString("username", username_edtxt.toString()));
        startActivity(intentLogin);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        publicMethods = new PublicMethods(this);

        title = (TextView) findViewById(R.id.title_register_form);
        username_edtxt = (EditText) findViewById(R.id.username);
        password_edtxt = (EditText) findViewById(R.id.password);
        repeatPassword_edtxt = (EditText) findViewById(R.id.repeatPassword);
        Button btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
        userDetails = new UserDetails(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_register){
            String username_value = username_edtxt.getText().toString();
            String password_value = password_edtxt.getText().toString();
            String repeatPassword_value = repeatPassword_edtxt.getText().toString();
            User userDetailStore = new User(username_value,password_value);

            if(username_value.length() == 0 || password_value.length() == 0 || repeatPassword_value.length() == 0){
                publicMethods.showToast(getResources().getString(R.string.please_fill_form_full));
            } else
            if(!password_value.equals(repeatPassword_value)){
                publicMethods.showToast(getResources().getString(R.string.password_not_match));
            }
            else {
             /*    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("username", userDetailStore.username).commit();
                PreferenceManager.getDefaultSharedPreferences(this).edit().putString("password", userDetailStore.password).commit(); */
                userDetails.userDetailsStore(userDetailStore);
                goToLogin();
            }
        }

    }
}
