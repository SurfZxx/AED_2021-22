package aed.sorting;
import java.util.*;


public class QuickSort3Way extends Sort {

//     public static boolean less(Comparable v, Comparable w) {
//         return v.compareTo(w) < 0;
//     }
//
//     public static void exchange(Comparable[] a, int i, int j) {
//         Comparable t = a[i];
//         a[i] = a[j];
//         a[j] = t;
//     }
//     public static boolean isSorted(Comparable[] a) {
//         for (int i = 1; i < a.length; i++) {
//             if (less(a[i], a[i-1])) return false;
//         }
//     }

    public static Integer[] generateExample(int n) {
        Random r = new Random();
        Integer[] examples = new Integer[n];
        for (int i = 0; i < n; i++) {
            examples[i] = r.nextInt();
        }
        return examples;
    }

    public static double calculateAverageExecutionTime(int n) {
        int trials = 30;
        double totalTime = 0;
        for (int i = 0; i < trials; i++) {
            Integer[] example = generateExample(n);
            long time = System.currentTimeMillis();
            sort(example);
            totalTime += System.currentTimeMillis() - time;
            if (!isSorted(example)) {
                System.out.println("Failed");
            }
        }
        return totalTime / trials;
    }


    public static <T extends Comparable<T>> void sort(T[] array) {
        int low = 0;
        int high = array.length-1;
        if (high <= low) return;
        int lt = low;
        int gt = high;
        T v = array[low];
        int i = low + 1;
        while (i <= gt) {
            int cmp = array[i].compareTo(v);
            if  (cmp < 0) {
                exchange(array, lt++, i++);
            }
            else if (cmp > 0) {
                exchange(array, i, gt--);
            }
            else {
                i++;
            }
        }
    }
    
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
//        Integer[] array = {2,2,1,0,1,1,3,3,3,0};
//        sort(array, 0, array.length-1);
//        System.out.println(Arrays.toString(array));
        int n = 5000;
        double previousTime = calculateAverageExecutionTime(n);
        double newTime;
        double doublingRatio;
        for (int i = 250; true; i*=2) {
            newTime = calculateAverageExecutionTime(i);
            if (previousTime > 0) {
                doublingRatio = newTime/previousTime;
            } else {
                doublingRatio = 0;
            }
            previousTime = newTime;
            System.out.println(i + "\t" + newTime + "\t" + doublingRatio);
        }

    }
}
