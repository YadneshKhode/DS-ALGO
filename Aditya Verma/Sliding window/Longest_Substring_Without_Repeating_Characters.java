
/*

https://leetcode.com/problems/longest-substring-without-repeating-characters/
https://www.youtube.com/watch?v=L6cffskouPQ&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=11

1) Check if end pointer character is in set if not then add in the set and increment the end pointer and keep recalculating the result
 as the string of unique character keeps incresing keep doing this until the condition is false basically we need set to keep track of unique characters


2) once the condition is false we know that the current character is present in the map so the new unique character string will start from the next character 
where we encountered the duplicate character

For eg: 
String s = abcabcbb

when end pointer reaches 4th index (b) we know b exists before this index as the set returns true now the new string (the new potential ans) 
will start from index 2 because we cannot take index 1 as the b is already present in our window now to reach this 2nd index from where our new 
potential answer will begin we have to keep on incrementing start until the start pointer is not equal to 'b' then we just increment start only once to go to 
next character the beginning of our new potential answer ('c' - 2nd index)


*/
import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        int result = 0, start = 0, end = 0;
        Set<Character> set = new HashSet<>();

        while (end < s.length()) {
            char c = s.charAt(end);
            if (!set.contains(c)) {
                set.add(c);
                result = Math.max(result, end - start + 1);
            } else {
                while (s.charAt(start) != c) {
                    set.remove(s.charAt(start));
                    start++;
                }
                start++;
            }
            end++;
        }
        return result;
    }
}