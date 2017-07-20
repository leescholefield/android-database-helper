package com.scholefield.lee.databasehelper.query;

/**
 * SQL selection statement.
 */
public class Selection {

    private String whereClause;
    private String[] whereArgs;

    /**
     * Constructor.
     *
     * @param whereClause SQL where clause (excluding the 'WHERE' itself).
     * @param whereArgs you may include '?'s in the {@code whereClause} which will be replaced with the values from whereArgs
     *                  in the order that they appear.
     */
    public Selection(String whereClause, String[] whereArgs) {
        this.whereClause = whereClause;
        this.whereArgs = whereArgs;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public String[] getWhereArgs() {
        return whereArgs;
    }
}
