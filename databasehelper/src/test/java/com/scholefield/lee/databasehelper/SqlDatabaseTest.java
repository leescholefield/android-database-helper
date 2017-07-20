package com.scholefield.lee.databasehelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.scholefield.lee.databasehelper.exceptions.DatabaseException;
import com.scholefield.lee.databasehelper.query.DeleteQuery;
import com.scholefield.lee.databasehelper.query.SearchQuery;
import com.scholefield.lee.databasehelper.query.Selection;
import com.scholefield.lee.databasehelper.query.UpdateQuery;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class SqlDatabaseTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private SqlDatabase databaseObj = new SqlDatabase(RuntimeEnvironment.application, new DbTestConfig());

    @Test
    public void testInsert() throws Exception {
        ContentValues cv = new ContentValues(2);
        cv.put("name", "jack");
        cv.put("dob", "11/07/1995");

        databaseObj.insert("simple_test", cv);

        SQLiteDatabase db = databaseObj.getReadableDatabase();
        Cursor c = db.rawQuery("select * from simple_test", null);

        assertEquals(1, c.getCount());
        c.moveToFirst();
        assertEquals("jack", c.getString(c.getColumnIndexOrThrow("name")));
        assertEquals("11/07/1995", c.getString(c.getColumnIndexOrThrow("dob")));
        c.close();
    }

    @Test
    public void testGet() throws Exception {
        SqlDatabase database = populateDatabase(databaseObj);
        // gets "john" record
        SearchQuery searchQuery = new SearchQuery("simple_test", null, createSelection("name", "john"), SearchQuery.NO_LIMIT);

        Cursor cursor = database.get(searchQuery);

        assertEquals(1, cursor.getCount());
        cursor.moveToFirst();
        assertEquals("john", cursor.getString(cursor.getColumnIndexOrThrow("name")));
        assertEquals("15/02/1991", cursor.getString(cursor.getColumnIndexOrThrow("dob")));
    }

    @Test
    public void testDelete() throws Exception {
        SqlDatabase database = populateDatabase(databaseObj);
        // delete "jack" entry
        DeleteQuery query = new DeleteQuery("simple_test", createSelection("name", "jack"));

        database.delete(query);

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM simple_test", null);

        assertEquals(1, c.getCount());
        c.close();
    }

    @Test
    public void testDeleteAll() throws Exception {
        SqlDatabase database = populateDatabase(databaseObj);

        DeleteQuery deleteAll = new DeleteQuery("simple_test", null);

        database.delete(deleteAll);

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM simple_test", null);

        assertEquals(0, c.getCount());
        c.close();
    }

    @Test
    public void testGetInvalidTableThrows() throws Exception {
        expectedException.expect(DatabaseException.class);
        databaseObj.get(new SearchQuery("invalid table", null,
                null, SearchQuery.NO_LIMIT));
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String, String> values = new HashMap<>();
        values.put("name", "john");
        SqlDatabase database = populateDatabase(databaseObj);
        UpdateQuery query = new UpdateQuery("simple_test", values,createSelection("name", "jack"));

        database.update(query);

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM simple_test", null);

        c.moveToFirst();
        assertEquals("john", c.getString(c.getColumnIndex("name")));
        c.close();

    }

    private SqlDatabase populateDatabase(SqlDatabase database) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues first = new ContentValues(2);
        first.put("name", "jack");
        first.put("dob", "11/07/1995");

        ContentValues second = new ContentValues(2);
        second.put("name", "john");
        second.put("dob", "15/02/1991");

        db.insertOrThrow("simple_test", null, first);
        db.insertOrThrow("simple_test", null, second);
        db.close();

        return database;
    }

    private Selection createSelection(String column, String value) {
        return new Selection(column + " =?", new String[]{value});
    }

    private static class DbTestConfig implements DbConfig {
        @Override
        public int getVersion() {
            return 1;
        }

        @Override
        public String getDatabaseName() {
            return "test.db";
        }

        @Override
        public String[] getCreateClauses() {
            return new String[]{"CREATE TABLE simple_test (name TEXT, dob TEXT)"};
        }

        @Override
        public String[] getDeleteClauses() {
            return new String[]{"DROP TABLE IF EXISTS simple_test"};
        }
    }
}