package com.projects.maheshbelnekar.pervasiveassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private Button sumbitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all variables
        nameEditText = findViewById(R.id.name);
        phoneEditText = findViewById(R.id.phone);
        emailEditText = findViewById(R.id.email);
        sumbitButton = findViewById(R.id.submit);

        // attach listener
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private Boolean loginUser() {
        // Extract values first
        String name,phone,email;
        name = nameEditText.getText().toString();
        phone = phoneEditText.getText().toString();
        email = emailEditText.getText().toString();

        Intent loginIntent = new Intent(MainActivity.this, Profile.class);
        loginIntent.putExtra("name",name);
        loginIntent.putExtra("phone",phone);
        loginIntent.putExtra("email",email);

        startActivity(loginIntent);

        return true;
    }
}
