package com.example.admin.attendanceassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button1,button2,button3,button4,button5,button6;
    TextView abc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button) findViewById(R.id.bt1);
        button2=(Button) findViewById(R.id.bt2);
        button3=(Button) findViewById(R.id.bt3);
        button4=(Button) findViewById(R.id.bt4);
        button5=(Button) findViewById(R.id.bt5);
        button6=(Button) findViewById(R.id.rmbtn);
        // Capture button clicks
        abc=(TextView) findViewById(R.id.textViewabc);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        abc.setAnimation(animation);

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
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity3();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity4();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity5();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity6();
            }
        });
    }

    private void openactivity1() {
        Intent intent=new Intent(this,maintakeActivity.class);
    startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void openactivity2() {
        Intent intent=new Intent(this,viewActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }private void openactivity3() {
        Intent intent=new Intent(this,studentproActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }private void openactivity4() {
        Intent intent=new Intent(this,facultypoActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }private void openactivity5() {
        Intent intent=new Intent(this,timetableActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openactivity6() {
        Intent intent=new Intent(this,alam.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
