//package aed.sorting;
import java.util.*;
public class JumpBubbleSort extends Sort {


    // public static boolean less(Comparable v, Comparable w) {
    //     return v.compareTo(w) < 0;
    // }

    // public static void exchange(Comparable[] a, int i, int j) {
    //     Comparable t = a[i];
    //     a[i] = a[j];
    //     a[j] = t;
    // }

    // public static boolean isSorted(Comparable[] a) {
    //     for (int i = 1; i < a.length; i++) {
    //         if (less(a[i], a[i-1])) return false;
    //     }
    // }

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

    public static <T extends  Comparable<T>> void sort(T[] array) {
        int n = array.length;
        int d=n-1;
        boolean swapped = false;
        while (d>=1)   {
            swapped = false ;
            for (int j = d; j < n; j++ )  {
                if  (less(array[j],array[j-d]))   {
                    exchange(array, j,j-d);
                    swapped = true;
                }
            }
            if(!swapped && d==1) return;
            d = (int) Math.floor(d*0.77);
        }
    }

    public static void main(String[] args) {
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

    /*
    testes:
    250     0.03333333333333333     0.029411764705882353
    500     0.03333333333333333     1.0
    1000    0.06666666666666667     2.0
    2000    0.26666666666666666     4.0
    4000    0.5     1.875
    8000    1.5     3.0
    16000   2.8333333333333335      1.888888888888889
    32000   7.066666666666666       2.494117647058823
    64000   15.466666666666667      2.188679245283019
    128000  36.0    2.3275862068965516
    */



    // public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);

    //     //Integer[] array = {1,2,3,4,5,6,7,8,9}; funciona
    //     //Integer[] array = {6,5,9,2,4,7,1,8,3}; funciona
    //     // Integer[] array = {9,8,7,6,5,4,3,2,1}; funciona
    //     //Integer[] array = {6,2,3,4,1,7,8,9}; funciona

    //     Integer[] array = new Integer[9];
    //     for (int i = 0; i < 9; i++) {
    //         array[i] = scanner.nextInt();
    //     }
    //     // int indice = 0;
    //     // while (scanner.hasNext()) {
    //     //     array[indice] = scanner.nextInt();
    //     //     indice++;
    //     // }
    //     sort(array);
    //     System.out.println(Arrays.toString(array));
    //     scanner.close();
    // }
}