import java.util.Arrays;

/**
 * Manages the radix sort operations.
 */
public class RadixSortManager {

    /**
     * Prints an array of Integer values.
     *
     * @param mainArray the array to print
     * @param length the length of the array
     */
    static void arrayPrint(Integer[] mainArray, int length) {
        if (mainArray == null) {
            System.out.println("mainArray cannot be null.");
            return;
        }
        for (int j = 0; j < length; j++) {
            System.out.print(mainArray[j] + " ");
        }
        System.out.println();
    }

    /**
     * Sorts the array based on the digit represented by exp.
     *
     * @param mainArray the array to sort
     * @param length the length of the array
     * @param exp the exponent representing the digit to sort by
     */
    static void digitSort(Integer[] mainArray, int length, int exp) {
        if (mainArray == null) {
            System.out.println("mainArray cannot be null.");
            return;
        }
        Integer[] convertedCount = new Integer[length]; // output array
        Arrays.fill(convertedCount, 0); // Ensure the array is initialized
        int[] digitCount = new int[10]; // Using int array instead of Integer to avoid nulls
        Arrays.fill(digitCount, 0);

        for (int j = 0; j < length; j++) {
            digitCount[(mainArray[j] / exp) % 10]++;
        }

        for (int j = 1; j < 10; j++) {
            digitCount[j] += digitCount[j - 1];
        }

        for (int j = length - 1; j >= 0; j--) {
            convertedCount[digitCount[(mainArray[j] / exp) % 10] - 1] = mainArray[j];
            digitCount[(mainArray[j] / exp) % 10]--;
        }

        System.arraycopy(convertedCount, 0, mainArray, 0, length);
    }

    /**
     * Performs radix sort on the given array.
     *
     * @param array the array to sort
     * @param length the length of the array
     */
    static void radixSorter(Integer[] array, int length) {
        if (array == null || length == 0) {  // Handle null and empty arrays
            return;
        }
        int m = arrayMax(array, length);
        for (int exp = 1; m / exp > 0; exp *= 10) {
            digitSort(array, length, exp);
        }
    }

    /**
     * Finds the maximum value in an array of Integer values.
     *
     * @param array the array to search
     * @param length the length of the array
     * @return the maximum value in the array
     */
    static int arrayMax(Integer[] array, int length) {
        if (array == null || length == 0) {
            return 0;
        }
        int maxValue = array[0];
        for (int j = 1; j < length; j++) {
            if (array[j] > maxValue) {
                maxValue = array[j];
            }
        }
        return maxValue;
    }
}
