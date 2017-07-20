package com.scholefield.lee.databasehelper;

import android.content.ContentValues;
import android.database.Cursor;
import com.scholefield.lee.databasehelper.exceptions.DatabaseException;
import com.scholefield.lee.databasehelper.query.SearchQuery;

/**
 * Interface between a SQLite database and the user defined {@code DataReader} and {@code DataWriter}.
 */
public class DataAccessor {

    private final Database<ContentValues, Cursor> database;

    public DataAccessor(Database<ContentValues, Cursor> database) {
        this.database = database;
    }

    /**
     * Uses the {@code writer} to convert the {@code item} into ContentValues and then saves it to the database.
     *
     * @param table table to save to.
     * @param item item to save.
     * @param writer converts the item to ContentValues.
     * @throws DatabaseException if an i/o error occurs.
     */
    public <V> void put(String table, V item, DataWriter<V> writer) throws DatabaseException {
        ContentValues cv = writer.write(item);
        if(cv != null)
            database.insert(table, cv);
    }

    /**
     * Makes a new query to the database and the uses the {@code reader} to convert the returned Cursor into an item of type
     * {@code V}.
     *
     * @param query contains query params.
     * @param reader converts Cursor to an item of type V.
     * @throws DatabaseException if an i/o error occurs.
     */
    public <V> V get(SearchQuery query, DataReader<V> reader) throws DatabaseException {
        Cursor cursor = database.get(query);
        V values = reader.read(cursor);

        if (!cursor.isClosed())
            cursor.close();

        return values;
    }
}
