import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Daniel W innes 101067175
 */
public class Assignment4 {
    private static <T> Boolean inputValidation(T[] theArray, int n) {
        //check if theArray and n are valid for use by the sorting algorithm
        if (theArray == null) {
            throw new IllegalArgumentException("theArray can not be null");
        } else if (theArray.length == 0) {
            throw new IllegalArgumentException("theArray can not be empty");
        } else if (n != theArray.length) {
            throw new IllegalArgumentException("n should be the array length on the first call");
        } else {
            return true;
        }
    }

    /**
     * Sort theArray in in place using a recursive version of selection sort.
     *
     * @param theArray the array to sort
     * @param <T>      the data type of the members of the array
     */
    public static <T extends Comparable<? super T>> void recursiveSelectionSort(T[] theArray) {
        recursiveSelectionSort(theArray, theArray.length);
    }

    /**
     * Sort theArray in in place using a recursive version of selection sort.
     * This should be private but can't be because of the assignment restrictions.
     *
     * @param theArray the array to sort
     * @param n        the split point of the array should be array length on the first call
     * @param <T>      the data type of the members of the array
     */
    public static <T extends Comparable<? super T>> void recursiveSelectionSort(T[] theArray, int n) {
        if (inputValidation(theArray, n))
            RSS(theArray, n);
    }

    private static <T extends Comparable<? super T>> void RSS(T[] theArray, int n) {
        //run selection sort from the max end
        if (n != 0) {
            T temp;
            int iMax = n - 1;
            //find index of the Max value
            for (int i = n - 2; i >= 0; i--) {
                if (theArray[i].compareTo(theArray[iMax]) > 0) {
                    iMax = i;
                }
            }
            //swap the max and the value at the end of the split point
            if (iMax != n - 1) {
                temp = theArray[n - 1];
                theArray[n - 1] = theArray[iMax];
                theArray[iMax] = temp;
            }
            RSS(theArray, n - 1);
        }
    }

    /**
     * Sort theArray in in place using a recursive version of bubble sort.
     *
     * @param theArray the array to sort
     * @param <T>      the data type of the members of the array
     */
    public static <T extends Comparable<? super T>> void recursiveBubbleSort(T[] theArray) {
        recursiveBubbleSort(theArray, theArray.length);
    }

    /**
     * Sort theArray in in place using a recursive version of bubble sort.
     * This should be private but can't be because of the assignment restrictions.
     *
     * @param theArray the array to sort
     * @param n        the split point of the array should be the array length on the first call
     * @param <T>      the data type of the members of the array
     */
    public static <T extends Comparable<? super T>> void recursiveBubbleSort(T[] theArray, int n) {
        if (inputValidation(theArray, n))
            rbs(theArray, n);
    }

    private static <T extends Comparable<? super T>> void rbs(T[] theArray, int n) {
        //run optimized bubble sort
        if (n > 0) {
            //index of last swapped value
            int swapped = 0;
            T temp;
            for (int i = 0; i < n - 1; i++) {
                if (theArray[i].compareTo(theArray[i + 1]) > 0) {
                    temp = theArray[i];
                    theArray[i] = theArray[i + 1];
                    theArray[i + 1] = temp;
                    swapped = i + 1;
                }
            }
            rbs(theArray, swapped);
        }
    }

    /**
     * Test if str is in the language.
     *
     * @param str the string to test
     * @return if the str is in the language
     */
    public static boolean isInLanguage(String str) {
        if (str.length() % 2 == 0 || str.isEmpty())
            return false;
        Stack<Character> wprime = new Stack<>();
        Queue<Character> w = new LinkedList<>();
        for (int i = 0; i < str.length() / 2; i++) {
            w.add(str.charAt(i));
            wprime.push(str.charAt(str.length() / 2 + 1 + i));
        }
        while (!wprime.isEmpty()) {
            if (w.peek() != wprime.pop()) {
                return false;
            }
            w.remove();
        }
        return true;
    }


    /**
     * Convert the str to a int
     * This is a horrible way of solving the problem but the assignment demands you use a queue
     * return Integer.parseInt(str.replaceAll("\\D+","")); is a one line solution
     *
     * @param str the string to convert
     * @return the str as a int
     */
    public static int convertToNumber(String str) {
        Queue<Integer> outputQueue = new LinkedList<>();
        int output = 0;
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (Character.isDigit(c)) {
                outputQueue.add(Character.getNumericValue(c));
            }
        }
        int pow = (int) Math.pow(10, outputQueue.size() - 1);
        int size = outputQueue.size();
        for (int i = 0; i < size; i++) {
            try {
                output = add(output,outputQueue.peek() * pow);
                outputQueue.remove();
                pow /= 10;
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("str parse to a number greater then the mex value of a Integer");
            }
        }
        return output;
    }

    private static int add(final int num1, final int num2) throws ArithmeticException {
        //check if the addition will results in a overflow
        int result = num1 + num2;
        if (((num1 & num2 & ~result) | (~num1 & ~num2 & result)) < 0)
            throw new ArithmeticException("int overflow add(" + num1 + ", " + num2 + ")");
        return result;
    }
}
