package com.example.mateuszwisnik.calculator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseWrapper {
    private DatabaseHelper databaseHelper;

    DatabaseWrapper(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    void insert(String expression) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_EXPRESSION, expression + "=" + Utils.calculate(expression));
        sqLiteDatabase.insert(DatabaseContract.DatabaseEntry.TABLE_NAME, null, contentValues);
    }

    public List select() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                DatabaseContract.DatabaseEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        final List expressions = new ArrayList<>();

        while(cursor.moveToNext()) {
            String expression = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_EXPRESSION));
            expressions.add(expression);
        }
        cursor.close();

        return expressions;
    }

    void delete() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        sqLiteDatabase.delete(DatabaseContract.DatabaseEntry.TABLE_NAME, null, null);
    }

    void closeConnection() {
        databaseHelper.close();
    }
}
