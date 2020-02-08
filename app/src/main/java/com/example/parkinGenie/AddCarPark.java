package com.example.parkinGenie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

public class AddCarPark extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_park);
        myDb = new DatabaseHelper(this);
    }

    public void createCarPark(View view) {
        EditText editName, editWebsite, editAddress, editPhone,
             editGPS, editTotal, editFree, editHeight, editPayment;

        editName = findViewById(R.id.editText_name);
        editWebsite =  findViewById(R.id.editText_website);
        editAddress =  findViewById(R.id.editText_address);
        editPhone =  findViewById(R.id.editText_phone);
        editGPS =  findViewById(R.id.editText_gps);
        editTotal =  findViewById(R.id.editText_totspaces);
        editFree =  findViewById(R.id.editText_freespaces);
        editHeight = findViewById(R.id.editText_HRestriction);
        editPayment = findViewById(R.id.editText_payment);

        String tempVal = editTotal.getText().toString();
        int total_val = Integer.parseInt(tempVal);

        tempVal = editFree.getText().toString();
        int free_val = Integer.parseInt(tempVal);

        boolean isInserted = myDb.insertCarPark(editName.getText().toString(),
                editWebsite.getText().toString(), editAddress.getText().toString(),
                editPhone.getText().toString(), editGPS.getText().toString(),
                total_val, free_val,
                editHeight.getText().toString(), editPayment.getText().toString());

        if (isInserted)
            Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
    }

}

