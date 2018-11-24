package com.example.mateuszwisnik.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.*;

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
            String s = "";
            int charRead;

            while ((charRead = inputStreamReader.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            history.setText(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
