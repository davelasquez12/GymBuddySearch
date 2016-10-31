package com.example.david.gymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ProfileSettingsActivity extends AppCompatActivity
{
    Spinner campus_dropdown;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        campus_dropdown = (Spinner)findViewById(R.id.campus_choice);
        String[] items = new String[]{"Choose campus", "Brownsville campus", "Edinburg campus"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        campus_dropdown.setAdapter(adapter);

        submitButton = (Button)findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ProfileSettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
