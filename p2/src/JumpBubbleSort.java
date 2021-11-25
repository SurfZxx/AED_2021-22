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

    

    public static void sort(int[] array) {
        int temp = 0;
        int dist = array.length-1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i-1]) {
                temp = array[i-1];
                array[i-1] = array[i];
                array[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = {6,5,9,2,4,7,1,8,3};
        int indice = 0;
        // while (scanner.hasNext()) {
        //     array[indice] = scanner.nextInt();
        //     indice++;
        // }
        sort(array);
    }
}