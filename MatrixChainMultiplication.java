import java.util.Arrays;

public class MatrixChainMultiplication {
    
    // public static void printMatrix(int[][] matrix) {
    //     for (int[] row : matrix) {
    //         System.out.println(Arrays.toString(row));
    //     }
    // }
    public static void printMatrix(int[][] matrix) {
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i==0||j==0)
                {
                    continue;
                }
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }
    public static void printOptimalParentheses(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParentheses(s, i, s[i][j]);
            printOptimalParentheses(s, s[i][j]+1, j);
            System.out.print(")");
        }
    }
    
    public static void matrixChainMultiplication(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n+1][n+1];
        int[][] s = new int[n+1][n+1];
        
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        
        for (int d = 2; d <= n; d++) {
            for (int i = 1; i <= n-d+1; i++) {
                int j = i + d - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j-1; k++) {
                    int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        
        System.out.println("Minimum number of multiplications: " + m[1][n]);
        System.out.println("Optimal parentheses: ");
        printOptimalParentheses(s, 1, n);
        System.out.println("\nMatrix M:");
        printMatrix(m);
        System.out.println("Matrix S:");
        printMatrix(s);
    }
    
    public static void main(String[] args) {
        int[] p = {5, 4, 6, 2, 7};
        //int[] p = {3,2,4,2,5};
        matrixChainMultiplication(p);
    }
}
