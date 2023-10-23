package org.example.binarySearchTree;

import java.util.*;

public class ExpressionTree extends BinarySearchTree<String> {

    private Node<String> root;

    public ExpressionTree(String expression) {
        super(null);
        String[] elements = revertToPrefix(expression);
        Stack<Node<String>> stack = new Stack<>();
        for (int i = elements.length - 1; i >= 0; i--) {
            String element = elements[i];
            Node<String> node = new Node<>(element);
            if (isElementAnOperator(element)) {
                node.leftChild = stack.pop();
                node.rightChild = stack.pop();
            }
            stack.push(node);
        }
        root = stack.pop();
    }

    private String[] revertToPrefix(String expression) {
        Stack<String> stack = new Stack<>();
        String[] symbols = expression.split("\\s+");
        List<String> prefix = new ArrayList<>();
        for (String s:
             symbols) {
            if (isElementAnOperator(s)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(s)) {
                    prefix.add(stack.pop());
                }
                stack.push(s);
            } else {
                prefix.add(s);
            }
        }
        while (!stack.isEmpty()) {
            prefix.add(stack.pop());
        }
        Collections.reverse(prefix);
        return prefix.toArray(new String[0]);
    }

    private int precedence(String element) {
        return switch (element) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> -1;
        };
    }

    public int evaluate() {
        return evaluate(root);
    }

    private int evaluate(Node<String> node) {
        if (!isElementAnOperator(node.value)) {
            return Integer.parseInt(node.value);
        } else {
            int leftValue = evaluate(node.leftChild);
            int rightValue = evaluate(node.rightChild);
            return switch (node.value) {
                case "+" -> rightValue + leftValue;
                case "-" -> rightValue - leftValue;
                case "*" -> rightValue * leftValue;
                case "/" -> rightValue / leftValue;
                default -> throw new IllegalArgumentException("Invalid operator: " + node.value);
            };
        }
    }

    private boolean isElementAnOperator(String element) {
        return element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/");
    }

    public String asIndentedPreOrder(int indent) {
        StringBuilder builder = new StringBuilder();
        buildInPreOrder(builder, indent, root);
        return builder.toString();
    }

    private void buildInPreOrder(StringBuilder builder, int indent, Node<String> root) {
        if (root == null)
            return;
        builder.append(" ".repeat(Math.max(0, indent)));
        builder.append(root.value).append("\n");
        buildInPreOrder(builder, indent + 2, root.leftChild);
        buildInPreOrder(builder, indent + 2, root.rightChild);
    }

    @Override
    public Node<String> getRoot() {
        return root;
    }
}
