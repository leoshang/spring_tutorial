package datastructure.algorithmus.array;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 4/09/13
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayApp {
    public static void  main(String[] args) throws IOException{
        int[] arr;
        arr = new int[100];
        int nElems = 0;          // number of items
        int j;                   // loop counter
        int searchKey;           // key of item to search for
        arr[0] = 77;
        arr[1] = 99;
        arr[2] = 44;
        arr[3] = 55;
        arr[4] = 22;
        arr[5] = 88;
        arr[6] = 11;
        arr[7] = 00;
        arr[8] = 66;
        arr[9] = 33;
        nElems = 10;

        // display items in the loop
        displayAllItems(arr, nElems);

        searchKey = 66;
        //search item with key 66
        j = getIndexForItem(arr, nElems, searchKey);
        if(j == nElems){
            System.out.println("Can't find "+searchKey);
        }else {
            System.out.println("Found "+searchKey);
        }

        //delete item with key 55
        searchKey = 55;
        j= getIndexForItem(arr, nElems, searchKey);

        for (int k =j; k<nElems; k++){
            arr[k]=arr[k+1];
        }
        nElems--;

        displayAllItems(arr, nElems);

    }

    private static int getIndexForItem(int[] arr, int nElems, int searchKey) {
        int j;
        for (j=0; j<nElems; j++){
            if(arr[j] == searchKey){
                break;
            }
        }
        return j;
    }

    private static void displayAllItems(int[] arr, int nElems) {
        int j;
        for (j=0; j<nElems; j++){
            System.out.println(arr[j] + " ");
        }
        System.out.println("");
    }
}
