package com.projects.maheshbelnekar.pervasiveassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private ImageView profileImage;
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;
    private Button addMemoButton;
    private ListView memoListListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Todo Initialize SQLite


        // Initialize
        profileImage = findViewById(R.id.image_profile);
        nameTextView = findViewById(R.id.name_profile);
        phoneTextView = findViewById(R.id.phone_profile);
        emailTextView = findViewById(R.id.email_profile);
        addMemoButton = findViewById(R.id.add_memo_profile);
        memoListListView = findViewById(R.id.memo_list_profile);

        populateList();

        addMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMemo();
            }
        });
    }

    private Boolean addNewMemo() {

        Intent addNewMemoIntent = new Intent(Profile.this, AddMemo.class);
        startActivity(addNewMemoIntent);

        return true;
    }

    private Boolean populateList() {

        // Todo: Read from sql database
        return true;
    }
}
