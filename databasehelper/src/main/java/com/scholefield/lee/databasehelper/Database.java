package com.scholefield.lee.databasehelper;

import com.scholefield.lee.databasehelper.exceptions.DatabaseException;
import com.scholefield.lee.databasehelper.query.DeleteQuery;
import com.scholefield.lee.databasehelper.query.SearchQuery;
import com.scholefield.lee.databasehelper.query.UpdateQuery;

/**
 * Defines the database contract.
 *
 * @param <T1> the type of data that can be inserted into the db.
 * @param <T2> the type of data that is returned from the database.
 */
public interface Database<T1, T2> {

    /**
     * Inserts the {@code data} into the given table.
     */
    void insert(String table, T1 data) throws DatabaseException;

    /**
     * Makes a new search query to the database.
     *
     * @param query contains query params.
     */
    T2 get(SearchQuery query) throws DatabaseException;

    /**
     * Deletes data from the database.
     */
    void delete(DeleteQuery query) throws DatabaseException;

    /**
     * Updates a specific row in the database.
     */
    void update(UpdateQuery query) throws DatabaseException;
}
