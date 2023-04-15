import java.util.*;

public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = input.nextInt();

        int[] weights = new int[n];
        int[] profits = new int[n];

        System.out.println("Enter the weight and profit of each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("weight["+i+"] : ");
             weights[i] = input.nextInt(); //System.out.println();
            System.out.print("profit7["+i+"] : ");
            profits[i] = input.nextInt();
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = input.nextInt();

        double[] fractions = fractionalKnapsack(weights, profits, capacity);

        System.out.println("Selected fractions of each item:");
        for (int i = 0; i < n; i++) {
            System.out.printf("%.2f ", fractions[i]);
        }
    }

    public static double[] fractionalKnapsack(int[] weights, int[] profits, int capacity) {
        // Compute profit/weight ratio for each item and sort them in decreasing order
        double[] ratios = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            ratios[i] = (double) profits[i] / weights[i];
        }
        int[] indices = new int[weights.length];
        for (int i = 0; i < weights.length; i++) {
            indices[i] = i;
        }
        sort(ratios, indices, 0, weights.length - 1);

        // Initialize total profit and total weight
        double totalProfit = 0;
        double totalWeight = 0;
        double[] fractions = new double[weights.length];

        // Loop through all the items in sorted order
        for (int i = 0; i < weights.length; i++) {
            int index = indices[i];
            // If the entire item fits, add it to the knapsack
            if (weights[index] <= capacity - totalWeight) {
                fractions[index] = 1;
                totalProfit += profits[index];
                totalWeight += weights[index];
            }
            // Otherwise, add a fraction of the item that fills the remaining capacity
            else {
                double fraction = (double) (capacity - totalWeight) / weights[index];
                fractions[index] = fraction;
                totalProfit += profits[index] * fraction;
                totalWeight += weights[index] * fraction;
                break;
            }
        }

        return fractions;
    }

    public static void sort(double[] array, int[] indices, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, indices, left, right);
            sort(array, indices, left, pivotIndex - 1);
            sort(array, indices, pivotIndex + 1, right);
        }
    }

    public static int partition(double[] array, int[] indices, int left, int right) {
        double pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] >= pivot) {
                i++;
                swap(array, i, j);
                swap(indices, i, j);
            }
        }
        swap(array, i + 1, right);
        swap(indices, i + 1, right);
        return i + 1;
    }

   
    public static void swap(double[] array, int i, int j) {
    double temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}
}
