package com.scholefield.lee.databasehelper.query;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class SearchQueryFactoryTest {

    /**
     * Ensures that the {@code Query} produced by {@link SearchQueryFactory#allRows} complies with the contract defined by
     * {@link android.database.sqlite.SQLiteDatabase#query}
     */
    @Test
    public void testAllRows() throws Exception {
        SearchQuery query = SearchQueryFactory.allRows("table name");

        assertNotNull(query.getTable());
        assertNull(query.getSelection());
        assertNull(query.getColumns());
        assertNull(query.getLimitAsString());
    }
}