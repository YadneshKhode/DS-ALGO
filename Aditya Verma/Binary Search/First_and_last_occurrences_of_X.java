import java.util.*;

// First and last occurrences of X

public class First_and_last_occurrences_of_X {
    public static void main(String[] args) {
        Solution s = new Solution();
        ArrayList<Integer> arr = new ArrayList<>();
        int[] arrint = new int[] { 1, 2, 3, 3, 4, 5, 6, 7 };
        arr = s.firstAndLast(arrint, arrint.length, 3);
        System.out.println(arr);
    }
}

class Solution {
    public ArrayList<Integer> firstAndLast(int arr[], int n, int x) {
        // Code here
        ArrayList<Integer> res = new ArrayList<>();

        int first = getFirstIndex(arr, n, x);
        int second = getLastIndex(arr, n, x);

        if (first == -1 && second == -1) {
            res.add(-1);
        } else {
            res.add(first);
            res.add(second);
        }

        return res;
    }

    // when we find mid == key just keep the Binary search on in the left side of
    // the mid
    // and whenever we find mid == key just update the result with new index of
    // leftmost key found
    public int getFirstIndex(int arr[], int n, int x) {
        int result = n, low = 0, high = n - 1;
        // low <= high because if there are 2 elements in array for e.g. x = 10 and
        // arr=[10,10]
        // to handle this condition we need to have low <= high
        // another e.g. x = 3 and { 1, 2, 3, 3, 4, 5, 6, 7 }; if we dont do the <= then
        // ans will be [3,3] instead of [2,3]
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] == x) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid] < x)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return result == n ? -1 : result;

    }

    public int getLastIndex(int arr[], int n, int x) {
        int result = -1, low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] == x) {
                result = Math.max(result, mid);
                low = mid + 1;
            } else if (arr[mid] < x)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return result;
    }
}