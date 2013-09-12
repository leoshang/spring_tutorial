package datastructure.algorithmus.simplesort;

import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 4/09/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleSort<T extends Comparable<T>> {
    private T[] unsort;

    public BubbleSort(T[] unsort){
        this.unsort = unsort;
    }

    public T[] getItems(){
        return this.unsort;
    }

    public void setItems(T[] unsort){
        this.unsort = unsort;
    }

    public void doSort(){
        //preconditions: unsort.length is 0 or unsort is null
        Preconditions.checkNotNull(unsort, "the elements to be sorted is null");
        Preconditions.checkArgument(unsort.length ==0, "elements length is zero");
        for (int outer = unsort.length -1; outer > 1 ; outer--){
            for(int inner = 0; inner < outer; inner++){
                if(unsort[inner]!=null && unsort[outer]!=null){
                    if(unsort[inner].compareTo(unsort[outer]) >0){
                        //do swap
                        T tmp = unsort[inner];
                        unsort[inner] = unsort[outer];
                        unsort[outer] = tmp;
                    }
                }

            }
        }
    }



}
