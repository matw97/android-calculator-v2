package com.example.mateuszwisnik.calculator;

import android.provider.BaseColumns;

final class DatabaseContract {

    private DatabaseContract() {}

    static class DatabaseEntry implements BaseColumns {
        static final String TABLE_NAME = "history";
        static final String COLUMN_NAME_EXPRESSION = "expression";
    }
}
