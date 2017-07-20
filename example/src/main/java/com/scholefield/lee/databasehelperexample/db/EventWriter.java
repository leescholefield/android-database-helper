package com.scholefield.lee.databasehelperexample.db;

import android.content.ContentValues;
import com.scholefield.lee.databasehelper.DataWriter;
import com.scholefield.lee.databasehelperexample.Event;

/**
 *
 */
public class EventWriter implements DataWriter<Event> {

    /**
     * Converts {@code data} to ContentValues.
     */
    @Override
    public ContentValues write(Event data) {
        ContentValues cv = new ContentValues();
        cv.put(Table.Event.COLUMN_TITLE, data.getTitle());
        cv.put(Table.Event.COLUMN_TYPE, data.getType());
        cv.put(Table.Event.COLUMN_TIMESTAMP, data.getTimestamp());

        return cv;
    }
}
