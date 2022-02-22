import java.util.*;

public class App<StackList, QueueList> {



    // public static void exer() {
    //     StackList<Integer> s = new StackList<Integer>();
    //     QueueList<Integer> q = new QueueList<Integer>();
    //     for(int i = 1; i< 7; i++) {
    //         s.push(i);
    //         q.enqueue(i);
    //     }
    //     while(!q.isEmpty()) s.push(q.dequeue());
    //     while(!s.isEmpty())  {
    //         System.out.print(s.pop() + ",");
    //     }
    // }


    // public static int xpto2(int[] array, int n) {
    //     int result = 0;
    //     for (int i = 0; i < array.length; i++) {
    //         if (array[i]%n == 0) {
    //             result = result + array[i];
    //         }
    //     }
    //     return result;
    // }

    public static int calcNPares(int n, int[] array) {
        int result = 0;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (count <= n-1 && array[i]%2 == 0) {
                    result = result + array[i];
                    count++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//         int[] array = {2,4,6,3,5,9};
//         int n = 3;
//         int result = xpto2(array, n);
//         System.out.println(result);
        // exer();
        int[] array = {8,6,5,4,3,2,1};
        int result = calcNPares(3, array);
        System.out.println(result);
    }
}
