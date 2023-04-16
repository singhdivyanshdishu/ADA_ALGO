import java.util.ArrayList;
import java.util.Arrays;

public class GreedyActivitySelector {
    public static ArrayList<Integer> selectActivities(int[] s, int[] f) {
        int n = s.length;
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(0); // select the first activity
        int i = 0;
        for (int m = 1; m < n; m++) {
            if (s[m] >= f[i]) { // check if activity m is compatible with the last selected activity
                A.add(m); // select activity m
                i = m; // update the index of the last selected activity
            }
        }
        return A;
    }
    
    
    public static void main(String[] args) {
        // int[] s =  {10, 1, 3, 5, 7, 8};
        // int[] f = {20, 2, 4, 6, 9, 12};
        int[] s =  {1,2,4,1,5,8,9,11,13};
        int[] f = {3,5,7,8,9,10,11,14,16};
        sortActivities(s, f); // sort the activities in non-decreasing order of end times
        ArrayList<Integer> selectedActivities = selectActivities(s, f);
        System.out.println("Selected activities: " + selectedActivities);
    }
    
    public static void sortActivities(int[] s, int[] f) {
        int n = s.length;
        // create an array of pairs (s[i], f[i])
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = s[i];System.out.println("s["+i+"] : "+s[i]);
            pairs[i][1] = f[i];System.out.println("f["+i+"] : "+f[i]);
        }
        // sort the array of pairs in non-decreasing order of end times
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));System.out.println(Arrays.toString(pairs));
        // copy the sorted start times and end times back to the original arrays
        for (int i = 0; i < n; i++) {
            s[i] = pairs[i][0];
            System.out.println("s["+i+"] : "+s[i]);
            f[i] = pairs[i][1];System.out.println("f["+i+"] : "+f[i]);
        }
    }
}


