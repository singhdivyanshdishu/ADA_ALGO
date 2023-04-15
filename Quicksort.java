// //import java.util.Arrays;
// public class Quicksort {

//     public static void quicksort(int[] arr, int l, int h) {
//         if (l < h) {
//             int j = partition(arr, l, h);
//             quicksort(arr, l, j);
//             quicksort(arr, j + 1, h);
//         }
//     }

//     public static int partition(int[] arr, int l, int h) {
//         int pivot = arr[l];
//         int i = l, j = h;
//         while (i < j) {
//             while (arr[i] < pivot) {
//                 i++;
//                 i++;
//                 int t=arr[i];
//                 arr[i]=arr[j];
//                 arr[j]=t;
//             }
//             while (arr[j] > pivot) {
//                 j--;
//             }
//             if (i < j) {
//                 int temp = arr[i];
//                 arr[i] = arr[j];
//                 arr[j] = temp;
//             }
//         }
//         arr[l] = arr[j];
//         arr[j] = pivot;
//         return j;
//     }

//     public static void main(String[] args) {
//         int[] arr = { 5, 2, 9, 1, 5, 6 };
//         quicksort(arr, 0, arr.length - 1);
//         // System.out.println(Arrays.toString(arr)); // prints [1, 2, 5, 5, 6, 9]
//         for (int i = 0; i < arr.length; i++) {
//             System.out.println(arr[i]);
//         }
//     }
// }
public class Quicksort {
    public static void quicksort(int a[],int l,int h)
    {
        if(l<h)
        {
            int pi = partition(a , l, h);

            quicksort(a , l ,pi-1);
            quicksort(a , pi+1 , h);
        }
    } 
    public static int partition(int a[],int l , int h) 
    {
        int pivot =a[h];
        int i=l-1;

        for(int j=l;j<h;j++)
        {
            if(a[j]<pivot)
            {
                i++;
                int t=a[i];
                a[i]=a[j];
                a[j]=t;
            }
        }
        i++;
        int t=a[i];
        a[i]=a[h];
        a[h]=t;
        return i;
    }
    public static void main(String[] args) {
        Quicksort ob=new Quicksort();
        int a[]={5,6,8,3,10,1,9,20,-6};
        int len=a.length;
        ob.quicksort(a,0,len-1);
        for(int i=0;i<len;i++)
        {
            System.out.print(a[i]+" ");
        }
    }
}

