
    public class MaxMin {

        public static void maxmin(int[] a, int i, int j, int[] result) {
            if (i == j) {
                result[0] = a[i];
                result[1] = a[i];
            } else if (i == j - 1) {
                if (a[i] > a[j]) {
                    result[0] = a[i];
                    result[1] = a[j];
                } else {
                    result[0] = a[j];
                    result[1] = a[i];
                }
            } else {
                int[] leftResult = new int[2];
                int[] rightResult = new int[2];
                int mid = i + (j - i) / 2;
                maxmin(a, i, mid, leftResult);
                maxmin(a, mid + 1, j, rightResult);
                result[0] = Math.max(leftResult[0], rightResult[0]);
                result[1] = Math.min(leftResult[1], rightResult[1]);
            }
        }
    
        public static void main(String[] args) {
            int[] a = { 3, 4, 2, 1, 5, 6 };
            int[] result = new int[2];
            maxmin(a, 0, a.length - 1, result);
            System.out.println("Max: " + result[0]);
            System.out.println("Min: " + result[1]);
        }
    }
    
}
