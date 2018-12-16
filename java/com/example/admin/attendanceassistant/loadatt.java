package com.example.admin.attendanceassistant;


import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class loadatt extends AppCompatActivity {

    ListView listview;
    SQLiteDatabase db;
    int spyear, spmonth, spday;
    int s1, s2;

    ArrayList<String> selectedItems, phlist;
    String grad, year, period, subject, date;
    ArrayList<String> thelist = new ArrayList<String>();

    public static final String alist = "com.example.admin.attendanceassistant.alist";
    public static final String plist = "com.example.admin.attendanceassistant.plist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadatt);


        selectedItems = new ArrayList<String>();
        phlist = new ArrayList<String>();
        Intent intent = getIntent();

        String grad = intent.getStringExtra(takeActivity.gradin);
        String year = intent.getStringExtra(takeActivity.yearin);

        String period = intent.getStringExtra(takeActivity.periodn);
        String subject = intent.getStringExtra(takeActivity.subjectn);
        String date = intent.getStringExtra(takeActivity.daten);


    }

    public void onStart() {
        super.onStart();

        ListView ch1 = (ListView) findViewById(R.id.checkable_list);
        ch1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Intent intent = getIntent();

        String grad = intent.getStringExtra(takeActivity.gradin);
        String year = intent.getStringExtra(takeActivity.yearin);

        try {
            db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS student(sname VARCHAR,regno INTEGER,graduation VARCHAR,year INTEGER,address VARCHAR,phone INTEGER);");


        } catch (Exception e) {
            Toast.makeText(this, "Error Occured for create table", Toast.LENGTH_LONG).show();
        }

        Cursor c = db.rawQuery("SELECT * FROM student WHERE graduation='" + grad + "' AND year='" + year + "' ORDER BY regno  ", null);
        if (c.getCount() == 0) {
            Toast.makeText(this, "Database was empty", Toast.LENGTH_LONG).show();
        } else {

            while (c.moveToNext()) {
                thelist.add(c.getString(0));

            }

            ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.txt_title, thelist);

            ch1.setAdapter(aa);

            ch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // selected item
                    String selectedItem = ((TextView) view).getText().toString();


                    if (selectedItems.contains(selectedItem)) {

                        selectedItems.remove(selectedItem);
                        //remove deselected item from the list of selected items
                    } else {
                        selectedItems.add(selectedItem); //add selected item to the list of selected items
                    }


                }


            });

        }
    }

    public void showSelectedItems(View view) {

        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS attendance(sname VARCHAR,graduation VARCHAR,year VARCHAR,period INTEGER ,subject VARCHAR,att_status VARCHAR,att_date INTEGER,att_month INTEGER,att_year INTEGER);");

        thelist.removeAll(selectedItems);

        Intent intent = getIntent();

        String grad = intent.getStringExtra(takeActivity.gradin);
        String year = intent.getStringExtra(takeActivity.yearin);

        String period = intent.getStringExtra(takeActivity.periodn);
        String subject = intent.getStringExtra(takeActivity.subjectn);
        String date = intent.getStringExtra(takeActivity.daten);

        String[] dateParts = date.split("-");
        spyear = Integer.parseInt(dateParts[2]);
        spmonth = Integer.parseInt(dateParts[1]);
        spday = Integer.parseInt(dateParts[0]);
        int size1=selectedItems.size();
        String pass1 = "present";
        try{
            for (int i = 0; i < size1; ++i) {
                ContentValues cv = new ContentValues();
                cv.put("sname", selectedItems.get(i));
                cv.put(  "graduation",grad);
                cv.put( "year", year);
                cv.put(  "period",period);
                cv.put(  "subject",subject);
                cv.put(   "att_status",pass1);
                cv.put(  "att_date",spday);
                cv.put( " att_month",spmonth);
                cv.put(  "att_year",spyear);

                db.insertOrThrow("attendance", null, cv);
            }
        } catch (Exception e){
            Log.e("Problem", e + " ");
        }

        int size2=thelist.size();
        String pass2 = "Absent";
        try{
            for (int i = 0; i < size2; ++i) {
                ContentValues cv = new ContentValues();
                cv.put("sname", thelist.get(i));
                cv.put(  "graduation",grad);
                cv.put( "year", year);
                cv.put(  "period",period);
                cv.put(  "subject",subject);
                cv.put(   "att_status",pass2);
                cv.put(  "att_date",spday);
                cv.put( " att_month",spmonth);
                cv.put(  "att_year",spyear);

                db.insertOrThrow("attendance", null, cv);
            }
        } catch (Exception e){
            Log.e("Problem", e + " ");
        }


        int size3=thelist.size();

        try {
            for (int i = 0; i < size3; ++i) {
                ContentValues cv = new ContentValues();


                Cursor c = db.rawQuery("SELECT phone FROM student WHERE sname='"+thelist.get(i)+"'   ", null);
                while (c.moveToNext()) {
                        phlist.add(c.getString(0));
                }
            }

        }catch (Exception e){
            Log.e("Problem", e + " ");
        }



        if (thelist.size()>0 ) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are You Sure To Inform Today Absentees");
            builder.setMessage("Confirm To Inform?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    SmsManager sms = SmsManager.getDefault();

                    for (int i = 0; i < phlist.size(); i++) {
                        sms.sendTextMessage(phlist.get(i), null, "Dear Parents,This message from Dr Ambedkar goverment Arts college, Thus your son / daughter today absent in college. Kindly inform reson for us. Thank you", null, null);
                    }
                    Toast.makeText(getApplicationContext(), "Message Sent successfully!", Toast.LENGTH_LONG).show();

                    Toast.makeText(loadatt.this, "Attendance Register Successfully" , Toast.LENGTH_LONG).show();
                    Toast.makeText(loadatt.this, "Today No of Present" + selectedItems.size(), Toast.LENGTH_LONG).show();
                    Toast.makeText(loadatt.this, "Today No of Absent" + thelist.size(), Toast.LENGTH_LONG).show();


                    phlist.clear();
                    thelist.clear();
                    selectedItems.clear();
                    Intent intent=new Intent(loadatt.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {


                    Toast.makeText(loadatt.this, "Attendance Register Successfully" , Toast.LENGTH_LONG).show();
                    Toast.makeText(loadatt.this, "Today No of Present" + selectedItems.size(), Toast.LENGTH_LONG).show();
                    Toast.makeText(loadatt.this, "Today No of Absent" + thelist.size(), Toast.LENGTH_LONG).show();

                    phlist.clear();
                    thelist.clear();
                    selectedItems.clear();
                    Intent intent=new Intent(loadatt.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

        }
         else
        {

            Toast.makeText(this, "Attendance Register Successfully" , Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Today No of Present" + selectedItems.size(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Today No of Absent" + thelist.size(), Toast.LENGTH_LONG).show();


            phlist.clear();
            thelist.clear();
            selectedItems.clear();
             intent=new Intent(loadatt.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }


    }

}







