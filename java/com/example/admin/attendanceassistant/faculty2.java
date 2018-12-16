package com.example.admin.attendanceassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class faculty2 extends AppCompatActivity {
    SQLiteDatabase db;
    Button get;
    EditText id;
    TextView r1,r2,r3,r4,r5,r11,r21,r31,r41,r51,abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstaff);
        abc=(TextView) findViewById(R.id.textView5);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        abc.setAnimation(animation);
        db=openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS staff (sname VARCHAR,sid INTEGER,dept VARCHAR,majorsub  VARCHAR ,address VARCHAR,phone INTEGER);");

        final EditText id=(EditText)findViewById(R.id.editText11);;

        final TextView r1= (TextView)findViewById(R.id.ftv11);
        final TextView  r2= (TextView)findViewById(R.id.ftv21);
        final TextView    r3= (TextView)findViewById(R.id.ftv41);
        final TextView  r4= (TextView)findViewById(R.id.textView36);
        final TextView r5= (TextView)findViewById(R.id.textView40);

        final TextView r11= (TextView)findViewById(R.id.ftv1);
        final TextView   r21= (TextView)findViewById(R.id.ftv2);
        final TextView r31= (TextView)findViewById(R.id.ftv3);
        final TextView  r41= (TextView)findViewById(R.id.ftv4);
        final TextView r51= (TextView)findViewById(R.id.ftv5);

        Button get= (Button) findViewById(R.id.button16);

        get.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(id.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    r1.setVisibility(View.INVISIBLE);
                    r2.setVisibility(View.INVISIBLE);
                    r3.setVisibility(View.INVISIBLE);
                    r4.setVisibility(View.INVISIBLE);
                    r5.setVisibility(View.INVISIBLE);
                    r11.setVisibility(View.INVISIBLE);
                    r21.setVisibility(View.INVISIBLE);
                    r31.setVisibility(View.INVISIBLE);
                    r41.setVisibility(View.INVISIBLE);
                    r51.setVisibility(View.INVISIBLE);

                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM staff WHERE sid='"+id.getText()+"'", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "Invalid Rollno");
                }
                else if(c.moveToFirst())
                {
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                    r5.setVisibility(View.VISIBLE);
                    r11.setVisibility(View.VISIBLE);
                    r21.setVisibility(View.VISIBLE);
                    r31.setVisibility(View.VISIBLE);
                    r41.setVisibility(View.VISIBLE);
                    r51.setVisibility(View.VISIBLE);


                    r1.setText(c.getString(0));
                    r2.setText(c.getString(2));
                    r3.setText(c.getString(3));
                    r4.setText(c.getString(4));
                    r5.setText(c.getString(5));

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
