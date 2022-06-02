
//https://leetcode.com/problems/search-in-rotated-sorted-array/ 

//https://www.youtube.com/watch?v=r3pMQ8-Ad5s&list=PLgUwDviBIf0qYbL4TBaEWgb-ljVdhkM7R&index=6

/*
1 approach is to find the pivot element and apply binary search on the side in which the target lies.

but we are doing extra work of finding pivot even though time complexity is log n

below approach finds target without finding pivot
*/

public class Search_in_Rotated_Sorted_Array {

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] == target)
                return mid;

            /*
             * Whenever we calculate mid ALWAYS one part either low to mid or mid to high
             * (always including mid) will be sorted for sure.
             * for e.g. -> [4,5,6,7,0,1,2]
             * 
             * here mid is 7 and portion 4 to 7 is sorted and portion 7 to 2 is unsorted so
             * we check if our target lies in sorted or not if yes then apply binary search
             * there if not then remove that whole portion
             * 
             * for e.g. target is 0 and it does not lie in 4 to 7 so we do low = mid + 1
             * 
             * if lets say mid was 0 and we are trying to find 5 so we would have checked in
             * sorted part 0 to 2 if 5 lies in it or not if yes then do binary search in
             * this part by doing low = mid + 1 if no then do high = mid - 1 and apply
             * search again
             * 
             */
            // if left side is sorted (low to mid)
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // if right side is sorted (mid to high)

            else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else
                    high = mid - 1;
            }

        }
        return -1;
    }
}
