package com.example.admin.attendanceassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class facultypoActivity extends AppCompatActivity {
    private Button button1,button2;
TextView logintext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstaffmain);

        logintext=(TextView) findViewById(R.id.textView5);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome);
        logintext.setAnimation(animation);

        button1=(Button) findViewById(R.id.sbt1);
        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity1();
            }
        });
        button2=(Button) findViewById(R.id.fbt2);
        // Capture button clicks
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity2();
            }
        });
    }
    private void openactivity1() {
        Intent intent=new Intent(this,faculty1.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void openactivity2() {
        Intent intent=new Intent(this,faculty2.class);
    startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
