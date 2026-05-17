package examples.junit;

import org.junit.Test;

public class UserTest {

    @Test
    public void testCreateUser() {
        System.out.println("UserTest.testCreateUser ....");
        System.out.println("Imagine: insert user into mock DB");
    }

    @Test
    public void testDeleteUser() {
        System.out.println("UserTest.testDeleteUser ...");
        System.out.println("Imagine: delete user from mock DB");
    }
}