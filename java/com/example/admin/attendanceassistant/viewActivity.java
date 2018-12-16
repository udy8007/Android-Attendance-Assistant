package com.example.admin.attendanceassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class viewActivity extends AppCompatActivity {
    private Button button1,button2;
TextView logintext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewatten);
        button1=(Button) findViewById(R.id.vbt1);
        button2=(Button) findViewById(R.id.vbt2);
        // Capture button clicks

        logintext=(TextView) findViewById(R.id.textView20);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome);
        logintext.setAnimation(animation);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity2();
            }
        });
    }


    private void openactivity1() {
        Intent intent=new Intent(this,indualActivity.class);
    startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void openactivity2() {
        Intent intent=new Intent(this,classwise.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
