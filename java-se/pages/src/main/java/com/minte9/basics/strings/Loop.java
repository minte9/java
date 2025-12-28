/**
 * Use getJSONArray() in order to loop an json
 */

package com.minte9.basics.strings;

import org.json.JSONArray;
import org.json.JSONObject;

public class Loop {
    public static void main(String[] args) {

        String str = "{"
            + "orders: ["
                + "{sym:goog,amount:100,error:1},"
                + "{sym:msft,amount:200,error:0}"
            + "], error:0" +
        "}";

        JSONObject obj = new JSONObject(str);
        JSONArray orders = obj.getJSONArray("orders");
            //System.out.println(orders);

        for(Object item : orders) {

            JSONObject order = (JSONObject) item; // Look Here
            System.out.println(""
                + order.getString("sym") 
                + ":"
                + order.getInt("error")
            );
                // goog:1
                // msft:0
        }
    }
}