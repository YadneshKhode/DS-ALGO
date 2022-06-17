package Take_U_Forward_Binary_Search;

//https://leetcode.com/problems/median-of-two-sorted-arrays/

//https://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
//https://www.youtube.com/watch?v=NTop3VTjmxk&list=PLgUwDviBIf0qYbL4TBaEWgb-ljVdhkM7R&index=21

/*
 * n1 = nums1.length
 * n2= nums2.length
 * 
 * NAIVE Approach
 * 1st approach is make new array of size n1+n2 and add all elements in it and sort it and return median
 * 
 * TC - (n1+n2) log (n1+n2)
 * SC - (n1+n2)
 * 
 * 
 * 2) Using technique of merge sort
 * have 2 pointers p1 and p2 at 0th index of both arrays respectively
 * 
 * think of it like merging 2 sorted arrays into 1
 * 
 * first check if p1 <= p2 if yes p1++ or else p2++ we keep doing this until counter <= (n1+n2) / 2 because lets say we have total 9 elements we need 5th element 9 / 2 = 4; we run loop 5 times from 0 upto 4  
 * 
 * 
 * loop counter inside keep track of current array element and prev (current-1) element (in case the length is even we need to return average of prev and curr) 
 * 
 * make sure to cover edge cases where both the medians (in case of even length) lie in array1 or array2
 * 
 * so if we have reached end of array1 we just keep incrementing array2 pointer and vice-versa
 * 
 * 
 */

class Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArraysCounterMethod(int[] nums1, int[] nums2) {

        int p1 = 0, p2 = 0, n1 = nums1.length, n2 = nums2.length, m1 = -1, m2 = -1;

        for (int count = 0; count <= ((n1 + n2) >>> 1); count++) {
            m2 = m1;
            if (p1 == n1) {
                m1 = nums2[p2++];
            } else if (p2 == n2) {
                m1 = nums1[p1++];
            } else {
                if (nums1[p1] <= nums2[p2]) {
                    m1 = nums1[p1++];
                } else {
                    m1 = nums2[p2++];
                }
            }
        }
        if (((n1 + n2) & 1) == 0) {
            return (m1 + m2) / 2.0;
        }
        return m1;
    }

    /*
     * TC = O(log(min(n1,n2)))
     * SC = O(1)
     */
    public double findMedianSortedArraysOptimised(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArraysOptimised(nums2, nums1);

        // having high = length is essential if we do length - 1 it gives wrong answer

        // consider nums1 = [1,3] nums2 = [2]
        /*
         * in above scenario
         * 
         * low = 0 high = 2
         * 
         * cut1 = 1 // if we had taken high = 1 (length - 1) the cut's value would have
         * been 0 and everything would be messed up
         */
        int low = 0, high = nums1.length, n1 = nums1.length, n2 = nums2.length;

        while (low <= high) {
            int cut1 = (low + high) >> 1;

            /*
             * cut = 3 means before the cut there are 3 elements
             * odd length -> 1 2 3 4 5 6 7 8 9 10 11
             * ans is 6 and we want it to be in left half so we can just do
             * Max(cut1-1,cut2-2)
             * to make sure it is left half we do 11+1/2 instead of 11/2
             * 
             * this formula works for even numbers as well suppose size is 10 we do 10+1/2 =
             * 5
             */

            int cut2 = ((n1 + n2 + 1) >> 1) - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                if (((n1 + n2) & 1) == 0) {
                    // dividing by 2.0 is essential since return type is double if we use >> 1 it
                    // wont consider the decimals and we want it to consider decimal for eg 9/2
                    // should return 4.5 and not 4
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }
                return Math.max(left1, left2);
            } else {
                if (left1 > right2) {
                    high = cut1 - 1;
                } else {
                    low = cut1 + 1;
                }
            }

        }
        return 0;
    }

}