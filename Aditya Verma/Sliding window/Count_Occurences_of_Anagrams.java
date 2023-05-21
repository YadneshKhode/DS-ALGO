import java.util.*;
//https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1#
//https://www.youtube.com/watch?v=MW4lJ8Y0xXk&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=5

/*
approach:
created 26 size array to keep track of frequency of alphabets in the pattern(pat)
used set to determine unique characters in pattern initialised count with set size

1) until window size(k) is reached keep incrementing end pointer because the anagrams size will be same as the pattern
2) whenever we increment end pointer we have to decrement its frequency
3) if the frequency for 1 character becomes 0 it means we have received adequate amount of that character(same amount of character in pattern)
4) so we decrement the count by 1
5) when the window size is reached there are 2 possibilities either we have found our anagram or not
6) if count = 0 then we have found our anagram because we decremented count whenever frequency of particular character had reached 0 so when all characters frequency was 0 
it means all characters in pattern are now present in the current window hence we increment our result variable by 1
7)  now its time to slide window but before sliding we have to check if the start pointer is pointing to character that is present in the pattern if not then we can
increment without any worries as it wont affect anything but if it points to character present in pattern then we have to increment its frequency as we are removing
it from window and now we lack that particular character to form our anagram and if the frequency has become 1 that means it was 0 earlier so we have to increment count as well
because count keep tracks of unique characters so we increment count whenever the frequency becomes 1 from 0 so next time when we increment the frequency from 1 to 2 we wont increment
count because we need unique characters


We need to allow frequency to become negative and whenever it hits 0 we have to decrement count frequency going negative means we have excess of that particular character
in the window

*/
class Solution {

    int search(String pat, String txt) {
        int start = 0, end = 0, counter = 0, result = 0, n = txt.length();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < pat.length(); i++) {
            map.put(pat.charAt(i), map.getOrDefault(pat.charAt(i), 0) + 1);
        }

        counter = map.size();

        while (end < n) {
            char currEndCharacter = txt.charAt(end);
            if (map.containsKey(currEndCharacter)) {
                int currCharacterFrequency = map.get(currEndCharacter);
                map.put(currEndCharacter, currCharacterFrequency - 1);
                if (currCharacterFrequency - 1 == 0)
                    counter--;
            }

            if (end - start + 1 == pat.length()) {
                if (counter == 0)
                    result++;

                char currStartCharacter = txt.charAt(start);

                if (map.containsKey(currStartCharacter)) {
                    int currCharacterFrequency = map.get(currStartCharacter);
                    map.put(currStartCharacter, currCharacterFrequency + 1);
                    if (currCharacterFrequency == 0)
                        counter++;
                }

                start++;
            }

            end++;

        }
        return result;
    }

    int search2(String pat, String txt) {
        // code here
        int start = 0, end = -1, n = txt.length(), k = pat.length(), result = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            map.put(pat.charAt(i), map.getOrDefault(pat.charAt(i), 0) + 1);
        }
        int count = map.size();
        while (end < n) {
            int size = end - start + 1;
            if (size < k) {
                end++;
                if (end < n) {
                    Character endChar = txt.charAt(end);
                    Integer freqOfEndChar = map.get(endChar);
                    if (freqOfEndChar != null) {
                        map.put(endChar, freqOfEndChar - 1);
                        if (freqOfEndChar == 1)
                            count--;
                    }
                }

            } else {
                if (count == 0) {
                    result++;
                }
                Character startChar = txt.charAt(start);
                Integer freqOfStartChar = map.get(startChar);
                if (freqOfStartChar != null) {
                    map.put(startChar, freqOfStartChar + 1);
                    if (freqOfStartChar == 0)
                        count++;
                }
                start++;
            }
        }
        return result;
    }
}
