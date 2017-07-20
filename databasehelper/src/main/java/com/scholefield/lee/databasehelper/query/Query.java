package com.scholefield.lee.databasehelper.query;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Base class for a query.
 */
class Query {

    @NonNull private final String table;
    @Nullable private final Selection where;

    /**
     * Package-private constructor.
     *
     * @param table table name to query.
     * @param where contains SQL where clause
     */
    Query(@NonNull String table, @Nullable Selection where) {
        this.table = table;
        this.where = where;
    }

    @NonNull
    public String getTable() {
        return table;
    }

    @Nullable
    public Selection getSelection() {return where;}
}
