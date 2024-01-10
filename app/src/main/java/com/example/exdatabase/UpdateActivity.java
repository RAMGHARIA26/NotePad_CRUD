package com.example.exdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {
    EditText userUpdateText,addressUpdate,phoneNumberUpdate;
    String ids,name,adddress,number;
    Button button,deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        userUpdateText = findViewById(R.id.userUpdateEditText);
        addressUpdate = findViewById(R.id.addressUpdateEditText);
        phoneNumberUpdate = findViewById(R.id.phoneUpdateEditText);
        button = findViewById(R.id.updateButon);
        deleteButton = findViewById(R.id.deleteButton);
        getIntentData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            MyDatabase db = new MyDatabase(getApplicationContext());
            db.updateData(ids,userUpdateText.getText().toString(),addressUpdate.getText().toString(),phoneNumberUpdate.getText().toString());
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoguePop();
            }
        });


    }


    void dialoguePop(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(this.name.toUpperCase(Locale.ROOT));
        alertDialog.setMessage("Do you want to DELTE "+this.name +"?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase db = new MyDatabase(getApplicationContext());
                db.deleteRow(ids);
                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.create().show();
    }

    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("address")
        && getIntent().hasExtra("number")){
            // Data leh leya and string jo create kiti ode ch assign krta
            ids = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            adddress = getIntent().getStringExtra("address");
            number = getIntent().getStringExtra("number");

            // now setting up the data

            userUpdateText.setText(name);
            addressUpdate.setText(adddress);
            phoneNumberUpdate.setText(number);
        }else{
            Toast.makeText(getApplicationContext(),"No Data.",Toast.LENGTH_LONG).show();
        }
    }
}
