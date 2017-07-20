package com.scholefield.lee.databasehelperexample.db;

import android.content.Context;
import com.scholefield.lee.databasehelper.DbConfig;
import com.scholefield.lee.databasehelper.SqlDatabase;

/**
 *
 */
public class DatabaseHelper {

    private SqlDatabase db;

    public DatabaseHelper(Context context) {
        this.db = new SqlDatabase(context, new Config());
    }

    private static class Config implements DbConfig {
        /**
         * Current Database version. Should be incremented on schema changes.
         */
        @Override
        public int getVersion() {
            return 1;
        }

        /**
         * The name to save the database under.
         */
        @Override
        public String getDatabaseName() {
            return "helper.db";
        }

        /**
         * Array of SQL create table clauses.
         */
        @Override
        public String[] getCreateClauses() {
            return new String[]{Table.Event.SQL_CREATE};
        }

        /**
         * Array of SQL delete table clauses.
         */
        @Override
        public String[] getDeleteClauses() {
            return new String[]{Table.Event.SQL_DELETE};
        }
    }
}
