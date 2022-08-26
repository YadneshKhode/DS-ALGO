//https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1

//https://www.youtube.com/watch?v=uUXXEgK2Jh8&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=4
import java.util.*;
import java.io.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); // Inputting the testcases
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            long a[] = new long[(int) (n)];
            // long getAnswer[] = new long[(int)(n)];
            String inputLine[] = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(inputLine[i]);
            }
            int k = Integer.parseInt(br.readLine().trim());

            Compute obj = new Compute();
            long answer[] = obj.printFirstNegativeInteger(a, n, k);
            int sz = answer.length;

            StringBuilder output = new StringBuilder();
            for (int i = 0; i < sz; i++)
                output.append(answer[i] + " ");
            System.out.println(output);

        }
    }
}

// } Driver Code Ends

// User function Template for Java

class Compute {

    public long[] printFirstNegativeInteger(long arr[], int n, int k) {
        Deque<Long> deque = new LinkedList<>();
        int start = 0, end = 0;
        long res[] = new long[n - k + 1];
        int x = 0;
        while (end < n) {
            if (arr[end] < 0) {
                deque.addLast(arr[end]);
            }
            if (end - start + 1 < k) {
                end++;
            } else if (end - start + 1 == k) {
                if (deque.size() == 0)
                    res[x++] = 0;
                else
                    res[x++] = deque.peek();
                if (deque.size() != 0 && deque.peek() == arr[start])
                    deque.poll();
                start++;
                end++;
            }
        }
        return res;
    }
}

// Second approach same logic

class Compute2 {

    public long[] printFirstNegativeInteger(long arr[], int n, int k) {
        long[] res = new long[n - k + 1];
        Deque<Long> deque = new ArrayDeque<>();
        int start = 0, end = -1, index = 0;

        while (end < n) {
            int size = end - start + 1;
            if (size < k) {
                end++;
                if (end < n && arr[end] < 0)
                    deque.addLast(arr[end]);
            } else if (size == k) {
                if (deque.isEmpty()) {
                    res[index++] = 0;
                } else {
                    res[index++] = deque.peekFirst();
                    if (arr[start] == deque.peekFirst())
                        deque.removeFirst();
                }
                start++;
            }
        }
        return res;
    }
}
