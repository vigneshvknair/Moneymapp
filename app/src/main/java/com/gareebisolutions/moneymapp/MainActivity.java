package com.gareebisolutions.moneymapp;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Expense ex;
    EditText editAmount,editIncome;
    Button btnAddData;
    Button btnGoto;
    Button btnViewIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

       // editAmount = (EditText) findViewById(R.id.editAmount);
       // editIncome = (EditText) findViewById(R.id.editIncome);
        btnAddData = (Button) findViewById(R.id.updateBtn);
        btnGoto = (Button) findViewById(R.id.buttonshift);
       // btnViewExpense = (Button) findViewById(R.id.viewExpense);
        //btnViewIncome = (Button) findViewById(R.id.viewIncome);

        AddExpense();
        gotoExpense();
        //viewAll();
    }

    public void AddExpense() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData("expense", "2011-10-05", 2, Integer.parseInt(editAmount.getText().toString()));
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void gotoExpense(){
        btnGoto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getApplicationContext(),Expense.class);
                        startActivity(i);

                    }
                }
        );
    }

    /*public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res= myDb.getData();
                        if(res.getCount()==0){
                            showMessage("Error","Nodatafound");
                            return;
                        }
                        StringBuffer buffer= new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID: "+ res.getString(0)+"\n");
                            buffer.append("Name: "+ res.getString(1)+"\n");
                            buffer.append("Surname: "+ res.getString(2)+"\n");
                            buffer.append("Marks: "+ res.getString(3)+"\n\n");
                        }

                        showMessage("data",buffer.toString());

                    }
                }
        );
    }
*/
    public void showMessage(String title,String Message)

    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}