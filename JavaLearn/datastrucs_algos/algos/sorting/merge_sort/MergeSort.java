import java.util.Arrays;
public class MergeSort {
    public static int[] mergeSort(int[] numbers) {
        if (numbers.length <= 1) {
            return numbers;
        }
        //constants
        int midpoint = numbers.length / 2;
        int[] left = new int[midpoint];
        int[] right;
        if (numbers.length % 2 == 0) {
            right = new int[midpoint];
        }  else {
            right = new int[midpoint + 1];
        }

        //populating array
        for (int i = 0; i < midpoint; i++) {
            left[i] = numbers[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = numbers[i + midpoint];
        }

        System.out.println("left" + Arrays.toString(left));
        System.out.println("right" + Arrays.toString(right));

        int[] result = new int[numbers.length];

        //splitting left and right down to one element
        left = mergeSort(left);
        right = mergeSort(right);
        result = merge(left, right);
        return result;
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        while (leftPointer < left.length || rightPointer < right.length) {
            if (leftPointer < left.length && rightPointer < right.length) {
                if (left[leftPointer] < right[rightPointer]) {
                    result[resultPointer] = left[leftPointer];
                    resultPointer++;
                    leftPointer++;
                } else {
                    result[resultPointer] = right[rightPointer];
                    resultPointer++;
                    rightPointer++;
                }
            } else if (leftPointer < left.length) {
                //if only elements in left array
                result[resultPointer++] = left[leftPointer++];
                resultPointer++;
                leftPointer++;
            } else if (rightPointer < right.length) {
                //if only elements in the right array
                result[resultPointer++] = right[rightPointer++];
                resultPointer++;
                rightPointer++;
            }
            //System.out.println("leftpointer: " + leftPointer);
            //System.out.println("rightpointer: " + rightPointer);

        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {3,4,1,9,2,0,5};
        System.out.println(Arrays.toString(mergeSort(nums)));
    }
}
