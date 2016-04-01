package com.gareebisolutions.moneymapp;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editAmount,editIncome;
    Button btnViewExpense;
    Button btnExpenseGoto;
    Button btntest;
    Button btnIncomeGoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

       // editAmount = (EditText) findViewById(R.id.editAmount);
       // editIncome = (EditText) findViewById(R.id.editIncome);
      //  btnAddData = (Button) findViewById(R.id.updateBtn);
        btnExpenseGoto = (Button) findViewById(R.id.buttonexpense);
        btnIncomeGoto = (Button) findViewById(R.id.buttonincome);

      //  btntest =(Button) findViewById(R.id.viewAll);
       btnViewExpense = (Button) findViewById(R.id.buttonexpenseview);
        //btnViewIncome = (Button) findViewById(R.id.viewIncome);
        submit();
        gotoExpense();
        gotoIncome();
        viewAll();
    }



    public void gotoExpense(){
        btnExpenseGoto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getApplicationContext(), Expense.class);
                        startActivity(i);

                    }
                }
        );
    }

    public void gotoIncome(){
        btnIncomeGoto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent x = new Intent(getApplicationContext(), Income.class);
                        startActivity(x);

                    }
                }
        );
    }
    public void submit()
    {


    }
    public void viewAll(){
        btnViewExpense.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent z = new Intent(getApplicationContext(), Viewer.class);
                        startActivity(z);

                    }
                }
        );
    }

    public void showMessage(String title,String Message)

    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}