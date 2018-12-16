package com.example.admin.attendanceassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View;

import java.util.Calendar;


public class alam  extends AppCompatActivity implements View.OnClickListener {
private int notificationId = 1;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
        }

@Override
public void onClick(View view) {

        EditText editText = ( EditText)findViewById(R.id.editText);
        TimePicker timePicker =(TimePicker) findViewById(R.id.timePicker);
        Intent intent = new Intent(alam.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", editText.getText().toString());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(alam.this, 0,
        intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) {

        case R.id.setBtn:
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        // Create time.
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, hour);
        startTime.set(Calendar.MINUTE, minute);
        startTime.set(Calendar.SECOND, 0);
        long alarmStartTime = startTime.getTimeInMillis();

        // Set alarm.
        // set(type, milliseconds, intent)
        alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

        Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
        break;

        case R.id.cancelBtn:
        alarm.cancel(alarmIntent);
        Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
        break;
        }

        }
        }
