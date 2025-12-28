package com.minte9.basics.strings;
import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

public class JsonTest {

    @Test public void json() {

        String s = "{message:{type:feed,timestamp:1},error:0}";
        JSONObject obj = new JSONObject(s);

        assertEquals("feed", obj.getJSONObject("message").getString("type"));
            // passed
            
        assertEquals(0, obj.getInt("error"));
            // passed
    }
}
