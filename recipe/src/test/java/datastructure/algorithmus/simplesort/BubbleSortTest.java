package datastructure.algorithmus.simplesort;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 4/09/13
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */

public class BubbleSortTest {

    private BubbleSort<Integer> unsort;

    @Before
    public void init(){
        Random randomGenerator = new Random();
        Integer[] intArray = new Integer[10];
        //"Generating 10 random integers in range 0 ... 99."
        for(int i=0; i<10; i++){
            intArray[i] = randomGenerator.nextInt(100);
        }
        unsort = new BubbleSort<Integer>(intArray);
        for(int i=0; i<intArray.length; i++){
            System.out.print(intArray[i]);
            if(i!=intArray.length-1){
                System.out.print(",");
            }else {
                System.out.println();
            }

        }
        System.out.println("******************************************");
    }
    @Test
    public void testDoSort(){
        unsort.doSort();
        for(int i=0; i<unsort.getItems().length; i++){
            System.out.print(unsort.getItems()[i]);
            System.out.print(",");
        }
    }

    @Test
    public void testDoSortWithNull(){
        unsort.setItems(null);
        unsort.doSort();
    }

    @Test
    public void testDoSortWithZeroElements(){
        Integer[] zeroElements = new Integer[0];
        unsort.setItems(zeroElements);
        unsort.doSort();
    }

}
