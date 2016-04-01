package com.gareebisolutions.moneymapp;

/**
 * Created by Vigneshvk on 3/28/2016.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;
import java.util.Calendar;

public class Income extends AppCompatActivity implements
        OnDateSetListener
         {

    DatabaseHelper myDb;
    private TextView dateTextView;
    public Button expenseAdd;
    public EditText Amount;
    String date = "";
             MainActivity x;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income);
        myDb = new DatabaseHelper(this);


       
        // Find our View instances
        dateTextView = (TextView) findViewById(R.id.date_textview);
        expenseAdd = (Button) findViewById(R.id.expenseAdd);
        Button dateButton = (Button) findViewById(R.id.date_button);
        Amount = (EditText) findViewById(R.id.editAmt);


        // Show a datepicker when the dateButton is clicked
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Income.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(true);
                // dpd.vibrate(vibrateDate.isChecked());
                //dpd.dismissOnPause(true);
                //dpd.showYearPickerFirst(showYearFirst.isChecked());
                dpd.setAccentColor(Color.parseColor("#26c6da"));
                dpd.setTitle("DatePicker Title");
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });


        submit();


    }




        /*
        public void onResume() {
            super.onResume();
            DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");

            if(dpd != null) dpd.setOnDateSetListener(this);
        }*/


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        if (monthOfYear < 10) {
            date = year + "-0" + (monthOfYear);
            if (dayOfMonth < 10) {
                date = date + "-0" + dayOfMonth + " 00:00:00";
            } else {
                date = date + "-" + dayOfMonth + " 00:00:00";
            }
        } else if (dayOfMonth < 10) {
            date = year + "-" + (monthOfYear) + "-0" + dayOfMonth + " 00:00:00";
        } else {
            date = year + "-" + (monthOfYear) + "-" + dayOfMonth + " 00:00:00";
        }

        dateTextView.setText(date);

    }


    public void submit() {
        expenseAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String amt = Amount.getText().toString();
                        if (amt.matches("") || date.matches("")) {
                            Toast.makeText(Income.this, "All the data is not entered!", Toast.LENGTH_LONG).show();
                        } else {
                            boolean isInserted = myDb.insertData("income", date,"none", Integer.parseInt(amt));
                            if (isInserted)
                                Toast.makeText(Income.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Income.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }





}

