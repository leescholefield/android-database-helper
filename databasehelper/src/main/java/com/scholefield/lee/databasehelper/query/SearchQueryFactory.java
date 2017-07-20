package com.scholefield.lee.databasehelper.query;

import android.support.annotation.NonNull;

/**
 * Factory class for generating {@link SearchQuery}s
 */
public class SearchQueryFactory {

    /**
     * Returns all rows from the given table.
     */
    public static SearchQuery allRows(@NonNull String table) {
        return new SearchQuery(table, null, null, SearchQuery.NO_LIMIT);
    }

    /**
     * Returns all rows from the db that match the given value.
     *
     * @param table table to search.
     * @param column column to search for value.
     * @param value value to search.
     */
    public static SearchQuery forValue(@NonNull String table, @NonNull String column, @NonNull String value) {
        Selection selection = new Selection(column + " =?", new String[]{value});
        return new SearchQuery(table, null, selection, SearchQuery.NO_LIMIT);
    }

}
