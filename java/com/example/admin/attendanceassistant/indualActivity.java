package com.example.admin.attendanceassistant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class indualActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button button1;
    SQLiteDatabase db;
    Cursor resultSet,resultSet1 ;
    EditText sna;
    String state[] = null;
    Spinner sp1,sp2,sp3;
    TextView r1,r2,r3,r4;
    ArrayAdapter<CharSequence>   adapter2,adapter3 ;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indual);

      Button  bn1 = (Button) findViewById(R.id.zx);

         final EditText id=(EditText)findViewById(R.id.editText10);
        final TextView r1 =(TextView) findViewById(R.id.textView67);
        final  TextView r2 =(TextView) findViewById(R.id.textView68);
        final TextView r3 =(TextView) findViewById(R.id.textView65);
        final  TextView r4 =(TextView) findViewById(R.id.textView66);

        sp1 = (Spinner) findViewById(R.id.grad);
        sp2 = (Spinner) findViewById(R.id.year);
        sp3 = (Spinner) findViewById(R.id.spd3);

        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS attendance(sname VARCHAR,graduation VARCHAR,year VARCHAR,period INTEGER ,subject VARCHAR,att_status VARCHAR,att_date INTEGER,att_month INTEGER,att_year INTEGER);");

        sp1.setOnItemSelectedListener(this);

        adapter3 = ArrayAdapter.createFromResource(this, R.array.stsm, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);

        bn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);

                String classSelected_1 = sp1.getSelectedItem().toString();
                String classSelected_2 = sp2.getSelectedItem().toString();
                String classSelected_3 = sp3.getSelectedItem().toString();
                String pass1 = "present";
                String pass2 = "Absent";
               try{
                   Cursor resultSet = db.rawQuery("SELECT COUNT(att_status) FROM attendance WHERE sname='" + id.getText().toString() + "'AND graduation='"+classSelected_1+"' AND year='"+classSelected_2+"' AND att_month='"+classSelected_3+"'  AND att_status='"+pass1+"' ", null);
                   Cursor resultSet1 = db.rawQuery("SELECT COUNT(att_status) FROM attendance WHERE sname='" + id.getText().toString() + "'AND graduation='"+classSelected_1+"' AND year='"+classSelected_2+"' AND att_month='"+classSelected_3+"'  AND att_status='"+pass2+"' ", null);

                   resultSet.moveToFirst();
                   resultSet1.moveToFirst();
                r1.setText(resultSet.getString(0));
                   r2.setText(resultSet1.getString(0));
            }
            catch (Exception e){
                Log.e("Problem", e + " ");
            }
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
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
