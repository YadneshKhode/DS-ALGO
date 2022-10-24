
// https://practice.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1/
public class Floor_And_Ceil_In_A_Sorted_Array {

    // Function to find floor of x
    // arr: input array
    // n is the size of array

    // The idea is to use normal binary search to find the X if we are able to find
    // it then
    // return its index
    // If we are not able to find it then the ending state will be like the low
    // pointer has passed high pointer
    // for eg - {1,2,4,5} x = 3
    // We are trying to find 3 and we are not able to find it so our "high" will be
    // at
    // index 1
    // and "low" will be at index 2
    // and the missing 3 should have been between this low and high and our high
    // will
    // be
    // pointing at the floor(the value just less than X) and low will be pointing
    // at Ceil
    // the value just more than X
    // We have to handle edge cases where 1) (low = n & high = n-1) 2) (low = 0 &
    // high = -1)
    // these are the 2 cases because low will be always ahead of high since it is
    // the breaking
    // condition

    static int findFloor(long arr[], int n, long x) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] < x)
                low = mid + 1;
            else
                high = mid - 1;
        }
        if (low == n && arr[low - 1] < x)
            return (low - 1);
        if (low == 0 && arr[low] > x)
            return -1;
        return low;

    }

}
