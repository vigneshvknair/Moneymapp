package com.gareebisolutions.moneymapp;

/**
 * Created by Vigneshvk on 3/28/2016.
 */

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class Expense extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener
{

    private TextView timeTextView;
    private TextView dateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetime);

        // Find our View instances
        timeTextView = (TextView) findViewById(R.id.time_textview);
        dateTextView = (TextView) findViewById(R.id.date_textview);
        buttonreact();

        // check if picker mode is specified in Style.xml

    }
        // Show a timepicker when the timeButton is clicked
        public void buttonreact()
{
    Button timeButton = (Button) findViewById(R.id.time_button);
    Button dateButton = (Button) findViewById(R.id.date_button);
    timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        Expense.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                    );
                tpd.vibrate(true);
                tpd.dismissOnPause(true);
               // tpd.enableSeconds(enableSeconds.isChecked());
                    tpd.setAccentColor(Color.parseColor("#64B5F6"));

                    tpd.setTitle("TimePicker");

                   // tpd.setTimeInterval(2, 5, 10);

                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        // Show a datepicker when the dateButton is clicked
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Expense.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.vibrate(true);
                //dpd.dismissOnPause(dismissDate.isChecked());
                //dpd.showYearPickerFirst(showYearFirst.isChecked());
                dpd.vibrate(true);
                dpd.dismissOnPause(true);
                // tpd.enableSeconds(enableSeconds.isChecked());
                dpd.setAccentColor(Color.parseColor("#64B5F6"));

                dpd.setTitle("TimePicker");

                // tpd.setTimeInterval(2, 5, 10);
/*
                if (limitDates.isChecked()) {
                    Calendar[] dates = new Calendar[13];
                    for(int i = -6; i <= 6; i++) {
                        Calendar date = Calendar.getInstance();
                        date.add(Calendar.MONTH, i);
                        dates[i+6] = date;
                    }
                    dpd.setSelectableDays(dates);
                }
                if (highlightDates.isChecked()) {
                    Calendar[] dates = new Calendar[13];
                    for(int i = -6; i <= 6; i++) {
                        Calendar date = Calendar.getInstance();
                        date.add(Calendar.WEEK_OF_YEAR, i);
                        dates[i+6] = date;
                    }
                    dpd.setHighlightedDays(dates);
                }*/
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        TimePickerDialog tpd = (TimePickerDialog) getFragmentManager().findFragmentByTag("Timepickerdialog");
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");

        if(tpd != null) tpd.setOnTimeSetListener(this);
        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String secondString = second < 10 ? "0"+second : ""+second;
        String time = "You picked the following time: "+hourString+"h"+minuteString+"m"+secondString+"s";
        timeTextView.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        dateTextView.setText(date);
    }


}
