package com.scholefield.lee.databasehelperexample.db;

import android.database.Cursor;
import com.scholefield.lee.databasehelper.DataReader;
import com.scholefield.lee.databasehelperexample.Event;


/**
 *
 */
public class EventReader implements DataReader<Event> {

    /**
     * Reads the {@code results} and converts them to an instance of {@code T}.
     */
    @Override
    public Event read(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(Table.Event._ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(Table.Event.COLUMN_TITLE));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(Table.Event.COLUMN_TYPE));
        long ts = cursor.getLong(cursor.getColumnIndexOrThrow(Table.Event.COLUMN_TIMESTAMP));

        return new Event(id, title, type, ts);
    }
}
