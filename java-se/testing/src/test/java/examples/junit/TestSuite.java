package examples.junit;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserTest.class, OrderTest.class})
public class TestSuite {

    @BeforeClass
    public static void globalSetup() {
        System.out.println("Connecting to test database...");
    }

    @AfterClass
    public static void globalTeardown() {
        System.out.println("Closing test databases...");
    }
}

/*
    Running examples.junit_v4.TestSuite
    Connecting to test database...

    UserTest.testCreateUser ....
    Imagine: insert user into mock DB
    UserTest.testDeleteUser ...
    Imagine: delete user from mock DB

    OrderTest.testPlaceOrder ...
    Imagine: insert order into mock DB

    Closing test databases...
    Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.057 sec
 */
