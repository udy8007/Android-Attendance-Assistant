package com.example.admin.attendanceassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class timetableActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    TextView logintext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetablemain);
        button1=(Button) findViewById(R.id.tbt1);
        button2=(Button) findViewById(R.id.tbt2);

        logintext=(TextView) findViewById(R.id.textView3);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome);
        logintext.setAnimation(animation);

        // Capture button clicks
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
        Intent intent=new Intent(this,addtime1.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void openactivity2() {
        Intent intent=new Intent(this,viewtimetableActivity1.class);
    startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
