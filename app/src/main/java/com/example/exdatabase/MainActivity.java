package com.example.exdatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;

    ArrayList userList,addressList,phoneNumberList,id;
    MyDatabase myDatabase;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.floatingButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddingUser.class);
                startActivity(intent);

            }
        });


        myDatabase = new MyDatabase(getApplicationContext());
        userList = new ArrayList();
        addressList = new ArrayList();
        phoneNumberList = new ArrayList();
        id = new ArrayList();
        recyclerView = findViewById(R.id.recycleView);
        displayData();
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,this,userList,addressList,phoneNumberList,id);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }


    void displayData(){
        Cursor cursor = myDatabase.readAllData();
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"NO DATA ",Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                userList.add(cursor.getString(1));
                addressList.add(cursor.getString(2));
                phoneNumberList.add(cursor.getString(3));
            }
        }
//        cursor.moveToFirst();
        Log.d("MainActivity", "id size: " + id.size());
        Log.d("MainActivity", "userList size: " + userList.size());
        Log.d("MainActivity", "addressList size: " + addressList.size());
        Log.d("MainActivity", "phoneNumberList size: " + phoneNumberList.size());

    }
}