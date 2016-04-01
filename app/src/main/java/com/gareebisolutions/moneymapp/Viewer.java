package com.gareebisolutions.moneymapp;

/**
 * Created by Vigneshvk on 3/28/2016.
 */

import android.app.AlertDialog;
import android.database.Cursor;
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

import org.w3c.dom.Text;

import java.util.Calendar;

public class Viewer extends AppCompatActivity implements
        OnDateSetListener {

    DatabaseHelper myDb;
    private TextView dateTextView;
    public Button expenseAdd;
    public EditText Amount;
    String date = "";
    int datePickerInput;
    TextView dateText1;
    TextView dateText2;
    String date1="";
    String date2="";
    MainActivity x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewer);
        myDb = new DatabaseHelper(this);


        // Find our View instances
        dateText1 = (TextView) findViewById(R.id.datetext1);
        dateText2 = (TextView) findViewById(R.id.datetext2);


        expenseAdd = (Button) findViewById(R.id.expenseAdd);
        Button dateButton1 = (Button) findViewById(R.id.datebutton1);
        Button dateButton2 = (Button) findViewById(R.id.datebutton2);
        Button btnshow = (Button) findViewById(R.id.btnshow);


        Amount = (EditText) findViewById(R.id.editAmt);


        // Show a datepicker when the dateButton is clicked
        dateButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Viewer.this,
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
                datePickerInput = 1;

                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        dateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd2 = DatePickerDialog.newInstance(
                        Viewer.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd2.setThemeDark(true);
                // dpd.vibrate(vibrateDate.isChecked());
                //dpd.dismissOnPause(true);
                //dpd.showYearPickerFirst(showYearFirst.isChecked());
                dpd2.setAccentColor(Color.parseColor("#26c6da"));
                dpd2.setTitle("DatePicker Title");
                datePickerInput = 2;
                dpd2.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        btnshow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res= myDb.getData("expense",date1,date2);
                        if(res.getCount()==0){
                            showMessage("Error","Nodatafound");
                            return;
                        }
                        StringBuffer buffer= new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Date: "+ res.getString(0)+"\n");
                            buffer.append("Category: "+ res.getString(1)+"\n");
                            buffer.append("Expense: "+ res.getString(2)+"\n");
                        }

                        showMessage("data", buffer.toString());

                    }
                }
        );

      
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
        switch (datePickerInput) {
            case 1:
                dateText1.setText(date);
                date1=date;
                break;
            case 2:
                dateText2.setText(date);
                date2=date;
                break;
            default:
                break;


        }
    }


    public void showMessage(String title,String Message)

    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void submit() {

    }




}


