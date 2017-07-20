package com.scholefield.lee.databasehelper.exceptions;

/**
 *
 */
public class DatabaseException extends Exception {

    public DatabaseException() {

    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String message, Throwable cause) {
            super(message, cause);
    }
}
