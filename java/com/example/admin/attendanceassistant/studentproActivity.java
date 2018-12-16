package com.example.admin.attendanceassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class studentproActivity extends AppCompatActivity {
    private Button button1,button2;
    TextView abc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudentmain);
        button1=(Button) findViewById(R.id.sbt1);
        button2=(Button) findViewById(R.id.sbt2);

        abc=(TextView) findViewById(R.id.textView5);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome);
        abc.setAnimation(animation);

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
        Intent intent=new Intent(this,studentpro1Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void openactivity2() {
        Intent intent=new Intent(this,studentpro2Activity.class);
    startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
