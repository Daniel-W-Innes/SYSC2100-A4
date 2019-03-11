import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Assignment4 {

    public static <T extends Comparable<? super T>> void recursiveSelectionSort (T[] theArray){
        recursiveSelectionSort(theArray,0);
    }

    public static <T extends Comparable<? super T>> void recursiveSelectionSort (T[] theArray, int n){
        if(n != theArray.length){
            T temp;
            int iMin = n;
            for (int i = n + 1; i < theArray.length; i++) {
                if(theArray[i].compareTo(theArray[iMin]) < 0){
                    iMin = i;
                }
            }
            if(iMin != n){
                temp = theArray[n];
                theArray[n] = theArray[iMin];
                theArray[iMin] = temp;
            }
            recursiveSelectionSort(theArray,n + 1);
        }
    }

    public static <T extends Comparable<? super T>> void recursiveBubbleSort (T[] theArray){
        recursiveBubbleSort(theArray, theArray.length);
    }

    public static <T extends Comparable<? super T>> void recursiveBubbleSort (T[] theArray, int n){
        if(n > 0){
            int swapped = 0;
            T temp;
            for (int i = 0; i < n - 1; i++) {
                if(theArray[i].compareTo(theArray[i+1]) > 0){
                    temp = theArray[i];
                    theArray[i] = theArray[i+1];
                    theArray[i+1] = temp;
                    swapped = i + 1;
                }
            }
            recursiveBubbleSort(theArray,swapped);
        }
    }

    public static boolean isInLanguage (String str){
        if(str.length() % 2 == 0 || str.isEmpty())
            return false;
        Stack<Character> wprime = new Stack<>();
        Queue<Character> w = new LinkedList<>();
        for (int i = 0; i < str.length()/2; i++) {
            w.add(str.charAt(i));
            wprime.push(str.charAt(str.length()/2+1+i));
        }
        while (!wprime.isEmpty()){
            if (w.peek() != wprime.pop()){
                return false;
            }
            w.remove();
        }
        return true;
    }

    public static int convertToNumber (String str){
        Queue<Integer> outputQueue = new LinkedList<>();
        int output = 0;
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if(Character.isDigit(c)){
                outputQueue.add(Character.getNumericValue(c));
            }
        }
        int pow = (int) Math.pow(10,outputQueue.size()-1);
        int size = outputQueue.size();
        for (int i = 0; i < size; i++) {
            assert (((double)output) + outputQueue.peek() * pow < Integer.MAX_VALUE);
            output += outputQueue.peek() * pow;
            outputQueue.remove();
            pow /= 10;
        }
        return output;
    }

    public static int convertToNumberV2 (String str){
        return Integer.parseInt(str.replaceAll("\\D+",""));
    }
}
