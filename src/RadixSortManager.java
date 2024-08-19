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
    public static void arrayPrint(Integer[] mainArray, int length) {
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
    private static void digitSort(Integer[] mainArray, int length, int exp) {
        //validate the mainArray argument
        if (mainArray == null) {
            System.out.println("mainArray cannot be null.");
            return;
        }

        //initialize digitCount in array to tally quantities of digits at
        //a given position in each element of mainArray
        int[] frequencyArray = new int[10];

        //populate frequencyArray by taking each mainArray element and extracting
        //from it the digit at position specified by argument "exp", and then
        //incrementing the frequencyArray element at the frequencyArray index that is
        //equal to the extracted digit minus one, thus incrementing the appropriate bucket
        for (int j = 0; j < length; j++) {
            frequencyArray[(mainArray[j] / exp) % 10]++;
        }

        //initializes cumulativeFrequencyArray to hold the values that show where the next
        // batch of equal elements starts in the sorted order delineated by frequencyArray.
        int cumulativeFrequencyArray[] = new int[10];

        //calculates prefix sum, thus altering frequencyArray by making each element
        // the sum of that element and the element immediately preceding it. Element at
        //index 0 does not change. After loop terminates, the updated initialArray has been
        //copied to array cumulativeFrequencyArray for subsequent computation
        for (int j = 1; j < 10; j++) {
            frequencyArray[j] += frequencyArray[j - 1];
            cumulativeFrequencyArray[j] = frequencyArray[j];
        }

        //create output array to represent mainArray rearranged via digits at a certain
        //digit position in the mainArray elements; populate array with 0 at each index
        Integer[] outputArray = new Integer[length];
        Arrays.fill(outputArray, 0);


        for (int j = length - 1; j >= 0; j--) {
            outputArray[frequencyArray[(mainArray[j] / exp) % 10] - 1] = mainArray[j];
            frequencyArray[(mainArray[j] / exp) % 10]--;
        }

        System.arraycopy(outputArray, 0, mainArray, 0, length);
    }

    /**
     * Performs radix sort on the given array.
     */
    static void radixSorter(Integer[] array, int length) {

        //Handles null and empty arrays
        if (array == null || length == 0) {
            return;
        }
        //Calls "arrayMax" helper method to provide
        //the maximum value in the array
        int m = arrayMax(array, length);

        //Iteratively calls helper method "digitSort"
        //to sort array values starting with the digits
        //at their units places and then escalating to
        //the highest place of the maximum value
        for (int exp = 1; m / exp > 0; exp *= 10) {
            digitSort(array, length, exp);
        }
    }

    /**
     * Finds the maximum value in an array of Integer values.
     */
    private static int arrayMax(Integer[] array, int length) {

        //returns value 0 if array is null or has length of 0
        if (array == null || length == 0) {
            return 0;
        }
        //Initializes preliminary maximum value of array
        //as the first element in the array
        int maxValue = array[0];

        //Iterates through array to determine true maximum
        //value, swapping each time a value at the current
        //index is larger than the current maximum value
        for (int j = 1; j < length; j++) {
            if (array[j] > maxValue) {
                maxValue = array[j];
            }
        }
        //Returns the maximum value in the argument array
        return maxValue;
    }
}
