package com.iteso.pdm18_scrollabletabs.tools;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iteso.pdm18_scrollabletabs.ActivityMain;
import com.iteso.pdm18_scrollabletabs.R;
import com.iteso.pdm18_scrollabletabs.beans.User;

public class ActivityLogIn extends AppCompatActivity {

    EditText name, password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        name=findViewById(R.id.activity_log_in_name);
        password=findViewById(R.id.activity_log_in_pass);
        login=findViewById(R.id.activity_log_in_log);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
                Intent intent=new Intent(ActivityLogIn.this,
                        ActivityMain.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void saveUser(){

        User user = new User();
        user.setName(name.getText().toString());
        user.setPassword(password.getText().toString());
        user.setLogged(true);

        SharedPreferences sharedPreferences=getSharedPreferences("iteso.com.USER_PREFERENCES",
                MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("NAME",name.getText().toString());
        editor.putString("PWD",password.getText().toString());
        editor.putBoolean("LOGGED",true);
        editor.apply();
    }
}
