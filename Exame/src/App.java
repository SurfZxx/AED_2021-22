public class App {


    public static int xpto2(int[] array, int n) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]%n == 0) {
                result = result + array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {2,4,6,3,5,9};
        int n = 3;
        int result = xpto2(array, n);
        System.out.println(result);
    }
}
