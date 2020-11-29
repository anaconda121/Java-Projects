public class SelectionSort {

    public void sort(int[] values) {
        SelectionSort.selectionSort(values);
    }
  
    public static void selectionSort(int[] array) {
        final int N = array.length;
        for (int i = 0; i < N; i++) {
            // Find the index beyond i with a lower value than i
            int swapIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (array[j] < array[swapIndex]) {
                    swapIndex = j;
                }
            }
            swap(array, i, swapIndex);
        }
    }
  
    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
        //System.out.println(java.util.Arrays.toString(ar));
    }
  
    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        int[] array = {9,8,7,6,5,34,3,2,1,3};
        sorter.sort(array);
        System.out.println(java.util.Arrays.toString(array));
    }
  }