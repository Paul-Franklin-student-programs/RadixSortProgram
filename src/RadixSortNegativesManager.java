import java.util.Arrays;

/**
 * Manages the separation, sorting, and merging of negative and non-negative integers for radix sort.
 */
public class RadixSortNegativesManager {
    private static Integer[] negativeArray;
    public static Integer[] nonNegativeArray;
    private static int negCount = 0;
    private static int posCount = 0;

    /**
     * Separates the negative and non-negative integers in the given array.
     *
     * @param mainArray the array to separate
     * @param length the length of the array
     */
    public static void negativeSeparator(Integer[] mainArray, int length) {
        if (mainArray == null || length == 0) {
            negativeArray = new Integer[0];
            nonNegativeArray = new Integer[0];
            return;
        }
        negativeArray = new Integer[length];
        nonNegativeArray = new Integer[length];

        int negIndex = 0;
        int posIndex = 0;

        for (int i = 0; i < length; i++) {
            if (mainArray[i] < 0) {
                negativeArray[negIndex++] = mainArray[i];
                negCount++;
            } else {
                nonNegativeArray[posIndex++] = mainArray[i];
                posCount++;
            }
        }

        negativeArray = Arrays.copyOf(negativeArray, negCount); // Trim array to actual size
        nonNegativeArray = Arrays.copyOf(nonNegativeArray, posCount); // Trim array to actual size
    }

    /**
     * Sorts both negative and non-negative integers in the given array.
     *
     * @param mainArray the array to sort
     * @param length the length of the array
     * @return the sorted array with negative and non-negative integers
     */
    public static Integer[] sortNegativesAndPositives(Integer[] mainArray, int length) {
        negativeSeparator(mainArray, length);

        if (negCount > 0) {
            // Convert negative values to positive, sort, then revert to negative
            for (int i = 0; i < negCount; i++) {
                negativeArray[i] = -negativeArray[i];
            }
            RadixSortManager.radixSorter(negativeArray, negativeArray.length);
            for (int i = 0; i < negCount; i++) {
                negativeArray[i] = -negativeArray[i];
            }
        }

        if (posCount > 0) {
            RadixSortManager.radixSorter(nonNegativeArray, nonNegativeArray.length);
        }

        return mergeNegativesAndPositives();
    }

    /**
     * Merges the sorted negative and non-negative integers.
     *
     * @return the merged array of sorted negative and non-negative integers
     */
    private static Integer[] mergeNegativesAndPositives() {
        Integer[] mergedArray = new Integer[negativeArray.length + nonNegativeArray.length];

        int p = 0;

        for (int i = negativeArray.length - 1; i >= 0; i--) {
            mergedArray[p++] = negativeArray[i];
        }

        for (Integer integer : nonNegativeArray) {
            mergedArray[p++] = integer;
        }

        return mergedArray;
    }
}
