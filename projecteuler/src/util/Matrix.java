package util;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Utility class to represent a 2-D matrix.
 */
public class Matrix {
    private final Fraction[][] matrix;

    public Matrix(double[][] mat){
        matrix = new Fraction[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[0].length; j++){
                matrix[i][j] = new Fraction(mat[i][j], 1);
            }
        }
    }

    public Matrix(int[][] mat){
        matrix = new Fraction[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[0].length; j++){
                matrix[i][j] = new Fraction(mat[i][j], 1);
            }
        }
    }

    /**
     * Get Leading Coefficient. Gets first non-zero element of a row.
     * @param row Row to get leading coefficient of.
     * @return The leading coefficient of the row.
     */
    public Fraction getLeadingCoefficient(int row){
        for (int i = 0; i < matrix[row].length; i++){
            if (matrix[row][i].numerator != 0){
                return matrix[row][i].clone();
            }
        }
        return Fraction.ZERO;
    }

    /**
     * Swap Row: The first elementary row operation. Swaps two rows in the martix.
     * @param rowA Index of first row to be swapped.
     * @param rowB Index of second row to be swapped.
     */
    public void swapRow(int rowA, int rowB){
        Fraction[] temp = matrix[rowA];
        matrix[rowA] = matrix[rowB];
        matrix[rowB] = temp;
    }

    /**
     * Multiply Row: The second elementary row operation. Multiplies all elements of a row by a factor.
     * @param row The index of the row to be multiplied.
     * @param factor The factor to multiply the row by.
     */
    public void multiplyRow(int row, Fraction factor){
        for (int i = 0; i < matrix[row].length; i++){
            matrix[row][i].multiply(factor);
        }
    }

    /**
     * Add Multiple: The third elementary operation. Adds a multiple of one row to another.
     * @param rowA Index of row to multiply to add to row B.
     * @param rowB Index of row to be added on by row A.
     * @param factor Factor to multiply row A by.
     */
    public void addMultiple(int rowA, int rowB, Fraction factor){
        for (int i = 0; i < matrix[rowA].length; i++){
            Fraction temp = matrix[rowA][i].clone();
            temp.multiply(factor);
            matrix[rowB][i].add(temp);
        }
    }

    /**
     * Forward Elimination. Runs forward elimination on the matrix to convert it into triangular form.
     * https://en.wikipedia.org/wiki/Gaussian_elimination#Definitions_and_example_of_algorithm
     */
    public void forwardElimination(){
        for (int row = 0; row < matrix.length; row++){
            for (int addRow = row + 1; addRow < matrix.length; addRow++){
                Fraction ratio = getLeadingCoefficient(addRow);
                ratio.divide(getLeadingCoefficient(row));
                ratio.multiply(-1);
                addMultiple(row, addRow, ratio);
            }
        }
    }

    /**
     * Back Substitution. Runs back substitution on the matrix in triangular form
     * for solving a system of equations.
     */
    public void backSubstitution(){
        for (int row = matrix.length-1; row >= 0; row--){
            multiplyRow(row, getLeadingCoefficient(row).reciprocal());
            for (int addRow = row - 1; addRow >= 0; addRow--){
                Fraction ratio = matrix[addRow][row].clone();
                ratio.multiply(-1);
                addMultiple(row, addRow, ratio);
            }
        }
    }

    /**
     * Getter for an element.
     * @param row Row to get element from.
     * @param col Column to get element from.
     * @return Element in the requested row and column.
     */
    public double getElement(int row, int col){
        return matrix[row][col].value;
    }

    @Override
    public String toString(){
        return Arrays.deepToString(matrix);
                //.replace("[", "\n[");
    }

    /*
    public static int determinant(int[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];
        int total = 0;
        //traverse first row
        for (int i = 0; i < matrix.length; i++) {
            int[][] submatrix = new int[matrix.length - 1][matrix.length - 1];
            int indx = 0;
            for (int j = 0; j < (matrix.length) * (matrix.length); j++) {
                if (j / matrix.length == 0 || j % matrix.length == i) continue;
                submatrix[indx / (matrix.length - 1)][indx % (matrix.length - 1)] = matrix[j / matrix.length][j % matrix.length];
                indx++;
            }
            total += Math.pow(-1, i % 2) * matrix[0][i] * determinant(submatrix);
        }
        return total;
    }
    public static double determinant(double[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];
        int total = 0;
        //traverse first row
        for (int i = 0; i < matrix.length; i++) {
            double[][] submatrix = new double[matrix.length - 1][matrix.length - 1];
            int indx = 0;
            for (int j = 0; j < (matrix.length) * (matrix.length); j++) {
                if (j / matrix.length == 0 || j % matrix.length == i) continue;
                submatrix[indx / (matrix.length - 1)][indx % (matrix.length - 1)] = matrix[j / matrix.length][j % matrix.length];
                indx++;
            }
            total += Math.pow(-1, i % 2) * matrix[0][i] * determinant(submatrix);
        }
        return total;
    }

    public static int[][] adjugate(int[][] matrix){
        int[][] adjunct = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int[][] submatrix = new int[matrix.length - 1][matrix.length - 1];
                int indx = 0;
                for (int k = 0; k < (matrix.length) * (matrix.length); k++) {
                    if (i == k/matrix.length || k%matrix.length == j) continue;
                    submatrix[indx / (matrix.length - 1)][indx % (matrix.length - 1)] = matrix[k / matrix.length][k % matrix.length];
                    indx++;
                }
                adjunct[j][i] = determinant(submatrix);
            }
        }
        return adjunct;
    }

    public static double[][] adjugate(double[][] matrix){
        double[][] adjunct = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                double[][] submatrix = new double[matrix.length - 1][matrix.length - 1];
                int indx = 0;
                for (int k = 0; k < (matrix.length) * (matrix.length); k++) {
                    if (i == k/matrix.length || k%matrix.length == j) continue;
                    submatrix[indx / (matrix.length - 1)][indx % (matrix.length - 1)] = matrix[k / matrix.length][k % matrix.length];
                    indx++;
                }
                adjunct[j][i] = Math.pow(-1, i%2 + j%2) * determinant(submatrix);
            }
        }
        return adjunct;
    }

    public static double[][] inverse(double[][] matrix){
        double[][] inverse = new double[matrix.length][matrix.length];
        double[][] adjugate = adjugate(matrix);
        double determinant = determinant(matrix);
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                inverse[i][j] = adjugate[i][j]/determinant;
            }
        }
        return inverse;
    }
     */

}