package com.gareebisolutions.moneymapp;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editExpense,editIncome;
    Button btnAddData;
    Button btnViewExpense;
    Button btnViewIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editExpense = (EditText) findViewById(R.id.editExpense);
        editIncome = (EditText) findViewById(R.id.editIncome);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnViewExpense = (Button) findViewById(R.id.viewExpense);
        btnViewIncome = (Button) findViewById(R.id.viewIncome);

        AddExpense();
        viewAll();
    }

    public void AddExpense() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData("expense",
                                editDate.getText().toString(),
                                editExpense.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll(){
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

    public void showMessage(String title,String Message)

    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}