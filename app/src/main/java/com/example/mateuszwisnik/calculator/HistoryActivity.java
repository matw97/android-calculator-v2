package com.example.mateuszwisnik.calculator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    DatabaseWrapper databaseWrapper;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button buttonClearHistory = findViewById(R.id.buttonClearHistory);
        listView = findViewById(R.id.ListView);

        databaseHelper = new DatabaseHelper(getBaseContext());
        databaseWrapper = new DatabaseWrapper(databaseHelper);

        populateListView();

        buttonClearHistory.setOnClickListener(view -> {
            databaseWrapper.delete();
            populateListView();
        });
    }

    public void populateListView() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.row, databaseWrapper.select());
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onDestroy() {
        databaseWrapper.closeConnection();
        super.onDestroy();
    }
}
