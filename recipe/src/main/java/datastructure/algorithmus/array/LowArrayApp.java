package datastructure.algorithmus.array;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 4/09/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class LowArrayApp {
    public static void main(String[] args){
        LowArray arr = new LowArray(100);
        arr.displayAllItems();
        arr.searchForItem(26);
        arr.deleteItem(55);
        arr.displayAllItems();

    }
}
