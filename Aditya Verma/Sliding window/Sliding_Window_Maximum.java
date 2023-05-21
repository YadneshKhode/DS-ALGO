/*
while adding to deque i make sure the max number always remains at top so if i find a smaller number in deque compared to the number
that i am going to insert i will keep popping the deque from backside until it is empty or i find a number greater than my curr number in the deque
if i find a number greater than my number in the deque i will add my number from back side so greatest will remain at head and my smaller will
come after this, because the largest number that came before will be removed first from the sliding window  and after it is removed from
sliding window i need to have next maximum number ready as top of the queue.

https://www.interviewbit.com/problems/sliding-window-maximum/
https://www.youtube.com/watch?v=xFJXtB5vSmM&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=6
*/

import java.util.*;

public class Sliding_Window_Maximum {

    public int[] slidingMaximum(final int[] arr, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = arr.length;
        int res[] = new int[n - k + 1];
        int start = 0, end = 0, index = 0;

        while (end < n) {
            while (!deque.isEmpty() && deque.peekLast() < arr[end]) {
                deque.removeLast();
            }
            deque.addLast(arr[end]);

            if (end - start + 1 < k) {
                end++;
            } else {
                res[index] = deque.peekFirst();
                index++;
                if (arr[start] == deque.peekFirst())
                    deque.removeFirst();
                start++;
                end++;
            }
        }
        return res;
    }
}
