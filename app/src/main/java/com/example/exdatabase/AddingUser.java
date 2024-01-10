package com.example.exdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddingUser extends AppCompatActivity {
    EditText userText ;
    EditText addressText ;
    EditText phoneText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_user);

        userText = findViewById(R.id.userEditText);
        addressText = findViewById(R.id.addressEditText);
        phoneText = findViewById(R.id.phoneEditText);

        MyDatabase db = new MyDatabase(getApplicationContext());
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addUser(userText.getText().toString(),addressText.getText().toString(),phoneText.getText().toString());
            }
        });
    }
}