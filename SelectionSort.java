 public class SelectionSort {
// 
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        } else {
            int minIdx = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] < arr[minIdx]) {
                    minIdx = i;
                }
            }
            int temp = arr[0];
            arr[0] = arr[minIdx];
            arr[minIdx] = temp;
            int[] rest = new int[n - 1];
            System.arraycopy(arr, 1, rest, 0, n - 1);
            selectionSort(rest);
            System.arraycopy(rest, 0, arr, 1, n - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 4, 6, 1, 3 };
        selectionSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
