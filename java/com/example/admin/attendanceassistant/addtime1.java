package com.example.admin.attendanceassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addtime1 extends AppCompatActivity {
    private Button button1;
    TextView logintext;
    EditText id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetableadd);
        button1=(Button) findViewById(R.id.atbt);
    final EditText id=(EditText)  findViewById(R.id.editText12);

        logintext=(TextView) findViewById(R.id.textView3);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        logintext.setAnimation(animation);

        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), addtime2.class);
                intent.putExtra("EXTRA_SESSION_ID",id.getText().toString());
                startActivity(intent);
            }
        });

    }


}
