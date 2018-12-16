package com.example.admin.attendanceassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
 import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
 import android.widget.TextView;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {
    Button login,cl;
    EditText username,password;
    TextView logintext,abc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        login =(Button)findViewById(R.id.buttonlogin);
        username=(EditText)findViewById(R.id.editTextusername);
        password=(EditText)findViewById(R.id.editTextpassword);

        cl =(Button)findViewById(R.id.button3);
        abc=(TextView) findViewById(R.id.textViewabc);

        logintext=(TextView) findViewById(R.id.animtext);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome);
        logintext.setAnimation(animation);

        Animation anima= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        abc.setAnimation(anima);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(user_name)) {
                        username.setError("Invalid User Name");
                    }
                    else if (TextUtils.isEmpty(pass_word)) {
                        password.setError("enter password");
                    }
                    else {
                        if (user_name.equals("admin") & pass_word.equals("dagac")) {
                            Intent intent = new Intent(MainActivity1.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                            username.setText("");
                            password.setText("");
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }



        });

        cl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



             username.setText("");
                password.setText("");
             }



        });


    }



}