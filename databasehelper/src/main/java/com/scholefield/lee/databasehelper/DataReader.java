package com.scholefield.lee.databasehelper;

import android.database.Cursor;

/**
 * Reads a {@code Cursor} returned from the database and converts it to an instance of {@code T}.
 *
 * Example:
 * <pre>
 * {@code
 *      List<Event> read(Cursor c) {
 *          List<Event> events = new ArrayList();
 *          while(c.moveToNext()) {
 *              String name = c.getString(c.getColumnIndex("name"));
 *              long timestamp = c.getLong(c.getColumnIndex("time"));
 *
 *              events.add(new Event(name, timestamp));
 *          }
 *          return events;
 *      }
 * }
 * </pre>
 */
public interface DataReader<T> {

    /**
     * Reads the {@code results} and converts them to an instance of {@code T}.
     *
     * Note, the cursor may be empty.
     */
    T read(Cursor results);
}
