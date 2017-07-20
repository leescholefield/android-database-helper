package com.scholefield.lee.databasehelper.query;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An SQL query for deleting a row(s) from the database.
 */
public class DeleteQuery extends Query {

    public DeleteQuery(@NonNull String table, @Nullable Selection selection) {
        super(table, selection);
    }
}
