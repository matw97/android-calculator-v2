package com.example.mateuszwisnik.calculator;

import org.mariuszgromada.math.mxparser.Expression;

public class Utils {
    public static double calculate(String expressionString) {
        Expression expression = new Expression(expressionString);
        return expression.calculate();
    }

    public static boolean isValidResult(double result) {
        return result != Double.NaN;
    }
}
