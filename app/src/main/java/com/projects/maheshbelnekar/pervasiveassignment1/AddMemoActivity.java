package com.projects.maheshbelnekar.pervasiveassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMemoActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText memoEditText;
    private Button saveButton;
    private Button editButton;

    private String REQUEST_TYPE;

    private MemoDBManager memoDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        // Initialize all variables
        titleEditText = findViewById(R.id.title_memo);
        memoEditText = findViewById(R.id.memo_memo);
        saveButton = findViewById(R.id.save_memo);
        editButton = findViewById(R.id.edit_memo);

        memoDbManager = new MemoDBManager(this);
        memoDbManager.open();

        Intent intent = getIntent();
        REQUEST_TYPE = intent.getStringExtra("REQUEST_TYPE");

        if (REQUEST_TYPE!=null && REQUEST_TYPE.equals("edit")) {
            editButton.setVisibility(View.VISIBLE);
            titleEditText.setText(intent.getStringExtra("TITLE"));
        }
        else {
            saveButton.setVisibility(View.VISIBLE);
        }

        // attach listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMemo();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMemo();
            }
        });
    }

    private Boolean editMemo() {

        // Extract values first
        String title = titleEditText.getText().toString();
        String memo = memoEditText.getText().toString();

        memoDbManager.updateUsingTitle(title,memo);

        return true;
    }

    private Boolean addNewMemo() {

        // Extract values first
        String title = titleEditText.getText().toString();
        String memo = memoEditText.getText().toString();

        memoDbManager.insert(title,memo);

        Intent addMemoIntent = new Intent(AddMemoActivity.this, ProfileActivity.class);
//        addMemoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(addMemoIntent);

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        memoDbManager.close();
    }
}
