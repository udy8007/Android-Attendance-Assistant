package com.example.admin.attendanceassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class classwise extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button button1;

    private Spinner sp1,sp2,sp3;

    String state[] = null;

    ArrayAdapter<CharSequence> adapter1,adapter2,adapter3;

    public static  final String gradin="com.example.admin.attendanceassistant.gradin";
    public static  final String yearin="com.example.admin.attendanceassistant.yearin";
    public static  final String daten="com.example.admin.attendanceassistant.daten";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classwise);
        button1=(Button) findViewById(R.id.c1bt1);

        sp1=(Spinner) findViewById(R.id.grad);
        sp2=(Spinner) findViewById(R.id.year);
        sp3=(Spinner) findViewById(R.id.cs3);

        sp1.setOnItemSelectedListener(this);

        adapter3 = ArrayAdapter.createFromResource(this, R.array.stsm, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String classSelected_1 = sp1.getSelectedItem().toString();
                String classSelected_2 = sp2.getSelectedItem().toString();
                String classSelected_3 = sp3.getSelectedItem().toString();
                Intent intent=new Intent(classwise.this,classwise2.class);


                intent.putExtra(gradin, classSelected_1);
                intent.putExtra(yearin, classSelected_2);
                intent.putExtra(daten, classSelected_3);

                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
