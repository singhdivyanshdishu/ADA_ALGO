public class AssemblyLineScheduling {
    
    public static void main(String[] args) {
        int n = 6; // number of stations
        int[] a1 = {7, 9, 3, 4, 8, 4}; // assembly time at each station for line 1
        int[] a2 = {8, 5, 6, 4, 5, 7}; // assembly time at each station for line 2
        int[] t1 = {2, 3, 1, 3, 4}; // transfer time from station i in line 1 to station i+1 in line 1
        int[] t2 = {2, 1, 2, 2, 1}; // transfer time from station i in line 2 to station i+1 in line 2
        int e1 = 2; // entry time for line 1
        int e2 = 4; // entry time for line 2
        int x1 = 3; // exit time for line 1
        int x2 = 2; // exit time for line 2
        
        int[] f1 = new int[n]; // fastest time to reach station i in line 1
        int[] f2 = new int[n]; // fastest time to reach station i in line 2
        int[][] l = new int[2][n]; // optimal path to reach station i in line j
        
        // Step 1: Initialization
        f1[0] = e1 + a1[0];
        f2[0] = e2 + a2[0];
        
        // Step 2: Compute the fastest times and optimal paths
        for (int j = 1; j < n; j++) {
            // Compute the fastest time to reach station j in line 1
            if (f1[j-1] + a1[j] <= f2[j-1] + t2[j-1] + a1[j]) {
                f1[j] = f1[j-1] + a1[j];
                l[0][j] = 1; // Take line 1 to station j
            } else {
                f1[j] = f2[j-1] + t2[j-1] + a1[j];
                l[0][j] = 2; // Take line 2 to station j
            }
            // Compute the fastest time to reach station j in line 2
            if (f2[j-1] + a2[j] <= f1[j-1] + t1[j-1] + a2[j]) {
                f2[j] = f2[j-1] + a2[j];
                l[1][j] = 2; // Take line 2 to station j
            } else {
                f2[j] = f1[j-1] + t1[j-1] + a2[j];
                l[1][j] = 1; // Take line 1 to station j
            }
        }
        
        // Step 3: Find the optimal time and path
        int fstar, lstar;
        if (f1[n-1] + x1 <= f2[n-1] + x2) {
            fstar = f1[n-1] + x1;
            lstar = 1; // Take line 1 at the last station
        } else {
            fstar = f2[n-1] + x2;
            lstar = 2; // Take line 2 at the last station
    System.out.println("Optimal time: " + fstar);}
    
    // Step 4: Print the optimal path
    int i = lstar;
    System.out.println("Optimal path:");
    System.out.println("Line " + i + " station " + n);
    for (int j = n-1; j >= 1; j--) {
        i = l[i-1][j];
        System.out.println("Line " + i + " station " + j);
    }
    System.out.println("Line " + lstar + " station 1");
}
}

