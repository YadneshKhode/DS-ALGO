
import java.util.*;

public class sort_array_using_recursion {
    public static void main(String args[]) {
        System.out.println(sort2(new ArrayList<>(
                Arrays.asList(3, -2, 1, 4, 5, 6, 70))));

    }

    public static ArrayList<Integer> sort2(ArrayList<Integer> arr) {
        if (arr.size() == 1)
            return arr;
        int temp = arr.get(0);
        arr.remove(0);
        arr = sort2(arr);
        insert2(arr, temp);
        return arr;
    }

    public static void insert2(ArrayList<Integer> arr, int temp) {
        if (arr.size() == 0 || arr.get(0) >= temp) {
            arr.add(0, temp);
        } else {
            int temp1 = arr.remove(0);
            insert2(arr, temp);
            arr.add(0, temp1);
        }
    }

}