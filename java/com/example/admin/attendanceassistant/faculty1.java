package com.example.admin.attendanceassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class faculty1 extends AppCompatActivity {


    EditText sname,sid,dept ,msub,address,ph;
    SQLiteDatabase db;
    Button add;
    TextView abc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstaff);
        abc=(TextView) findViewById(R.id.textView5);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        abc.setAnimation(animation);
        sname=(EditText)findViewById(R.id.se1);
        sid=(EditText)findViewById(R.id.se2);
        dept=(EditText)findViewById(R.id.editText3);
        msub=(EditText)findViewById(R.id.editText7);
        address=(EditText)findViewById(R.id.editText8);
        ph=(EditText)findViewById(R.id.editText20);

        add=(Button)findViewById(R.id.button7);

        db=openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS staff (sname VARCHAR,sid INTEGER,dept VARCHAR,majorsub  VARCHAR ,address VARCHAR,phone INTEGER);");

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(sname.getText().toString().trim().length()==0||msub.getText().toString().trim().length()==0||
                        dept.getText().toString().trim().length()==0||
                        sid.getText().toString().trim().length()==0|| ph.getText().toString().trim().length()==0 ||
                        address.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO staff VALUES('"+sname.getText().toString()+"','"+sid.getText().toString()+
                        "','"+dept.getText().toString()+"','"+msub.getText().toString()+"','"+address.getText().toString()+"','"+ph.getText().toString()+"');");
                showMessage("Success", "Record added successfully");
                clearText();



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
    public void clearText()
    {
        sname.setText("");
        sid.setText("");
        address.setText("");
        ph.setText("");
        msub.setText("");
        dept.setText("");
    }


}
