    package com.gareebisolutions.moneymapp;

    /**
     * Created by Vigneshvk on 3/28/2016.
     */

    import android.content.DialogInterface;
    import android.graphics.Color;
    import android.net.Uri;
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
    import java.util.ArrayList;
    import java.util.List;
    import android.widget.AdapterView.OnItemSelectedListener;

    import com.google.android.gms.appindexing.Action;
    import com.google.android.gms.appindexing.AppIndex;
    import com.google.android.gms.common.api.GoogleApiClient;
    import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
    import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.List;

    public class Expense extends AppCompatActivity implements
            OnDateSetListener,
            OnItemSelectedListener {
        private Spinner spinner1;

        MainActivity x;
        DatabaseHelper myDb;
        private TextView dateTextView;
        public Button expenseAdd;
        public EditText Amount;
        String date = "";
        String category;
        String item;



        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.datetime);
            myDb = new DatabaseHelper(this);


            //spinner
            // Spinner element
            Spinner spinner = (Spinner) findViewById(R.id.spinner1);

            // Spinner click listener
            spinner.setOnItemSelectedListener(this);

            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            categories.add("Food");
            categories.add("College");
            categories.add("Online");
            categories.add("Bills");
            categories.add("Personal");
            categories.add("Travel");

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

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
                            Expense.this,
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
                                Toast.makeText(Expense.this, "All the data is not entered!", Toast.LENGTH_LONG).show();
                            } else {
                                boolean isInserted = myDb.insertData("expense", date, item, Integer.parseInt(amt));
                                if (isInserted)
                                    Toast.makeText(Expense.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(Expense.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            );
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }





    }

