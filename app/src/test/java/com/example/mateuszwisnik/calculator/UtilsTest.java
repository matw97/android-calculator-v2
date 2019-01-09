package com.example.mateuszwisnik.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    private static final double DELTA = 1e-15;

    @Test
    public void calculateMethodReturnsCorrectResults() {
        assertEquals(4, Utils.calculate("2+2"), DELTA);
        assertEquals(4, Utils.calculate("2*2"), DELTA);
        assertEquals(4, Utils.calculate("8/2"), DELTA);
        assertEquals(4, Utils.calculate("8-4"), DELTA);
        assertEquals(5, Utils.calculate("5*5/5"), DELTA);
        assertEquals(10, Utils.calculate("5*10/5"), DELTA);
        assertEquals(91, Utils.calculate("3*3+4*4+5*5/3*3+4*4+5*5"), DELTA);
        assertEquals(Double.NaN, Utils.calculate("1/0"), DELTA);
        assertEquals(Double.NaN, Utils.calculate("----------"), DELTA);
        assertEquals(Double.NaN, Utils.calculate("......"), DELTA);
        assertEquals(Double.NaN, Utils.calculate("///"), DELTA);
        assertEquals(Double.NaN, Utils.calculate("0.0.0.0"), DELTA);
    }

    @Test
    public void isValidResultMethodReturnsCorrectValues() {
        assertTrue(Utils.isValidResult(23.00));
        assertTrue(Utils.isValidResult(13.45));
        assertTrue(Utils.isValidResult(3.0));
        assertFalse(Utils.isValidResult(Double.NaN));
    }

    @Test
    public void isOperationPossibleMethodReturnsCorrectValues() {
        assertTrue(Utils.isOperationPossible("3+3"));
        assertTrue(Utils.isOperationPossible("3+3+3/2/2*2"));
        assertFalse(Utils.isOperationPossible(".."));
        assertFalse(Utils.isOperationPossible("++"));
        assertFalse(Utils.isOperationPossible("--"));
        assertFalse(Utils.isOperationPossible("//"));
        assertFalse(Utils.isOperationPossible("**"));
        assertFalse(Utils.isOperationPossible("-----------------"));
        assertFalse(Utils.isOperationPossible("......."));
        assertFalse(Utils.isOperationPossible(""));
    }
}
