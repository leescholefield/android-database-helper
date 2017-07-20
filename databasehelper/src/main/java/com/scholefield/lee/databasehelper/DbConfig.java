package com.scholefield.lee.databasehelper;


/**
 * Contains configuration info for a SQL database.
 */
public interface DbConfig {

    /**
     * Current Database version. Should be incremented on schema changes.
     */
    int getVersion();

    /**
     * The name to save the database under.
     */
    String getDatabaseName();

    /**
     * Array of SQL create table clauses.
     */
    String[] getCreateClauses();

    /**
     * Array of SQL delete table clauses.
     */
    String[] getDeleteClauses();
}
