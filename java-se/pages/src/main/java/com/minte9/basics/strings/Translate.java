/**
 * Translate custom formats to json for easy parsing.
 */

package com.minte9.basics.strings;
import org.json.JSONObject;

public class Translate {
    public static void main(String[] args) {

        //String s = "{message:{type:feed,timestamp:1},error:0}"; // json

        String s = "{message={type=feed|timestamp=1},error=0}"; // not a json
        JSONObject obj;

        s = translate(s);
        obj = new JSONObject(s);

        System.out.println(
            obj.getJSONObject("message").getString("type") // feed
        );

        System.out.println(
            obj.getInt("error") // 0
        );
    }
    
    public static String translate(String s) {

        s = s.replaceAll("([^={}|]+)=", "$1="); // add quotes to keys
        s = s.replaceAll("=([^={}|]+)", "=$1"); // add quotes to values
        s = s.replace("|", ",").replace("=", ":");
            // System.out.println(s);

        return s;
    }
}
