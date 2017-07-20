package com.scholefield.lee.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.scholefield.lee.databasehelper.exceptions.DatabaseException;
import com.scholefield.lee.databasehelper.query.DeleteQuery;
import com.scholefield.lee.databasehelper.query.SearchQuery;
import com.scholefield.lee.databasehelper.query.Selection;
import com.scholefield.lee.databasehelper.query.UpdateQuery;

/**
 * {@link Database} implementation for androids SQLite.
 */
public class SqlDatabase extends SQLiteOpenHelper implements Database<ContentValues, Cursor> {

    private final DbConfig config;

    /**
     * Public constructor.
     *
     * @param context application context.
     * @param config contains configuration info.
     */
    public SqlDatabase(Context context, DbConfig config) {
        super(context, config.getDatabaseName(), null, config.getVersion());
        this.config = config;
    }

    /**
     * Inserts the {@code data} into the given table.
     */
    @Override
    public void insert(String table, ContentValues data) throws DatabaseException {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            db.insertOrThrow(table, null, data);
        } catch (SQLiteException e) {
            throw new DatabaseException("Could not insert values into " + table, e);
        }
    }

    /**
     * Makes a new search query to the database.
     *
     * Note, the caller is responsible for closing the cursor.
     *
     * @param query contains query params.
     */
    @Override
    public Cursor get(SearchQuery query) throws DatabaseException {

        String where = null;
        String[] whereArgs = null;
        Selection selection = query.getSelection();
        if (selection != null) {
            where = selection.getWhereClause();
            whereArgs = selection.getWhereArgs();
        }

        try {
            return execGet(query.getTable(), query.getColumns(), where, whereArgs, query.getLimitAsString());
        } catch (SQLiteException e) {
            throw new DatabaseException("Could not perform the query: ", e);
        }
    }

    /**
     * Private implementation of {@link #get}.
     */
    private Cursor execGet(String table, @Nullable String[] columns, @Nullable String where,
                           @Nullable String[] whereArgs, @Nullable String limit) throws SQLiteException {

        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(table, columns, where, whereArgs, null ,null, null, limit);
    }

    /**
     * Deletes data from the database.
     */
    @Override
    public void delete(DeleteQuery query) throws DatabaseException {
        String where = null;
        String[] whereArgs = null;
        Selection selection = query.getSelection();
        if (selection != null) {
            where = selection.getWhereClause();
            whereArgs = selection.getWhereArgs();
        }

        try {
            execDelete(query.getTable(), where, whereArgs);
        } catch (SQLiteException e) {
            throw new DatabaseException("Could not delete from the table " + query.getTable(), e);
        }
    }

    /**
     * Private implementation of {@link #delete}.
     */
    private void execDelete(String table, String where, String[] whereArgs) throws SQLiteException {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            db.delete(table, where, whereArgs);
        }
    }

    /**
     * Updates the database.
     */
    @Override
    public void update(UpdateQuery query) throws DatabaseException {
        String where = null;
        String[] whereArgs = null;
        Selection selection = query.getSelection();
        if (selection != null) {
            where = selection.getWhereClause();
            whereArgs = selection.getWhereArgs();
        }
        try {
            execUpdate(query.getTable(), query.getValuesAsContentValues(), where, whereArgs);
        } catch (SQLiteException e) {
            throw new DatabaseException("Could not update the table " + query.getTable(), e);
        }
    }

    /**
     * Private implementation of {@link #update}.
     */
    private void execUpdate(String table, ContentValues cv, @Nullable String where, @Nullable String[] whereArgs) throws SQLiteException {
        try (SQLiteDatabase db = this.getWritableDatabase()){
            db.update(table, cv, where, whereArgs);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String s : config.getCreateClauses())
            db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String s : config.getDeleteClauses())
            db.execSQL(s);
        onCreate(db);
    }
}
