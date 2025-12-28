/**
 * TEST SUITE - RUN MULTIPLE TEST CLASSES TOGETHER
 * -----------------------------------------------
 * A Test Suite lets you group several test classes and run them as a unit.
 * 
 * WHY USE A SUITE?
 * ----------------
 *  - To run related tests together (e.g., integration tests)
 *  - To share expensive setup across multiple test classes
 *  - To control execution order
 * 
 * In this example:
 *  - @BeforeClass opens a shared "database connection"
 *  - @AfterClass closes it
 *  - UserTest and OrderTest both run inside the same suite
 */

package com.minte9.junit.test_suite;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        OrderTest.class
})
public class SuiteTest {

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
    Connecting to test database...
    Running UserTest.testCreateUser
    Running UserTest.testDeleteUser
    Running OrderTest.testPlaceOrder
    Closing test databases...
 */