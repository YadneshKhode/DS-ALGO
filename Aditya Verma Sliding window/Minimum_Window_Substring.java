import java.util.*;

/*

https://leetcode.com/problems/minimum-window-substring/submissions/

https://www.youtube.com/watch?v=iwv1llyN6mo&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=21

            1) character present in map so decrement frequency by 1 and if freq hits zero count--, end++
            2) character not present in map just end++
            3) While(count == 0) check if start is present in map if yes then increment freq by 1 and since freq's value was 0 we have to do count++, freq could be -1 or 1 or any other number apart from 0 and while incrementing on these values we dont have to increment count since count is the value that denotes the count of alphabets in string 't', when count = 0 it means all the characters in string 't' are present inside the window in exact or more amount of frequency as in string 't'    
            4) extract length when count == 0 and window size is minimum compared to previous window size
            */

//Latest solution by me as of 04-09-2022, old solution is below with comments but latest is optimised

class Solution {
    public String minWindow(String s, String t) {
        int n = s.length(), m = t.length(), start = 0, end = 0, minLength = Integer.MAX_VALUE, startIndex = 0,
                endIndex = 0;
        StringBuilder result = new StringBuilder();
        if (m > n)
            return result.toString();

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int count = map.size();

        while (end < n) {
            if (count > 0) {
                Integer freq = map.get(s.charAt(end));
                if (freq != null) {
                    if (freq == 1)
                        count--;
                    map.put(s.charAt(end), --freq);
                }
                end++; // we can write the end after below while loop then while appending
                       // stringbuilder in result we have to do i <= endIndex instead of i < endIndex
                       // this is because for e.g. start=0, end=0 now lets say my count has become 0 in
                       // while loop the value of endIndex will be 1 instead of 0 and hence this
                       // endIndex can go out of bounds as well.
            }
            while (count == 0) {
                if (minLength > end - start + 1) {
                    minLength = end - start + 1;
                    startIndex = start;
                    endIndex = end;
                }
                Integer freq = map.get(s.charAt(start));
                if (freq != null) {
                    map.put(s.charAt(start), freq + 1);
                    if (freq == 0)
                        count++;
                }
                start++;
            }
        }

        for (int i = startIndex; i < endIndex; i++) {
            result.append(s.charAt(i));
        }

        return result.toString();
    }
}

// class Solution {
// public static String minWindow(String s, String t) {

// int n = s.length();
// int m = t.length();

// if(n < m) return "";
// StringBuilder result = new StringBuilder();
// HashMap<Character,Integer> map = new HashMap<>();

// for(int k = 0; k < m; k++){
// char c = t.charAt(k);
// map.put(c,map.getOrDefault(c,0) + 1);
// }
// // count will keep track of characters with frequency more than 0
// // if frequency of all characters becomes 0 then it means the window contains
// 1 possible answer
// int count = map.size();
// int i = 0, j = 0, iIndex = -1, jIndex = -1, minLength = Integer.MAX_VALUE;

// while(j < n){
// char c = s.charAt(j);
// Integer currVal = map.get(c);
// // Make sure the key is never NULL and even the value is never null ony then
// use the below logic
// // character present in map so decrement by 1 and increment end pointer
// if(currVal != null){
// map.put(c,currVal - 1);
// if(currVal == 1) count--;
// }
// //If count ==0 it means all character frequency is 0 in the map
// //it means the characters inside the window satisfies the solution
// //so now we need to extract answer
// while(count == 0) {
// Integer containsStart = map.get(s.charAt(i));
// // Extracting answer since count == 0
// if(count == 0){
// //if window length is less than previous window length only then we will
// consider this answer
// if(minLength > (j-i+1)){
// minLength = j-i+1;
// //storing the start and end index so that we can extract the characters in
// the window later on and return the final answer
// iIndex = i;
// jIndex = j;
// }
// }
// // if the containsStart is null it means that character does not exist in the
// map and we can
// //increment the start pointer(i) carefree
// if(containsStart != null){
// // the current start pointer(i) is pointing to a character that is present in
// the map
// // so we need to increase the frequency of that character in the map because
// we are
// // going to remove it from our window and now our window has become deficit
// of 1 required character
// map.put(s.charAt(i), containsStart + 1);
// // if the frequency count of that character became 1 from 0 we need to
// increment count as the count keep track of the number of characters we still
// require
// if(containsStart == 0) count++;
// }
// // once we increment the start pointer and if our count is still 0 so it
// means our window size has shrunk, and hence we need to extract the answer
// once again as this might be the possible answer
// i++;
// }
// j++;
// }

// if(iIndex != -1 && jIndex != -1){
// for(int x = iIndex; x <= jIndex; x++){
// result.append(s.charAt(x));
// }
// }

// return result.toString();

// }
// }
