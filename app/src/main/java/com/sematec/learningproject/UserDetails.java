package com.sematec.learningproject;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Farnoosh on 3/7/2016.
 */
public class UserDetails {

    public static final String shared_name = "userDetails";
    SharedPreferences userDetailsLocalStore;

    public UserDetails(Context context){
        userDetailsLocalStore = context.getSharedPreferences(shared_name, 0);
    }

    public void userDetailsStore(User user){
        SharedPreferences.Editor sh_editor = userDetailsLocalStore.edit();
        sh_editor.putString("username", user.username);
        sh_editor.putString("password", user.password);
        sh_editor.commit();
    }

    public User getUserLoginDetail() {
        String username = userDetailsLocalStore.getString("username", "");
        String password = userDetailsLocalStore.getString("password", "");
        User loginUser = new User(username, password);
        return loginUser;
    }

    public void clearUserDetailStore(){
        SharedPreferences.Editor sh_editor = userDetailsLocalStore.edit();
        sh_editor.clear();
        sh_editor.commit();

    }


}
