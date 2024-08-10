import java.util.Arrays;
import java.util.Scanner;

/**
 * This class tests the functionality of Radix Sort implementation in RadixSortManager.
 * It creates an instance of RadixSortManager, initializes an array of integers,
 * sorts the array using radix sort, and prints the sorted array.
 */
public class RadixSortTester {
    /**
     * main method and test entry point.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer[] mainArray = new Integer[10];
        int length = mainArray.length;

        System.out.println("Enter an array of 10 Integers:");

        try (Scanner scnr = new Scanner(System.in)) {  // Using try-with-resources for Scanner
            for (int i = 0; i < 10; i++) {
                System.out.println("Enter value #" + (i + 1) + ":");
                String scannedValue = scnr.nextLine();
                Integer newElement = Integer.valueOf(scannedValue);
                mainArray[i] = newElement;
            }

            Integer[] mainArrayCopy = Arrays.copyOf(mainArray, mainArray.length);  // Used Arrays.copyOf to create a copy of the array

            Integer[] finalArray = RadixSortNegativesManager.sortNegativesAndPositives(mainArray, length);

            System.out.print("Unsorted array: ");
            RadixSortManager.arrayPrint(mainArrayCopy, length);
            System.out.print("Sorted array: ");
            RadixSortManager.arrayPrint(finalArray, finalArray.length);
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid integers.");
        }
    }
}
