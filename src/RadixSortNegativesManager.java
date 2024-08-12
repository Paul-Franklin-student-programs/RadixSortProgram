import java.util.Arrays;

/**
 * Manages the separation, sorting, and merging of negative and non-negative integers for radix sort.
 */
public class RadixSortNegativesManager {
    /**
     * Declared to store all negative Integers from "mainArray"
     */
    private static Integer[] negativeArray;
    /**
     * Declared to store all non-negative Integers from "mainArray"
     */
    public static Integer[] nonNegativeArray;
    /**
     * Declared to count the quantity of negative values in "mainArray"
     */
    private static int negCount = 0;
    /**
     * Declared to count the quantity of non-negative values in "mainArray"
     */
    private static int posCount = 0;

    /**
     * Separates the negative and non-negative integers in the given array
     * Initializes the arrays "negativeArray" "nonNegativeArray"
     * Initializes ints "posCount" and "negCount"
     * Populates "negativeArray" and "nonNegativeArray" and trims them according
     * to posCount and negCount
     */
    public static void negativeSeparator(Integer[] mainArray, int length) {
        //Sets lengths of both "negativeArray" and "nonNegativeArray" to 0
        //if "mainArray" is null or if "mainArray" has a length of 0
        if (mainArray == null || length == 0) {
            negativeArray = new Integer[0];
            nonNegativeArray = new Integer[0];
            return;
        }
        //Initializes "negativeArray" and "nonNegativeArray", setting lengths according
        //to user-provided argument for "negativeSeparator" method's "length" parameter
        negativeArray = new Integer[length];
        nonNegativeArray = new Integer[length];

        //Initializes "negIndex" and "posIndex" to 0, so these variables can facilitate
        //iteration through both "nonNegativeArray" and "negativeArray" by
        //indicating what position in the arrays to insert values
        int negIndex = 0;
        int posIndex = 0;

        for (int i = 0; i < length; i++) {
            //adds negative "mainArray" elements to "negativeArray", increments
            //"negCount" (quantity of negative values in negativeArray) and increments
            //"negIndex" (position in "negativeArray" to insert value)
            if (mainArray[i] < 0) {
                negativeArray[negIndex++] = mainArray[i];
                negCount++;

            //Adds non-negative "mainArray" elements to "nonNegativeArray",
            //increments "posCount" (quantity of non-negative values in "nonNegativeArray")
            //and increments "posIndex" (position in "nonNegativeArray" to insert value)
            } else {
                nonNegativeArray[posIndex++] = mainArray[i];
                posCount++;
            }
        }

        //trims "negativeArray" and "nonNegativeArray" to match quantities of elements
        //in each array
        negativeArray = Arrays.copyOf(negativeArray, negCount);
        nonNegativeArray = Arrays.copyOf(nonNegativeArray, posCount);
    }

    /**
     * Sorts both negative and non-negative integers in the given array.
     *
     * @param mainArray the array to sort
     * @param length the length of the array
     * @return the sorted array with negative and non-negative integers
     */
    public static Integer[] sortNegativesAndPositives(Integer[] mainArray, int length) {
        //calls method "negativeSeparator" to separate "mainArray" into an array of
        //non-negative values ("nonNegativeArray" and array of negative values
        //("negativeArray")
        negativeSeparator(mainArray, length);

        if (negCount > 0) {
            //If negativeArray contains at least one negative value, convert
            //"negativeArray" negative values to positive values
            for (int i = 0; i < negCount; i++) {
                negativeArray[i] = -negativeArray[i];
            }

            //sort positive values in negativeArray from smallest to largest
            RadixSortManager.radixSorter(negativeArray, negativeArray.length);

            //convert positive values in "negativeArray" back to negative values
            for (int i = 0; i < negCount; i++) {
                negativeArray[i] = -negativeArray[i];
            }
        }

        //sort the values in nonNegativeArray from largest to smallest
        if (posCount > 0) {
            RadixSortManager.radixSorter(nonNegativeArray, nonNegativeArray.length);
        }

        //call the "mergeNegativesAndPositives" method to fuse negative and positive
        //elements into a single array called "mergedArray"
        return mergeNegativesAndPositives();
    }
    /**
     * Merges the sorted negative and non-negative integers into a single array
     * called "mergedArray", which is sorted from smallest to largest, including
     * negative numbers, if any, and non-negative numbers, if any
     */
    private static Integer[] mergeNegativesAndPositives() {
        //Initializes "mergedArray" with a length of the combined lengths of
        //"negativeArray" and "nonNegativeArray"
        Integer[] mergedArray = new Integer[negativeArray.length + nonNegativeArray.length];

        //"mergeIndex" will determine what position in "mergeArray" at which to place a value
        //during distinct iterations through "negativeArray" and "nonNegativeArray", respectively
        int mergeIndex = 0;

        for (int i = negativeArray.length - 1; i >= 0; i--) {
            //Places negative values from "negativeArray" into "mergedArray"
            //BUT IN REVERSED ORDER TO ATTAIN AN ORDER OF SMALLEST TO LARGEST,
            //incrementing "mergeIndex"
            mergedArray[mergeIndex++] = negativeArray[i];
        }
        for(int p = 0; p < nonNegativeArray.length; p++){
            //Now that negative values have been placed into "mergedArray", add
            //the non-negative values into "mergedArray", incrementing "mergeIndex"
            mergedArray[mergeIndex++] = nonNegativeArray[p];
        }
        //return "mergedArray" which consists of all values from "mainArray," including
        //any negative and non-negative values, sorted from smallest to largest
        return mergedArray;
    }
}
