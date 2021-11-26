import java.util.*;
package aed.sorting;

public class JumpBubbleSort extends Sort {



//    public static int[] simpleSort(int[] array) {
//        int temp = 0;
//        for (int i = 0; i < array.length; i++) {
//            for (int j = i+1; j < array.length; j++) {
//                if (array[i] > array[j]) {
//                    temp = array[j];
//                    array[j] = array[i];
//                    array[i] = temp;
//                }
//            }
//
//        }
//        return array;
//    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
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
            if(!swapped) return;
            d= (int) Math.floor(d*0.77);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Integer[] array = {1,2,3,4,5,6,7,8,9}; funciona
        //Integer[] array = {6,5,9,2,4,7,1,8,3}; funciona
        // Integer[] array = {9,8,7,6,5,4,3,2,1}; funciona
        //Integer[] array = {6,2,3,4,1,7,8,9}; nao funciona

        Integer[] array = new Integer[9];
        for (int i = 0; i < 9; i++) {
            array[i] = scanner.nextInt();
        }
        // int indice = 0;
        // while (scanner.hasNext()) {
        //     array[indice] = scanner.nextInt();
        //     indice++;
        // }
        sort(array);
        System.out.println(Arrays.toString(array));
        scanner.close();
    }
}