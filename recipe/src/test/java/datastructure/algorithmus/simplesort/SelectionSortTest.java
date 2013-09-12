package datastructure.algorithmus.simplesort;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 5/09/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class SelectionSortTest {

    private Integer[] array;
    Random randomGenerator;
    @Before
    public void init(){
        randomGenerator = new Random();
        array = new Integer[20];
        for(int i=0; i<array.length; i++){
            array[i]= randomGenerator.nextInt(100);
            System.out.print(array[i] + ",");
        }
        System.out.println();
        System.out.println("**********************************************************");
    }
    
    @Test
    public void testSort(){
        SelectionSort.doSort(array);
        for(int i=0; i<array.length; i++){
           System.out.print(array[i] + ",");
        }
        System.out.println();
        System.out.println("**********************************************************");
    }
}
