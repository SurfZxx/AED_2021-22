import java.util.*;
//package aed.sorting;

public class JumpBubbleSort{

    /*
    0 2 1 3 6 4 5 8 7 9 -> maior elemento passa para topo
    9 0 2 1 3 6 4 5 8 7 -> corta o primeiro elemento
    0 2 1 3 6 4 5 8 7 -> maior elemento passa para o topo
    8 0 2 1 3 6 4 5 7 -> corta o primeiro elemento
    0 2 1 3 6 4 5 7 -> maior elemento passa para topo
    7 0 2 1 3 6 4 5 -> corta o primeiro elemento
    0 2 1 3 6 4 5 -> repetir ate ultimo elemento ser cortado
    resutltado final = {9 8 7 6 5 4 3 2 1 0} 
    */



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
        //Scanner scanner = new Scanner(System.in);
        Integer[] array = {6,5,9,2,4,7,1,8,3};
        //int indice = 0;
        // while (scanner.hasNext()) {
        //     array[indice] = scanner.nextInt();
        //     indice++;
        // }
        sort(array);
        System.out.println(Arrays.toString(array));
        //scanner.close();
    }
}