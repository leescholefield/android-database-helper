package com.scholefield.lee.databasehelperexample.db;

import android.provider.BaseColumns;

/**
 * Created by lee on 15/07/17.
 */
public class Table {

    static class Event implements BaseColumns {
        static final String TABLE_NAME = "event.db";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_TYPE = "type";
        static final String COLUMN_TIMESTAMP = "timestamp";

        static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                                            _ID + " INT AUTO_INCREMENT," +
                                            COLUMN_TITLE + " TEXT, " +
                                            COLUMN_TYPE + " TEXT, " +
                                            COLUMN_TIMESTAMP + " INTEGER)";
        static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
