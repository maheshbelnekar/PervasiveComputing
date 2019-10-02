package com.projects.maheshbelnekar.pervasiveassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMemo extends AppCompatActivity {

    private EditText titleEditText;
    private EditText memoEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        // Initialize all variables
        titleEditText = findViewById(R.id.title_memo);
        memoEditText = findViewById(R.id.memo_memo);
        saveButton = findViewById(R.id.save_memo);

        // attach listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMemo();
            }
        });
    }

    private Boolean addNewMemo() {

        // Extract values first
        String title = titleEditText.getText().toString();
        String memo = memoEditText.getText().toString();

        Intent addMemoIntent = new Intent(AddMemo.this, Profile.class);
        addMemoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        addMemoIntent.putExtra("title",title);
        addMemoIntent.putExtra("memo",memo);

        startActivity(addMemoIntent);

        return true;
    }
}
