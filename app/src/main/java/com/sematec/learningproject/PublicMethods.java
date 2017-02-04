package com.sematec.learningproject;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Farnoosh on 2/26/2016.
 */
public class PublicMethods {
    private Context mContext;

    public PublicMethods(Context mContext) {
        this.mContext = mContext;
    }

    public Typeface getTypeFace(){
        return Typeface.createFromAsset(mContext.getAssets(), "Yekan.ttf");
    }

    public void showToast(String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
    public void L(String str){
        Log.d("Project_debugging" , str);
    }
}
