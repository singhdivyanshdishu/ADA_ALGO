public class NQueens {
    int[] x;
    
    public void solve(int n) {
        x = new int[n+1];
        nQueens(1, n);
    }
    
    public void nQueens(int k, int n) {
        for (int i = 1; i <= n; i++) {
            if (place(k, i)) {
                x[k] = i;
                if (k == n) {
                    printSolution(x);
                }
                else {
                    nQueens(k+1, n);
                }
            }
        }
    }
    
    
    public boolean place(int k, int i) {
        for (int j = 1; j < k; j++) {
            if (x[j] == i || Math.abs(x[j] - i) == Math.abs(j - k)) {
                return false;
            }
        }
        return true;
    }
    
    public void printSolution(int[] x) {
        for (int i = 1; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        NQueens nq = new NQueens();
        int n = 5; // number of queens
        nq.solve(n);
    }
}
