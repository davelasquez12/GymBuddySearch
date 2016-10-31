package com.example.david.gymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity
{
    EditText mUsernameEditText;
    EditText mPasswordEditText;
    TextView errorMessageLogIn;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameEditText = (EditText)findViewById(R.id.username_editText);
        mPasswordEditText = (EditText)findViewById(R.id.password_editText);
        loginButton = (Button)findViewById(R.id.loginbutton);
        errorMessageLogIn = (TextView) findViewById(R.id.error_message_login);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if (username.equals("Admin") && password.equals("12345"))
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    errorMessageLogIn.setVisibility(View.INVISIBLE);
                    finish();
                }
                else
                {
                    errorMessageLogIn.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}
