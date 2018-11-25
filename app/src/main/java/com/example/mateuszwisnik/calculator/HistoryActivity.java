package com.example.mateuszwisnik.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView history = findViewById(R.id.historyView);


        try {
            FileInputStream fileInputStream = openFileInput("history.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            char[] inputBuffer = new char[256];
            String result = "";
            int charRead;

            while ((charRead = inputStreamReader.read(inputBuffer)) > 0) {
                String stringRead = String.copyValueOf(inputBuffer, 0, charRead);
                result += stringRead;
            }
            history.setText(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
