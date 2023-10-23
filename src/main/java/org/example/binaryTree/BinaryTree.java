package org.example.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private final BinaryTree<E> left;
    private final BinaryTree<E> right;

    public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public E getKey() {
        return key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder builder = new StringBuilder();
        buildInPreOrder(builder, indent, this);
        return builder.toString();
    }

    private void buildInPreOrder(StringBuilder builder, int indent, BinaryTree<E> root) {
        if (root == null)
            return;
        builder.append(" ".repeat(Math.max(0, indent)));
        builder.append(root.key).append("\n");
        buildInPreOrder(builder, indent + 2, root.left);
        buildInPreOrder(builder, indent + 2, root.right);
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        ArrayList<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if (left != null)
            result.addAll(left.preOrder());
        if (right != null)
            result.addAll(right.preOrder());
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        ArrayList<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null)
            result.addAll(left.preOrder());
        result.add(this);
        if (right != null)
            result.addAll(right.preOrder());
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        ArrayList<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null)
            result.addAll(left.preOrder());
        if (right != null)
            result.addAll(right.preOrder());
        result.add(this);
        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (left != null) {
            left.forEachInOrder(consumer);
        }
        consumer.accept(key);
        if (right != null) {
            right.forEachInOrder(consumer);
        }
    }

    @Override
    public String bfs() {
        StringBuilder builder = new StringBuilder();
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            BinaryTree<E> current = queue.poll();
            builder.append(current.key).append(", ");
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }

    @Override
    public String dfs() {
        StringBuilder builder = new StringBuilder();
        buildDfs(builder, this);
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }

    private void buildDfs(StringBuilder builder, BinaryTree<E> root) {
        if (root == null)
            return;
        builder.append(root.key).append(", ");
        buildDfs(builder, root.left);
        buildDfs(builder, root.right);
    }
}
