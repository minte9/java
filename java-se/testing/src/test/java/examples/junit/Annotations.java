package examples.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Annotations {
    
    @Before 
    public void start() {
        System.out.println("Db connection start");
    }

    @Test 
    public void run() {
        System.out.println("App run test");
    }

    @After 
    public void end() {
        System.out.println("Db connection close");
    }

    /*
        Db connection start
        App run test
        Db connection close
    */
}
