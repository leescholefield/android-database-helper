package com.scholefield.lee.databasehelper.query;

import android.content.ContentValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests the {@link UpdateQuery} class.
 *
 * This uses Robolectric to read the content values so it will be slower.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class UpdateQueryTest {

    @Test
    public void testGetAsContentValues() throws Exception {
        Map<String, String> values = new HashMap<>(); values.put("name", "jack"); values.put("dob", "11/07/1997");
        UpdateQuery query = new UpdateQuery("table", values, null);

        ContentValues cv = query.getValuesAsContentValues();

        assertEquals(2, cv.size());
        assertEquals("jack", cv.getAsString("name"));
        assertEquals("11/07/1997", cv.getAsString("dob"));
    }

}