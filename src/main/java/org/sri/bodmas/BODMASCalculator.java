package org.sri.bodmas;

import java.util.*;

public class BODMASCalculator {

    public static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If it's a digit, keep building the number
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                // If a number was being built, add it to tokens
                if (number.length() > 0) {
                    tokens.add(number.toString());
                    number.setLength(0);
                }

                // Add operator or bracket to the tokens
                if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                    tokens.add(String.valueOf(c));
                }
            }
        }
        // Add the last number if it's left
        if (number.length() > 0) {
            tokens.add(number.toString());
        }

        return tokens;
    }

    private static Map<String, Integer> precedence = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
    );

    public static int evaluateExpression(List<String> tokens) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                operandStack.push(Integer.parseInt(token));
            } else if (isOperator(token)) {
                // Handle operator precedence
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), token)) {
                    applyOperation(operandStack, operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    applyOperation(operandStack, operatorStack.pop());
                }
                operatorStack.pop();  // Discard the left parenthesis
            }
        }

        // Apply remaining operators
        while (!operatorStack.isEmpty()) {
            applyOperation(operandStack, operatorStack.pop());
        }

        // Final result
        return operandStack.pop();
    }

    // Helper to check if a string is a number
    private static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper to check if a string is an operator
    private static boolean isOperator(String token) {
        return precedence.containsKey(token);
    }

    // Helper to determine operator precedence
    private static boolean hasHigherPrecedence(String op1, String op2) {
        if (op1.equals("(") || op1.equals(")")) return false;
        return precedence.get(op1) >= precedence.get(op2);
    }

    // Apply an operation (e.g., "+", "*") to the operand stack
    private static void applyOperation(Stack<Integer> operandStack, String operator) {
        int b = operandStack.pop();
        int a = operandStack.pop();
        switch (operator) {
            case "+":
                operandStack.push(a + b);
                break;
            case "-":
                operandStack.push(a - b);
                break;
            case "*":
                operandStack.push(a * b);
                break;
            case "/":
                operandStack.push(a / b);
                break;
        }
    }

    public static void main(String[] args) {
        String expression = "3+(2*5)";
        List<String> tokens = tokenize(expression);
        System.out.println(tokens);// Output: [3, +, (, 2, *, 5, ), -, 8, /, 4]
        int i = evaluateExpression(tokens);
        System.out.println(i);
    }
}
