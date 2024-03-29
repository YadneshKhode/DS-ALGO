//Explanation - https://www.youtube.com/watch?v=20v8zSo2v18
//Leetcode - https://leetcode.com/problems/subarray-sum-equals-k/

import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rsum = sum - k;
            if (map.containsKey(rsum)) {
                count += map.get(rsum);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}