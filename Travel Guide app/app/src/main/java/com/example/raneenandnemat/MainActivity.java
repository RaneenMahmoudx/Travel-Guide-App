package com.example.raneenandnemat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signUp = (Button) findViewById(R.id.SignUp);
        Button LogIn = (Button) findViewById(R.id.Login);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
        CheckBox checkBox = (CheckBox) findViewById(R.id.check);

        sharedPrefManager = SharedPrefManager.getInstance(this);
        if (!sharedPrefManager.readString("userName", "NOVALUE").equals("NOVALUE")) {
            email.setText(sharedPrefManager.readString("userName", "NOVALUE"));
            password.setText(sharedPrefManager.readString("password","NOVALUE"));
            checkBox.setChecked(true);
        }




        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(MainActivity.this, "Wrong Email or Password... Try Again", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this, "RaneenAndNemat", null, 1);
                    boolean isValid = dataBaseHelper.LogIn(email.getText().toString(), password.getText().toString());
                    if (isValid) {
                        if (checkBox.isChecked()) {
                            sharedPrefManager.writeString("userName", email.getText().toString());
                            sharedPrefManager.writeString("password", password.getText().toString());
                            Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

                        }


                        Toast toast = Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                        MainActivity.this.startActivity(intent);
                        OpenMainPage(email.getText().toString());
                        finish();


                    } else {

                        Toast toast = Toast.makeText(MainActivity.this, "Check Your Email or Password", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });

    }
    public void OpenMainPage(String email) {
        Intent Intent = new Intent(MainActivity.this, MainApp_Activity.class);
        startActivity(Intent);
    }
}