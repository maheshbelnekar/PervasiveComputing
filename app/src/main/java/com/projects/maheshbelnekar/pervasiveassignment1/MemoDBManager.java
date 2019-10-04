package com.projects.maheshbelnekar.pervasiveassignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MemoDBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public MemoDBManager(Context c) {
        context = c;
    }

    public MemoDBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String title, String content) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, title);
        contentValue.put(DatabaseHelper.CONTENT, content);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TITLE, DatabaseHelper.CONTENT };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public Cursor fetchUsingTitle(String title) {
            String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TITLE, DatabaseHelper.CONTENT };
            Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, DatabaseHelper.TITLE + " =?", new String[] {title}, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        }

    public int update(long _id, String title, String content) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TITLE, title);
        contentValues.put(DatabaseHelper.CONTENT, content);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public int updateUsingTitle(String title, String content) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CONTENT, content);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.TITLE + " =?", new String[] {title});
        return i;
    }



    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public List<Memo> getMemoList() {
        Cursor cursor = fetch();

        List<Memo> memoList = new ArrayList<>();

        String title, content;
        do {
            title = cursor.getString(1);
            content = cursor.getString(2);

            memoList.add(new Memo(title, content));
        }while (cursor.moveToNext());

        return memoList;
    }

    public Memo getMemoDetailsUsingTitle(String title) {
        Cursor cursor = fetchUsingTitle(title);

        String content = cursor.getString(2);

        return new Memo(title, content);
    }

    public List<String> getMemoTitleList() {
        Cursor cursor = fetch();

        List<String> memoTitleList = new ArrayList<>();

        String title, content;
        do {
            title = cursor.getString(1);

            memoTitleList.add(title);
        }while (cursor.moveToNext());

        return memoTitleList;
    }
}
