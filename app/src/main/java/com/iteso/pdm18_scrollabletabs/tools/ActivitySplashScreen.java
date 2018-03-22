package com.iteso.pdm18_scrollabletabs.tools;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.pdm18_scrollabletabs.ActivityMain;
import com.iteso.pdm18_scrollabletabs.R;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.beans.User;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.database.StoreControl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {
     DataBaseHandler dataBaseHandler;
     ArrayList<Store> stores;
     StoreControl storeControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                User user=loadUser();
                Intent intent;
                if(user.isLogged()){
                    intent=new Intent(ActivitySplashScreen.this,
                            ActivityMain.class);
                }else{
                    intent=new Intent(ActivitySplashScreen.this,
                            ActivityLogIn.class);
                }
                startActivity(intent);
                finish();
            }
        };
        Timer timer=new Timer();
        timer.schedule(task,2000);
        dataBaseHandler=DataBaseHandler.getInstance(getApplicationContext());
        if(storeControl.getStores(dataBaseHandler).isEmpty()){
            Store s=new Store(0,"Bestbuy","3310156716",R.drawable.mac,1.5,1.5, new City(0,"Guadalajara"));
            storeControl.addStore(s,dataBaseHandler);
            s=new Store(1,"RadioS","3310156715",R.drawable.alienware,2.5,2.5, new City(1,"CDMX"));
            storeControl.addStore(s,dataBaseHandler);
            s=new Store(2,"Bestbuy","3810156716",R.drawable.mac,3.5,3.5, new City(2,"Aguascalientes"));
            storeControl.addStore(s,dataBaseHandler);
        }
        stores=storeControl.getStores(dataBaseHandler);
    }
    public User loadUser(){
        SharedPreferences sharedPreferences=getSharedPreferences("iteso.com.USER_PREFERENCES",MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("NAME", null));
        user.setPassword(sharedPreferences.getString("PWD", null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        sharedPreferences=null;
        return user;

    }
}

