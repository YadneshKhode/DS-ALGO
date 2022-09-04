import java.util.*;

/*https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1#
https://www.youtube.com/watch?v=Lav6St0W_pQ&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=10


3 things we have to take care off- when the number of unique characters in window are less than k, more than k or
equal to k

To keep track of number of unique characters and their frequency we use HashMap We need frequency because
when the frequency hits 0 we need to remove the character from map

Scenario 1: When they are less than k, we just have to keep increasing the window size by doing end++,
Scenario 2: when size of map == k (unique characters inside window equals k) we have to extract ans store the max of curr ans vs the current size of window then keep on doing end++
because we want to maximize size of window
Scenario 3: map size > k (unique characters inside the window exceeds the K) in such scenario we keep doing start++ and keep decrementing the frequency of that character in the map
if the frequency hits 0 we need to remove the character from the map to reduce the size of the map. keep doing this until the condition becomes false 


*/

class Solution {
    public int longestkSubstr(String s, int k) {
        // code here
        int start = 0, end = 0, ans = -1;
        Map<Character, Integer> map = new HashMap<>();

        while (end < s.length()) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);
            if (map.size() > k) {
                while (map.size() > k) {
                    char c = s.charAt(start);
                    map.put(c, map.get(c) - 1);
                    if (map.get(c) == 0)
                        map.remove(c);
                    start++;
                }
            }
            if (map.size() == k) {
                ans = Math.max(ans, end - start + 1);
            }
            end++;
        }
        return ans;
    }
}
