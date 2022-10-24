package yetToBePlaced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class BoilerplateForCompetitiveProgramming {
    static PrintWriter out;

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();
        int testCases = sc.nextInt();
        while (testCases-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int indicator1 = 0, indicator2 = 0;
            for (int i = 0; i < arr.length; i++) {
                int count = 0;
                for (int j = i; (j <= (i << 1) + 1) && (j < arr.length); j++) {
                    if (arr[j] != i + 1)
                        break;
                    count++;
                }
                if (count == i + 1)
                    indicator2++;
            }
            for (int i = arr.length - 1, count1 = 0; i >= 0; i -= count1) {
                int x = i;
                count1 = 0;
                int curr = arr[i];
                while (x >= 0 && x >= i - curr) {
                    if (arr[x--] != curr)
                        break;
                    count1++;
                }
                if (count1 == curr)
                    indicator1++;
            }

            out.println("indicator1 = " + indicator1);
            out.println("indicator2 = " + indicator2);
            out.println("expecetd ans = " + Math.abs(indicator1 - indicator2));
        }
        out.flush();
    }
}
