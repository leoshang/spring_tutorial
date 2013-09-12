package datastructure.algorithmus.simplesort;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 5/09/13
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */

/*public class SelectionSort<T extends Comparable<T>> {

    //Error: datastructure.algorithmus.simplesort.SelectionSort.this' cannot be referenced from a static context
    public static void doSort(T[] a){

    }
}*/

public class SelectionSort{

    public static <T extends Comparable<T>> void doSort(T[] array){
        //preconditions: array is not null and size is bigger than 2
        if(array==null || array.length<2) return;

        for (int outer =0; outer <= array.length-2; outer++){
            int min= outer; // marking the minimal element
            for (int inner = outer; inner<array.length-1; inner++){
                //compare array[min] with array[inner+1]
                if(array[min] != null && array[inner+1] != null){
                    if(array[min].compareTo(array[inner+1]) > 0){
                        min = inner+1;
                    }
                }

            }
            // end of loop, switch the ith element with min-th element.
            swap(array, outer, min);
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int pos1, int pos2){
        T tmp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = tmp;
    }
}
