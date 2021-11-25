import java.util.*;

public class JumpBubbleSort {

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

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int[] simpleSort(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
            
        }
        return array;
    }

    public static int[] bubbleSort(int[] array) {
        int dist = array.length-1;
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
         if (array[i] > array[dist]) {
             temp = array[dist];
             array[dist] = array[i];
         }
        }



        return array;
    }

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        int[] array = {6,5,9,2,4,7,1,8,3};
        //int indice = 0;
        // while (scanner.hasNext()) {
        //     array[indice] = scanner.nextInt();
        //     indice++;
        // }
        simpleSort(array);
        System.out.println(Arrays.toString(array));
        //scanner.close();
    }
}