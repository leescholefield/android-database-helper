package com.scholefield.lee.databasehelper.query;

import android.content.ContentValues;

import java.util.Map;

/**
 * An SQL query for updating a row in the db.
 */
public class UpdateQuery extends Query{

    private Map<String, String> values;

    public UpdateQuery(String table, Map<String, String> values, Selection where) {
        super(table, where);
        this.values = values;
    }

    public Map<String, String> getValues() {
        return values;
    }

    /**
     * Returns the {@code values} map as {@code ContentValues}.
     */
    public ContentValues getValuesAsContentValues() {
        ContentValues cv = new ContentValues();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            cv.put(entry.getKey(), entry.getValue());
        }

        return cv;
    }
}
