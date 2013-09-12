package datastructure.algorithmus.array;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 4/09/13
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LowArray {
    private double[] a;
    private int nElems;
    public LowArray(int size){
        a = new double[size];
        setElem(0, 77);
        setElem(1, 99);
        setElem(2, 44);
        setElem(3, 55);
        setElem(4, 22);
        setElem(5, 88);
        setElem(6, 11);
        setElem(7, 00);
        setElem(8, 66);
        setElem(9, 33);

    }

    public void setElem(int index, double value){
        a[index] = value;
        nElems++;
    }

    public void searchForItem(int searchKey){
        int j = getIndexForItem(a, nElems, searchKey);
        if(j == nElems){
            System.out.println("Can't find "+searchKey);
        }else {
            System.out.println("Found "+searchKey);
        }
    }

    public int deleteItem(int searchKey){
        int j= getIndexForItem(a, nElems, searchKey);

        for (int k =j; k<nElems; k++){
            a[k]=a[k+1];
        }
        return --nElems;

    }
    public void displayAllItems() {
        int j;
        for (j=0; j<nElems; j++){
            System.out.println(a[j] + " ");
        }
        System.out.println("");
    }

    private static int getIndexForItem(double[] arr, int nElems, int searchKey) {
        int j;
        for (j=0; j<nElems; j++){
            if(arr[j] == searchKey){
                break;
            }
        }
        return j;
    }
}
