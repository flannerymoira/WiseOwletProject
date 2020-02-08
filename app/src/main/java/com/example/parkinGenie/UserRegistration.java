package com.example.parkinGenie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserRegistration extends AppCompatActivity {


    EditText email, firstName, lastName, password, confirm, telephone;
    Button continuebtn;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);

        db = new DatabaseHelper(this);

        email = findViewById(R.id.txtEmail);
        firstName = findViewById(R.id.txtFName);
        lastName = findViewById(R.id.txtLName);
        password = findViewById(R.id.txtCreatePass);
        confirm = findViewById(R.id.txtConfPass);
        telephone = findViewById(R.id.txtPhone);


        //continue button
        continuebtn = findViewById(R.id.btnContReg);
        continuebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String firstName_S = firstName.getText().toString();
                String lastName_S = lastName.getText().toString();
                String email_S = email.getText().toString();
                String password_S = password.getText().toString();
                String confirm_S = confirm.getText().toString();
                String telephone_S = telephone.getText().toString();
                String role_S = "Customer";

                if (email_S.equals("") || password_S.equals("") || confirm_S.equals(""))
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
                else {
                    if (password_S.equals(confirm_S)) {
                        Boolean checkEmail = db.checkEmail(email_S);
                        if (checkEmail) {
                            Boolean insert = db.insert(firstName_S, lastName_S, email_S, password_S, telephone_S, role_S);
                            if (insert) {
                                Toast.makeText(getApplicationContext(), "Account Registered!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Account already Exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}





