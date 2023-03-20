package com.example.raneenandnemat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_up);
        Button Login = (Button) findViewById(R.id.Login_sign);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        final EditText firstName = (EditText) findViewById (R.id.firstName);
        final EditText lastName = (EditText) findViewById (R.id.lastName);
        final EditText email = (EditText) findViewById (R.id.email_sign);
        final EditText password = (EditText) findViewById (R.id.userpassword);
        final EditText confirmPassword = (EditText) findViewById (R.id.userconfirmPassword);
        final Button signUp = (Button) findViewById (R.id.signUp);

        Spinner spinner = findViewById(R.id.spinner);

// Define the array of items

        String[] items = {"Choose a continent","Asia", "Europe", "Africa", "North America"};

// Create an ArrayAdapter using the array of items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

// Set the adapter for the spinner
        spinner.setAdapter(adapter);




        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()){
                    email.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast toast =Toast.makeText(SignUpActivity.this, "Email is required ",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    email.setBackgroundColor(getResources().getColor(R.color.white));
                }
                if(firstName.getText().toString().isEmpty()){
                    firstName.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast toast =Toast.makeText(SignUpActivity.this, "First Name is required ",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    firstName.setBackgroundColor(getResources().getColor(R.color.white));
                }

                if(lastName.getText().toString().isEmpty()){
                    lastName.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast toast =Toast.makeText(SignUpActivity.this, "Last Name is required ",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    lastName.setBackgroundColor(getResources().getColor(R.color.white));
                }
                if(password.getText().toString().isEmpty()){
                    password.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast toast =Toast.makeText(SignUpActivity.this, "Password is required ",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    password.setBackgroundColor(getResources().getColor(R.color.white));
                }
                if(confirmPassword.getText().toString().isEmpty()){
                    confirmPassword.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast toast =Toast.makeText(SignUpActivity.this, "Confirm Password is required ",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    confirmPassword.setBackgroundColor(getResources().getColor(R.color.white));
                }
                if(spinner.getSelectedItem().toString()=="Choose a continent"){
                    spinner.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast toast =Toast.makeText(SignUpActivity.this, "continent is required ",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    spinner.setBackgroundColor(getResources().getColor(R.color.white));
                }


                if(email.getText().toString().isEmpty() || firstName.getText().toString().isEmpty() ||
                        lastName.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                        confirmPassword.getText().toString().isEmpty()){
                    Toast toast =Toast.makeText(SignUpActivity.this, "Check Input Fields  you miss some of them",Toast.LENGTH_LONG);
                    toast.show();

                }
                else if(!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    Toast toast =Toast.makeText(SignUpActivity.this, "Email is not Valid",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(firstName.getText().toString().length() < 3 || lastName.getText().toString().length() < 3 || firstName.getText().toString().length() >20 || lastName.getText().toString().length() > 20 ){
                    Toast toast =Toast.makeText(SignUpActivity.this, "Name can't be less than 3 characters and not more 20 characters",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(firstName.getText().toString().length() < 3 || lastName.getText().toString().length() < 3 || firstName.getText().toString().length() >20 || lastName.getText().toString().length() > 20 ){
                    Toast toast =Toast.makeText(SignUpActivity.this, "Name can't be less than 3 characters and not more 20 characters",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(password.getText().toString().length() < 8 || confirmPassword.getText().toString().length() < 8 || password.getText().toString().length() >15 || confirmPassword.getText().toString().length() > 15 ){
                    Toast toast =Toast.makeText(SignUpActivity.this, "Password can't be less than 3 characters and not more 15 characters",Toast.LENGTH_LONG);
                    toast.show();
                }


                else if(!password.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast toast =Toast.makeText(SignUpActivity.this, "Passwords must match",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(!Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}$").matcher(password.getText().toString()).matches()){
                    Toast toast =Toast.makeText(SignUpActivity.this, "password should include capital and small characters, number and special char",Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    DataBaseHelper dataBaseHelper =new DataBaseHelper(SignUpActivity.this,"RaneenAndNemat",null,1);
                    dataBaseHelper.AddUsers(email.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),
                            password.getText().toString(), (String) spinner.getSelectedItem());

                    Toast toast =Toast.makeText(SignUpActivity.this, "Successfully Registered",Toast.LENGTH_LONG);
                    toast.show();

                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));

                }
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                SignUpActivity.this.startActivity(intent);
                finish();
            }
        });
    }



}
