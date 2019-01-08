package com.example.mateuszwisnik.calculator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper = null;
    private static final String[] signs = {"+", "-", "*", "/", "."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button0 = findViewById(R.id.btnZero);
        Button button1 = findViewById(R.id.btnOne);
        Button button2 = findViewById(R.id.btnTwo);
        Button button3 = findViewById(R.id.btnThree);
        Button button4 = findViewById(R.id.btnFour);
        Button button5 = findViewById(R.id.btnFive);
        Button button6 = findViewById(R.id.btnSix);
        Button button7 = findViewById(R.id.btnSeven);
        Button button8 = findViewById(R.id.btnEight);
        Button button9 = findViewById(R.id.btnNine);
        Button buttonC = findViewById(R.id.btnClear);
        Button buttonDot = findViewById(R.id.btnDot);
        Button buttonEval = findViewById(R.id.btnEqual);
        Button buttonMul = findViewById(R.id.btnMultiply);
        Button buttonAdd = findViewById(R.id.btnAdd);
        Button buttonSub = findViewById(R.id.btnSubtract);
        Button buttonDiv = findViewById(R.id.btnDivide);
        Button buttonHistory = findViewById(R.id.btnHistory);
        TextView result = findViewById(R.id.txtScreen);

        databaseHelper = new DatabaseHelper(getBaseContext());

        button0.setOnClickListener(view -> result.setText(result.getText() + "0"));

        button1.setOnClickListener(view -> result.setText(result.getText() + "1"));

        button2.setOnClickListener(view -> result.setText(result.getText() + "2"));

        button3.setOnClickListener(view -> result.setText(result.getText() + "3"));

        button4.setOnClickListener(view -> result.setText(result.getText() + "4"));

        button5.setOnClickListener(view -> result.setText(result.getText() + "5"));

        button6.setOnClickListener(view -> result.setText(result.getText() + "6"));

        button7.setOnClickListener(view -> result.setText(result.getText() + "7"));

        button8.setOnClickListener(view -> result.setText(result.getText() + "8"));

        button9.setOnClickListener(view -> result.setText(result.getText() + "9"));

        buttonDot.setOnClickListener(view -> {
            if(isOperationPossible(result.getText().toString())) {
                result.setText(result.getText() + ".");
            }
        });

        buttonC.setOnClickListener(view -> result.setText(""));

        buttonAdd.setOnClickListener(view -> {
            if(isOperationPossible(result.getText().toString())) {
                result.setText(result.getText() + "+");
            }
        });

        buttonSub.setOnClickListener(view -> {
            if(result.getText().toString().equals("")) {
                result.setText(result.getText() + "-");
            }
            if(isOperationPossible(result.getText().toString())) {
                result.setText(result.getText() + "-");
            }
        });

        buttonMul.setOnClickListener(view -> {
            if(isOperationPossible(result.getText().toString())) {
                result.setText(result.getText() + "*");
            }
        });

        buttonDiv.setOnClickListener(view -> {
            if(isOperationPossible(result.getText().toString())) {
                result.setText(result.getText() + "/");
            }
        });

        buttonEval.setOnClickListener(v -> {
            Expression expression = new Expression(result.getText().toString());
            if(!String.valueOf(expression.calculate()).equals("NaN")) {
                result.setText(String.valueOf(expression.calculate()));

                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_EXPRESSION, result.getText().toString() + "=" + String.valueOf(expression.calculate()));
                db.insert(DatabaseContract.DatabaseEntry.TABLE_NAME, null, values);
            } else {
                result.setText("Invalid operation");
            }
        });

        buttonHistory.setOnClickListener(v -> showHistory());
    }

    private boolean isOperationPossible(String expression) {
        if(!expression.equals(""))
        {
            String match = String.valueOf(expression.charAt(expression.length() - 1));
            return Arrays.stream(signs).noneMatch(match::equals);
        }
        return false;
    }

    private void showHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        databaseHelper.close();
        super.onDestroy();
    }
}




