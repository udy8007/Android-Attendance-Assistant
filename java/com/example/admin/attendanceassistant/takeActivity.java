package com.example.admin.attendanceassistant;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class takeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button button1;
    String state[] = null;
    private Spinner sp1,sp2,sp3 ;
    ArrayAdapter<CharSequence>   adapter2,adapter3 ;
    public static  final String gradin="com.example.admin.attendanceassistant.gradin";
    public static  final String yearin="com.example.admin.attendanceassistant.yearin";
    public static  final String periodn="com.example.admin.attendanceassistant.periodn";
    public static  final String subjectn="com.example.admin.attendanceassistant.subjectn";
    public static  final String daten="com.example.admin.attendanceassistant.daten";

    EditText sub,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manualtakeatt);
        button1=(Button) findViewById(R.id.c1bt1);
        // Capture button clicks

        sp1 = (Spinner) findViewById(R.id.grad);
        sp2 = (Spinner) findViewById(R.id.year);
        sp3 = (Spinner) findViewById(R.id.spinner);

        sub = (EditText) findViewById(R.id.subeditText);
        date = (EditText) findViewById(R.id.txtdate);

        sp1.setOnItemSelectedListener(this);


        adapter3 = ArrayAdapter.createFromResource(this, R.array.period, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);


        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openactivity2();
            }
        });


    }
    public void onStart(){
        super.onStart();

        final EditText txtDate=(EditText)findViewById(R.id.txtdate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus) {
                    DateDialog dialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }

        });


    }

    private void openactivity2() {

        String classSelected_1 = sp1.getSelectedItem().toString();
        String classSelected_2 = sp2.getSelectedItem().toString();
        String classSelected_3 = sp3.getSelectedItem().toString();


        Intent intent=new Intent(this,loadatt.class);

        intent.putExtra(gradin, classSelected_1);
        intent.putExtra(yearin, classSelected_2);

        intent.putExtra(subjectn, sub.getText().toString());
        intent.putExtra(periodn, classSelected_3);
        intent.putExtra(daten, date.getText().toString());



        startActivity(intent);


        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
