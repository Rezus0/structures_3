package org.example.binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;

    public BinarySearchTree(Node<E> node) {
        this.root = node;
    }

    @Override
    public void insert(E element) {
        root = insert(getRoot(), element);
    }

    private Node<E> insert(Node<E> root, E element) {
        if (root == null)
            return new Node<>(element);
        if (element.compareTo(root.value) < 0)
            root.leftChild = insert(root.leftChild, element);
        else if (element.compareTo(root.value) > 0)
            root.rightChild = insert(root.rightChild, element);
        return root;
    }


    @Override
    public boolean contains(E element) {
        return contains(getRoot(), element);
    }

    private boolean contains(Node<E> root, E element) {
        if (root == null)
            return false;
        if (element.compareTo(root.value) == 0)
            return true;
        else if (element.compareTo(root.value) < 0)
            return contains(root.leftChild, element);
        else
            return contains(root.rightChild, element);
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        return new BinarySearchTree<>(search(getRoot(), element));
    }

    private Node<E> search(Node<E> root, E element) {
        if (root == null)
            return null;
        if (element.compareTo(root.value) == 0)
            return root;
        if (element.compareTo(root.value) < 0)
            return search(root.leftChild, element);
        else
            return search(root.rightChild, element);
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    @Override
    public Node<E> getLeft() {
        return root.leftChild;
    }

    @Override
    public Node<E> getRight() {
        return root.rightChild;
    }

    @Override
    public E getValue() {
        return root.value;
    }

    @Override
    public String bfs() {
        StringBuilder builder = new StringBuilder();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(getRoot());
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            builder.append(current.value).append(", ");
            if (current.leftChild != null)
                queue.add(current.leftChild);
            if (current.rightChild != null)
                queue.add(current.rightChild);
        }
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }

    @Override
    public String dfs() {
        StringBuilder builder = new StringBuilder();
        buildDfs(builder, getRoot());
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }

    private void buildDfs(StringBuilder builder, Node<E> root) {
        if (root == null)
            return;
        builder.append(root.value).append(", ");
        buildDfs(builder, root.leftChild);
        buildDfs(builder, root.rightChild);
    }
}
