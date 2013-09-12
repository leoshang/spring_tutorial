package datastructure.algorithmus.simplesort;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 5/09/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */

/*

            *
         0  1  2  3  4     N-1
        [] [] [] [] [] ... []
        |<-|
               *
         0  1  2  3  4     N-1
        [] [] [] [] [] ... []
        |  |<-|
        |<----|
                  *
         0  1  2  3  4     N-1
        [] [] [] [] [] ... []
        |  |  |<-|
        |  |<----|
        |<-------|

                            *
         0  1  2  3  4     N-1
        [] [] [] [] [] ... []



 */
public class InsertionSort {

    public static <T extends Comparable<T>> void doSort(T[] array){
        //outer loop 1 to N-1
        for(int outer =1; outer <= array.length-1; outer++){
            //mark player

            /*
            WRONG: no swap, just copy the marked player
            int marker = outer;
            for(int inner = outer-1; inner >=0; inner--){
                //compare mark with each inner element
                if(array[marker]!=null && array[inner]!=null){
                    if(array[inner].compareTo(array[marker]) >0){
                        //do swap if mark's left element is bigger than mark.
                        swap(array, inner, marker);
                        //update mark
                        marker = inner;
                    }
                }
            }*/

            T marked_player = array[outer];
            int vacancy = outer;
            for(int inner = outer-1; inner >=0; inner--){
                //compare marked_player with each inner element
                if(marked_player!=null && array[inner]!=null){
                    if(array[inner].compareTo(marked_player) >0){
                        //no swap, just shift.
                        array[vacancy] = array[inner];
                        //update mark
                        vacancy --;
                    }
                }
            }
            array[vacancy] = marked_player;
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int pos1, int pos2){
        T tmp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = tmp;

    }
}
