package com.scholefield.lee.databasehelper;

import android.content.ContentValues;

/**
 * Converts {@code T} into {@code ContentValues} so it can be saved in the database.
 *
 * Example:
 * <pre>
 * {@code
 *      ContentValues write(Event e) {
 *          ContentValues cv = new ContentValues(2);
 *          cv.put("name", e.getName());
 *          cv.put("time", e.getTime());
 *          return cv;
 *      }
 * }
 * </pre>
 */
public interface DataWriter<T> {

    /**
     * Converts {@code data} to ContentValues.
     */
    ContentValues write(T data);
}
