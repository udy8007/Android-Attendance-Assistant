package com.example.admin.attendanceassistant;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class classwise2 extends AppCompatActivity  {
    private Button button1,button2,button3;
    ArrayList<String>namelist;

    ArrayList<String> percen;
    SQLiteDatabase db;
    String state[] = null;

    public static  final String g="com.example.admin.attendanceassistant.g";
    public static  final String y="com.example.admin.attendanceassistant.y";
    public static  final String m="com.example.admin.attendanceassistant.m";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classwise2);

        button1=(Button) findViewById(R.id.c2bt1);
        button2=(Button) findViewById(R.id.button);
        button3=(Button) findViewById(R.id.button2);

        ArrayList<String> namelist = new ArrayList<String>();
        ArrayList<String> percen = new ArrayList<String>();

        ListView ch1 = (ListView) findViewById(R.id.listView);
        ListView ch2 = (ListView) findViewById(R.id.listView2);
        Intent intent = getIntent();

        final String grad = intent.getStringExtra(classwise.gradin);
        final String year = intent.getStringExtra(classwise.yearin);
        final String date = intent.getStringExtra(classwise.daten);

        namelist.clear();
        percen.clear();

        try {
            db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS attendance(sname VARCHAR,graduation VARCHAR,year VARCHAR,period INTEGER ,subject VARCHAR,att_status VARCHAR,att_date INTEGER,att_month INTEGER,att_year INTEGER);");


        } catch (Exception e) {
            Toast.makeText(this, "Error Occured for create table", Toast.LENGTH_LONG).show();
        }

        Cursor c2 = db.rawQuery("SELECT sname,count(att_status)*3.3 FROM attendance WHERE att_status='present' AND graduation='"+grad+"' AND att_month='"+date+"' AND year='"+year+"' Group By sname  ", null);
        if (c2.getCount() == 0) {
            Toast.makeText(this, "Database was empty", Toast.LENGTH_LONG).show();
        } else {

            while (c2.moveToNext()) {

                namelist.add(c2.getString(0));
                percen.add(c2.getString(1));

            }
        }

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.classiteam1 , R.id.textView72, namelist );
        ArrayAdapter<String> bb = new ArrayAdapter<String>(this, R.layout.classiteam2 , R.id.textView69,  percen);

        ch1.setAdapter(aa);
        ch2.setAdapter(bb);

        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(classwise2.this,above_class.class);

                intent.putExtra(g,grad);
                intent.putExtra(y,year);
                intent.putExtra(m,date);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(classwise2.this,bello_class.class);

                intent.putExtra(g,grad);
                intent.putExtra(y,year);
                intent.putExtra(m,date);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent=new Intent(classwise2.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });


    }

}