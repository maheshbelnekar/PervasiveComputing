package com.projects.maheshbelnekar.pervasiveassignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private String TAG = "Mahesh";


    private MemoDBManager memoDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        storeProfileInfo();

    }

    private Boolean addNewMemo() {

        Intent addNewMemoIntent = new Intent(Profile.this, AddMemo.class);
        startActivity(addNewMemoIntent);

        return true;
    }

    private void storeProfileInfo() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        String name = nameTextView.getText().toString();
        String phone = phoneTextView.getText().toString();
        String email = emailTextView.getText().toString();

        if (name!= null && !name.isEmpty() && !name.equals("Name"))
            editor.putString("PROFILE_NAME",name);

        if (phone != null && !phone.isEmpty() && !phone.equals("Phone Number"))
            editor.putString("PROFILE_PHONE",phone);

        if (email!= null && !email.isEmpty() && !email.equals("Email"))
            editor.putString("PROFILE_EMAIL",email);

        editor.apply();
    }

    private Boolean retrieveProfileInfo() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        String name = sharedPref.getString("PROFILE_NAME","");
        String phone = sharedPref.getString("PROFILE_PHONE","");
        String email = sharedPref.getString("PROFILE_EMAIL","");

        if (name!= null && !name.isEmpty() && !name.equals("Name"))
            nameTextView.setText(name);

        if (phone != null && !phone.isEmpty() && !phone.equals("Phone Number")) {
            phoneTextView.setText(phone);
        }

        if (email!= null && !email.isEmpty() && !email.equals("Email")) {
            emailTextView.setText(email);
        }

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
        storeProfileInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        retrieveProfileInfo();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
        storeProfileInfo();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
        retrieveProfileInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
        initializeProfile();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        memoDbManager.close();
    }
}
