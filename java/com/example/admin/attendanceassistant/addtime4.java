package com.example.admin.attendanceassistant;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addtime4 extends AppCompatActivity {
    private Button button1;
    EditText p1,a,b,c,d,e;
    String id;
    SQLiteDatabase db;
    String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetableadd3);

        button1=(Button) findViewById(R.id.addbt2);
        final EditText p1=(EditText)  findViewById(R.id.editText13);
        TextView logintext=(TextView) findViewById(R.id.textView26);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        logintext.setAnimation(animation);

        final String sessionId= getIntent().getStringExtra("EXTRA_SESSION_ID");
        p1.setText(sessionId);

        final    EditText a=(EditText)findViewById(R.id.editText14);
        final   EditText b=(EditText)findViewById(R.id.editText15);
        final   EditText c=(EditText)findViewById(R.id.editText16);
        final  EditText d=(EditText)findViewById(R.id.editText17);
        final  EditText e=(EditText)findViewById(R.id.editText5);

        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Wednesday(sid INTEGER,p1 VARCHAR,p2 VARCHAR,p3 VARCHAR,p4 VARCHAR,p5 VARCHAR);");

        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(a.getText().toString().trim().length()==0||
                        b.getText().toString().trim().length()==0|| c.getText().toString().trim().length()==0 ||
                        d.getText().toString().trim().length()==0 || e.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO Wednesday VALUES('"+p1.getText().toString()+"','"+a.getText().toString()+
                        "','"+b.getText().toString()+"','"+c.getText().toString()+"','"+d.getText().toString()+"','"+e.getText().toString()+"');");

                Intent intent=new Intent(addtime4.this,addtime5.class);
                intent.putExtra("EXTRA_SESSION_ID",p1.getText().toString());
                startActivity(intent);
                Toast.makeText(addtime4.this, "Wednesday Record Register Successfully" , Toast.LENGTH_SHORT).show();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
