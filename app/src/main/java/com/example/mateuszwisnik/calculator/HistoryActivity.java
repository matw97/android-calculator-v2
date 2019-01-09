package com.example.mateuszwisnik.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {

    private DatabaseWrapper databaseWrapper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button buttonClearHistory = findViewById(R.id.buttonClearHistory);
        listView = findViewById(R.id.ListView);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        databaseWrapper = new DatabaseWrapper(databaseHelper);

        populateListView();

        buttonClearHistory.setOnClickListener(view -> {
            databaseWrapper.delete();
            populateListView();
        });
    }

    private void populateListView() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.row, databaseWrapper.select());
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onDestroy() {
        databaseWrapper.closeConnection();
        super.onDestroy();
    }
}
