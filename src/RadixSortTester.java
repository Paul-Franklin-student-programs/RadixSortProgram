import java.util.Arrays;
import java.util.Scanner;

/**
 * This class tests the functionality of program Radix Sort's sortation implementation
 * It initializes a main array, fills it with user-inputted values, sorts
 * both negative and non-negative Integers when applicable, and finally
 * prints the sorted array juxtaposed with original array
 */
public class RadixSortTester {
    /**
     * main method and test entry point.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //initializes empty list of type Integer
        Integer[] mainArray = new Integer[10];

        //prompts user for Integer inputs to populate the
        //mainArray before sortation
        System.out.println("Enter an array of 10 Integers:");

        //using try-with-resources exception handling, inputs 10 int user inputs
        try (Scanner scnr = new Scanner(System.in)) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Enter value #" + (i + 1) + ":");
                String scannedValue = scnr.nextLine();

                //converts each inputed String value "scannedValue"
                //into type Integer
                Integer newElement = Integer.valueOf(scannedValue);

                //adds Integer "newElement" to mainArray at current
                //iteration's index
                mainArray[i] = newElement;
            }

            //makes duplicate of mainArray for the purposes of printing
            //the unsorted mainArray at end of main method
            Integer[] mainArrayCopy = Arrays.copyOf(mainArray, mainArray.length);  // Used Arrays.copyOf to create a copy of the array

            //determines the sorted "finalArray" by calling sortNegativesAndPositives
            //method with mainArray and mainArray's length as arguments
            Integer[] finalArray = RadixSortNegativesManager.sortNegativesAndPositives(mainArray, mainArray.length);

            //prints unsorted mainArray by printing previously-made mainArrayCopy
            System.out.print("Unsorted array: ");
            RadixSortManager.arrayPrint(mainArrayCopy, mainArrayCopy.length);

            //prints finalArray (which is the sorted mainArray) by calling
            //"arrayPrint" method in RadixSortManager
            System.out.print("Sorted array: ");
            RadixSortManager.arrayPrint(finalArray, finalArray.length);

        //prints error message if exception occurs in main method
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid integers.");
        }
    }
}
