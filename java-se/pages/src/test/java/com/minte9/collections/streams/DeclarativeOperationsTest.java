/**
 * Unit tests for Declarative Operations
 */

package com.minte9.collections.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class DeclarativeOperationsTest {
    
    @Test
    public void testIsEvenPredicate() {
        assertTrue(DeclarativeOperations.IS_EVEN.test(2));
        assertTrue(DeclarativeOperations.IS_EVEN.test(10));
        assertFalse(DeclarativeOperations.IS_EVEN.test(3));
    }

    @Test
    public void testSqareFunction() {
        assertEquals(Integer.valueOf(4), DeclarativeOperations.SQUARE.apply(2));
        assertEquals(Integer.valueOf(100), DeclarativeOperations.SQUARE.apply(10));
        assertNotEquals(Integer.valueOf(8), DeclarativeOperations.SQUARE.apply(3));
    }

    @Test
    public void testPipelineFilterAndSquare() {
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> expected = List.of(4, 16, 36);

        assertEquals(expected, DeclarativeOperations.filterAndSquare(input));
    }
}
