package com.example.admin.attendanceassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class viewtimetableActivity1 extends AppCompatActivity {
    private Button button1;
    SQLiteDatabase db;
     EditText id;
    TextView res,res2;
    ArrayAdapter<CharSequence>   adapter1,adapter2 ;
Spinner sp1,sp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        TextView logintext=(TextView) findViewById(R.id.textView3);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        logintext.setAnimation(animation);
        final TextView res=(TextView)findViewById(R.id.textView58);
        final EditText id=(EditText)findViewById(R.id.editText9);
        final TextView res2=(TextView)findViewById(R.id.textView55);
        sp1 = (Spinner) findViewById(R.id.weak);
        sp2 = (Spinner) findViewById(R.id.per);


        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Monday(sid INTEGER,p1 VARCHAR,p2 VARCHAR,p3 VARCHAR,p4 VARCHAR,p5 VARCHAR);");



        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Tuesday(sid INTEGER,p1 VARCHAR,p2 VARCHAR,p3 VARCHAR,p4 VARCHAR,p5 VARCHAR);");

        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Wednesday(sid INTEGER,p1 VARCHAR,p2 VARCHAR,p3 VARCHAR,p4 VARCHAR,p5 VARCHAR);");

        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Thursday(sid INTEGER,p1 VARCHAR,p2 VARCHAR,p3 VARCHAR,p4 VARCHAR,p5 VARCHAR);");

        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Friday(sid INTEGER,p1 VARCHAR,p2 VARCHAR,p3 VARCHAR,p4 VARCHAR,p5 VARCHAR);");

        adapter1 = ArrayAdapter.createFromResource(this, R.array.weakday, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);

        adapter2 = ArrayAdapter.createFromResource(this, R.array.pe, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);



        button1=(Button) findViewById(R.id.vtbt1);

        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 res.setVisibility(View.VISIBLE);
                res2.setVisibility(View.VISIBLE);
                String classSelected_1 = sp1.getSelectedItem().toString();
                String classSelected_2 = sp2.getSelectedItem().toString();


                if(id.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");

                    return;
                }
                Cursor c=db.rawQuery("SELECT "+classSelected_2+"  FROM  "+classSelected_1+" WHERE sid='"+id.getText()+"'", null);

                if(c.getCount()==0)
                {
                    showMessage("Error", "Invalid Rollno");
                }

                else if (c.moveToFirst())
                {
                    res.setText(c.getString(0));
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }





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
        id.setText("");

    }

}
