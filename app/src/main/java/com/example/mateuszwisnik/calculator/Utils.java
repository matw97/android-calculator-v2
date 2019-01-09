package com.example.mateuszwisnik.calculator;

import org.mariuszgromada.math.mxparser.Expression;

class Utils {

    private Utils() {}

    static double calculate(String expressionString) {
        Expression expression = new Expression(expressionString);
        return expression.calculate();
    }

    static boolean isValidResult(double result) {
        return Double.compare(result, Double.NaN) != 0;
    }
}
