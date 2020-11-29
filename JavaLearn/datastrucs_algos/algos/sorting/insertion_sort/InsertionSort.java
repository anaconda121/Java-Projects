public class InsertionSort {
    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();

        int[] array = new int[100000];
        int index =0;
        for (int i = 100000; i> 0; --i) {
            array[index] = i;
            index++;
        }
        sorter.insertionSort(array);
        System.out.println(java.util.Arrays.toString(array));
    }

    public int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current) {
                //start switching values until we reached correct spot
                array[j+1] = array[j];
                j--; //variable responsible for shifting over sorted array and comparing values
            }
            array[j + 1] = current;
        }
        return array;
    }
}
