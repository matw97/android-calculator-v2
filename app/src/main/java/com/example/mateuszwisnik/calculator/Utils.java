package com.example.mateuszwisnik.calculator;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.Arrays;

class Utils {

    private static final String[] signs = {"+", "-", "*", "/", "."};

    private Utils() {}

    static double calculate(String expressionString) {
        Expression expression = new Expression(expressionString);
        return expression.calculate();
    }

    static boolean isValidResult(double result) {
        return Double.compare(result, Double.NaN) != 0;
    }

    static boolean isOperationPossible(String expression) {
        if(!expression.equals(""))
        {
            String match = String.valueOf(expression.charAt(expression.length() - 1));
            return Arrays.stream(signs).noneMatch(match::equals);
        }
        return false;
    }
}
