package com.projects.maheshbelnekar.pervasiveassignment1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class Profile extends AppCompatActivity {

    private ImageView profileImage;
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;
    private Button addMemoButton;
    private ListView memoListListView;
    private ArrayAdapter adapter;


    private MemoDBManager memoDbManager;

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

        memoDbManager = new MemoDBManager(this);
        memoDbManager.open();
        initializeProfile();

        initializeMemoList();

        addMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMemo();
            }
        });
    }

    private void initializeMemoList() {
        List<String> memoTitleList = memoDbManager.getMemoTitleList();

        adapter = new ArrayAdapter(this, R.layout.memo_item_layout, memoTitleList);

        memoListListView.setAdapter(adapter);
        memoDbManager.close();
    }

    private void initializeProfile() {

        Intent loginIntent = getIntent();

        String name = loginIntent.getStringExtra("name");
        String phone = loginIntent.getStringExtra("phone");
        String email = loginIntent.getStringExtra("email");

        if (name!= null && !name.isEmpty())
            nameTextView.setText(name);
        if (phone != null && !phone.isEmpty())
            phoneTextView.setText(phone);
        if (email!= null && !email.isEmpty())
            emailTextView.setText(email);

    }

    private Boolean addNewMemo() {

        Intent addNewMemoIntent = new Intent(Profile.this, AddMemo.class);
        startActivity(addNewMemoIntent);

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        memoDbManager.close();
    }
}
