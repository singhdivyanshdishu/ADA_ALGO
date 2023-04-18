import java.util.Arrays;

public class knapsack01 {
    public static void main(String[] args) {
        // int[] p = {0, 1, 2, 5, 6}; // Profits
        // int[] wt = {0, 2, 3, 4, 5}; // Weights
        // int m = 8; // Capacity of knapsack
        // int n = 4; // Number of items
        int[] p = {0,2,5,8,1}; // Profits
        int[] wt = {0,10,15,6,9}; // Weights
        int m = 15; // Capacity of knapsack
        int n = 4; // Number of items
        int[][] k = new int[n+1][m+1]; // DP table
        boolean[][] used = new boolean[n+1][m+1]; // Items used in optimal solution

        // Fill DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= m; w++) {
                if (i == 0 || w == 0) {
                    k[i][w] = 0; // Base case
                } else if (wt[i] <= w) {
                    int withItem = k[i-1][w-wt[i]] + p[i];
                    int withoutItem = k[i-1][w];
                    if (withItem > withoutItem) {
                        k[i][w] = withItem;
                        used[i][w] = true;
                    } else {
                        k[i][w] = withoutItem;
                    }
                } else {
                    k[i][w] = k[i-1][w]; // Item cannot fit
                }
            }
        }

        int maxProfit = k[n][m]; // Maximum profit
        System.out.println("Maximum profit: " + maxProfit);

        // Determine items used in optimal solution
        boolean[] itemUsed = new boolean[n+1];
        int w = m;
        for (int i = n; i > 0; i--) {
            if (used[i][w]) {
                itemUsed[i] = true;
                w -= wt[i];
            }
        }

        // Print items used in optimal solution in 0/1 form
        System.out.print("Items used in optimal solution: ");
        for (int i = 1; i <= n; i++) {
            if (itemUsed[i]) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
        }
        System.out.println();

        // Optional: Print DP table
        for (int[] row : k) {
            System.out.println(Arrays.toString(row));
        }
    }
}



// import java.util.Arrays;

// public class knapsack01 {
//     public static void main(String[] args) {
//         int[] p = {0, 1, 2, 5, 6}; // Profits
//         int[] wt = {0, 2, 3, 4, 5}; // Weights
//         int m = 8; // Capacity of knapsack
//         int n = 4; // Number of items

//         int[][] k = new int[n+1][m+1]; // DP table

//         // Fill DP table
//         for (int i = 0; i <= n; i++) {
//             for (int w = 0; w <= m; w++) {
//                 if (i == 0 || w == 0) {
//                     k[i][w] = 0; // Base case
//                 } else if (wt[i] <= w) {
//                     k[i][w] = Math.max(k[i-1][w], k[i-1][w-wt[i]] + p[i]); // Include or exclude item
//                 } else {
//                     k[i][w] = k[i-1][w]; // Item cannot fit
//                 }
//             }
//         }

//         int maxProfit = k[n][m]; // Maximum profit
//         System.out.println("Maximum profit: " + maxProfit);

//         // Optional: Print DP table
//         for (int[] row : k) {
//             System.out.println(Arrays.toString(row));
//         }
//     }
// }
