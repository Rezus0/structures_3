package org.example;

import org.example.binarySearchTree.BinarySearchTree;
import org.example.binarySearchTree.ExpressionTree;
import org.example.binaryTree.BinaryTree;
import org.example.binaryTree.TreePrinter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        binaryTreeTest();
        System.out.println("-------------------");
        binarySearchTreeTest();
        System.out.println("-------------------");
        expressionTreeTest();
    }

    private static void binaryTreeTest() {
        var tree = new BinaryTree<>(4,
                new BinaryTree<>(2,
                        new BinaryTree<>(1,
                                null,
                                null),
                        new BinaryTree<>(3,
                                null,
                                null)
                ),
                new BinaryTree<>(6,
                        new BinaryTree<>(5,
                                null,
                                null),
                        new BinaryTree<>(7,
                                null,
                                null)
                )
        );
        System.out.println(tree.asIndentedPreOrder(0));
        System.out.println(tree.bfs());
        System.out.println(tree.dfs());
        TreePrinter.print(tree);
    }



    private static void binarySearchTreeTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(null);
        bst.insert(4);
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(9);
        bst.insert(9);
        System.out.println(bst.bfs());
        System.out.println("Tree contains value 4: " + bst.contains(4));
        System.out.println("Tree contains value 12: " + bst.contains(12));
        System.out.println("Tree contains value 9: " + bst.contains(9));
        System.out.println("Tree contains value 0: " + bst.contains(0));
        System.out.println(bst.search(2).bfs());
    }

    private static void expressionTreeTest() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input an expression without brackets:");
        ExpressionTree tree = new ExpressionTree(in.nextLine());
        System.out.println(tree.asIndentedPreOrder(0));
        System.out.println(tree.bfs());
        System.out.println(tree.contains("-"));
        System.out.println(tree.contains("+"));
        System.out.println(tree.evaluate());
    }
}