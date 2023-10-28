package org.example.binaryTree;

public class TreePrinter {
    private static int getHeight(AbstractBinaryTree<Integer> tree) {
        if (tree == null)
            return 0;
        return Math.max(getHeight(tree.getLeft()), getHeight(tree.getRight())) + 1;
    }

    private static int getColumns(int h) {
        if (h == 1)
            return 1;
        return getColumns(h - 1) + getColumns(h - 1) + 1;
    }

    private static void fillMatrix(Object[][] M, AbstractBinaryTree<Integer> tree, int col, int row, int height) {
        if (tree == null)
            return;
        M[row][col] = tree.getKey();
        fillMatrix(M, tree.getLeft(), col - (int)Math.pow(2, height - 2), row + 1, height - 1);
        fillMatrix(M, tree.getRight(), col + (int)Math.pow(2, height - 2), row + 1, height - 1);
    }

    public static void print(AbstractBinaryTree<Integer> tree) {
        int h = getHeight(tree);
        int col = getColumns(h);
        Object[][] matrix = new Object[h][col];
        fillMatrix(matrix, tree, col / 2, 0, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == null)
                    System.out.print("  ");
                else
                    System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
