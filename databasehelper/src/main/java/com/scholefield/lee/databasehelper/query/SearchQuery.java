package com.scholefield.lee.databasehelper.query;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An SQL query for searching the database.
 */
public class SearchQuery extends Query {

    public static final int NO_LIMIT = -1;

    @Nullable
    private final String[] columns;
    private final int limit;

    /**
     * Public constructor.
     *
     * @param table table to query. Must not be null.
     * @param columns which columns to return. If null this will return all columns.
     * @param selection optional where clause. If null this will return all rows.
     * @param limit how many rows to return. Set to {@link #NO_LIMIT} if you want to return all rows.
     */
    public SearchQuery(@NonNull String table, @Nullable String[] columns, @Nullable Selection selection, int limit) {
        super(table, selection);
        this.columns = columns;
        this.limit = limit;
    }

    @Nullable
    public String[] getColumns() {
        return columns;
    }

    public int getLimit() {
        return limit;
    }

    /**
     * If limit is equal to NO_LIMIT it returns null.
     */
    public String getLimitAsString() {
        if (limit == NO_LIMIT)
            return null;
        return String.valueOf(limit);
    }
}
