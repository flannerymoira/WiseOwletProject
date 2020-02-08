package com.example.parkinGenie;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkinGenie.Entities.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateBooking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    DatabaseHelper myDb;
    EditText editEmail, editCarReg, editDateOfBooking;
    Button btnAddData,btnAddDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_booking);
        myDb = new DatabaseHelper(this);
    }

    public void getDate(View view) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
    }

    public void addBooking(View view) {
        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editCarReg = (EditText) findViewById(R.id.editTextCarReg);
        btnAddDate = (Button) findViewById(R.id.btnDate);
        btnAddData = (Button) findViewById(R.id.button_add);

        boolean isInserted = myDb.insertBooking(editEmail.getText().toString(),
                editCarReg.getText().toString(),
                btnAddDate.getText().toString());

        if (isInserted = true) {
            Toast.makeText(CreateBooking.this, "Confirmation sent to email", Toast.LENGTH_LONG).show();
            setContentView(R.layout.create_booking);
        }
        else {Toast.makeText(CreateBooking.this, "Incorrect email", Toast.LENGTH_LONG).show();
            setContentView(R.layout.create_booking);}

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);

        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.btnDate);
        textView.setText(currentDateString);

    }
}

