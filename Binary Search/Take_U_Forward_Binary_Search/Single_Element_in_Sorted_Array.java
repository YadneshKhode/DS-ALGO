public class Single_Element_in_Sorted_Array {
    /*
     * 
     * Leetcode - https://leetcode.com/problems/single-element-in-a-sorted-array/
     * 
     * 
     * Youtube link -
     * https://www.youtube.com/watch?v=PzszoiY5XMQ&list=PLgUwDviBIf0qYbL4TBaEWgb-
     * ljVdhkM7R&index=6
     * 
     * 
     * Key details in problem -> All elements except 1 element occurs twice we need
     * to find that 1 element, the array is sorted.
     * 
     * 
     * 
     * Assume array was not sorted then we could just XOR all elements and the last
     * element remaining is the single element that we are trying to find
     * 
     * 
     * because 2 xor 2 is 0 and 0 xor 3(any number) is 3
     * 
     * 
     * so if array is [1,2,3,2,3] 1 xor 2 xor 3 xor 2 xor 3 = 1
     * 
     * since 2 xor 2 will become 0 and 0 xor 1 will become 1
     * 
     * 
     * TC - O(N) SC - O(1)
     * 
     * 
     * 
     * Since the array is already sorted we can use this to reduce time complexity
     * to O(log n)
     * 
     * 
     * 
     * 
     * arr = [1,1,2,2,3,4,4,8,8];
     * 
     * 
     * 1) Notice the elements appearing on left side of Single element has 1st
     * element on even index and 2nd element on odd index ( for e.g. 1st occurance
     * of 1 is on 0th index and 2nd occurance is on 1st index)
     * 
     * 2) The elements that occur after the presence of single element has 1st
     * occurance on odd number and 2nd occurance on even index ( for e.g. 4 occurs
     * 1st on 5th index and occurs on 6th index in second occurance )
     * 
     * 3) So we can apply Binary search on these conditions, if we are on right side
     * of the single element we have to do high = mid - 1 and if we are on left side
     * of single element we have to do low = mid + 1
     * 
     * 
     * if mid is different from both neighbours it is the answer and return it
     * 
     */

    public int singleNonDuplicate(int[] nums) {

        /*
         * we are initialising high as 2nd last index of array because assume the single
         * element (our ans) is the last element in the array now we keep pushing low =
         * mid + 1
         * again and again and eventually low will be pointing to last and high will be
         * pointing to second last and our while condition would fail and we will return
         * arr[low]
         */

        int low = 0, high = nums.length - 2;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            /*
             * 1) Find mid index
             * 
             * For e.g. index is odd, now we check left side of index value
             * if it is same
             * 
             * Case 1: it is same it means we are on the left side of the single element
             * occurance and hence we need to go more towards right and hence low = mid + 1
             * 
             * arr = [1,1,2,2,3,4,4]
             * 
             * here mid value is 2 and on right on left of mid value is 2 as well and hence
             * we are on left side of single element occurance (desired element) and hence
             * now low = mid + 1
             * 
             * 
             * Case 2: It is not same it means exact vice versa of above
             * 
             */

            /*
             * 1) mid xor 1 === mid ^ 1
             * 2) even number (xyz) xor 1 gives us even number + 1(xyz+1)
             * 
             * for e.g. 6 ^ 1 = 7
             * 
             * 3) odd number xor 1 gives us odd number - 1
             * 
             * for e.g. 5 ^ 1 = 4
             * 
             * 
             * here we need to check if mid is odd we need to check left of mid
             * and if mid is even we need to check right of mid
             * 
             * since on left side of single elements 1st occurance of number is always on
             * even and 2nd occurance is on odd index
             * 
             * 
             * hence if mid is odd we look at left if it is same as arr[mid] then we move
             * right low = mid + 1 else high = mid - 1
             * 
             * 
             * so in below case if mid is odd we will automaically check the left of mid and
             * if mid is even we will automatically check right of mid
             * 
             */
            if (nums[mid] == nums[mid ^ 1])
                low = mid + 1;
            else
                high = mid - 1;
        }

        return nums[low];
    }
}
