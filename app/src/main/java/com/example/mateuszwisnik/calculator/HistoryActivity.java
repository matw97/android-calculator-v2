package com.example.mateuszwisnik.calculator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button buttonClearHistory = findViewById(R.id.buttonClearHistory);
        final ListView listView = findViewById(R.id.ListView);

        final DatabaseHelper mDbHelper = new DatabaseHelper(getBaseContext());
        final SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.DatabaseEntry.COLUMN_NAME_EXPRESSION,
        };

        Cursor cursor = db.query(
                DatabaseContract.DatabaseEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        final List historyList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String expression = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_EXPRESSION));
            historyList.add(expression);
        }
        cursor.close();

        adapter = new ArrayAdapter<String>(this,R.layout.row, historyList);

        listView.setAdapter(adapter);

        buttonClearHistory.setOnClickListener(v -> db.delete(DatabaseContract.DatabaseEntry.TABLE_NAME, null, null));
    }

    @Override
    protected void onDestroy() {
        DatabaseHelper mDbHelper = new DatabaseHelper(getBaseContext());
        mDbHelper.close();
        super.onDestroy();
    }
}
