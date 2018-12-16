package com.example.admin.attendanceassistant;


import android.app.AlertDialog.Builder;
import android.content.Context;

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


public class studentpro1Activity extends AppCompatActivity   implements AdapterView.OnItemSelectedListener {
    TextView abc;
    EditText sname,reg, address,ph;
    SQLiteDatabase db;
    private Spinner sp1, sp2;
    String state[] = null;
    Button add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);

        sname=(EditText)findViewById(R.id.se1);
        reg=(EditText)findViewById(R.id.se2);
        address=(EditText)findViewById(R.id.se3);
        ph=(EditText)findViewById(R.id.se4);
        add=(Button)findViewById(R.id.sbt1);
        sp1 = (Spinner) findViewById(R.id.as1);
        sp2 = (Spinner) findViewById(R.id.as2);
        abc=(TextView) findViewById(R.id.textView5);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        abc.setAnimation(animation);

        sp1.setOnItemSelectedListener(this);

        db=openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(sname VARCHAR,regno INTEGER,graduation VARCHAR,year INTEGER,address VARCHAR,phone INTEGER);");



        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String classSelected_1 = sp1.getSelectedItem().toString();
                String classSelected_2 = sp2.getSelectedItem().toString();


                if(sname.getText().toString().trim().length()==0||
                        reg.getText().toString().trim().length()==0|| ph.getText().toString().trim().length()==0 ||
                        address.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('"+sname.getText().toString()+"','"+reg.getText().toString()+
                        "','"+classSelected_1+"','"+classSelected_2+"','"+address.getText().toString()+"','"+ph.getText().toString()+"');");
                showMessage("Success", "Record added successfully");



                clearText();



            }



        });



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            state = new String[]{"1", "2", "3"};

        } else {
            state = new String[]{"1", "2"};

        }


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, state);
        sp2.setAdapter(adapter2);
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        sname.setText("");
        reg.setText("");
        address.setText("");
        ph.setText("");
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
