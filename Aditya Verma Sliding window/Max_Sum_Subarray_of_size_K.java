//https://practice.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
//https://www.youtube.com/watch?v=KtpqeN0Goro&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=3

import java.util.*;

class Solution {
    static long maximumSumSubarray(int K, ArrayList<Integer> Arr, int N) {
        int i = 0, j = 0;
        long sum = 0, max = Long.MIN_VALUE;

        // j-i+1 gives length of the window i is starting pointer j is end pointer of
        // the window
        // while we don't reach window length keep increasing end pointer(j)
        // and keep adding array elements in sum variable
        // once the condition is met (end pointer == k) we keep moving the window by
        // doing i++ and j++ and subtracting the value at ith position and adding value
        // at j++

        while (j < N) {
            sum += Arr.get(j);
            // now the window size is reached at K now just keep moving the window
            if (j - i + 1 == K) {
                max = Math.max(max, sum);
                sum -= Arr.get(i);
                i++;
            }
            j++;
        }
        return max;

    }
}
