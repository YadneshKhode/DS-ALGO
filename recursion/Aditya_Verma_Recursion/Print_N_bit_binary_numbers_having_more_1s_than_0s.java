package recursion.Aditya_Verma_Recursion;

// https://practice.geeksforgeeks.org/problems/print-n-bit-binary-numbers-having-more-1s-than-0s0252/1#
import java.util.*;

public class Print_N_bit_binary_numbers_having_more_1s_than_0s {

    static List<String> list = new ArrayList<>();

    public static void main(String args[]) {
        ArrayList<String> res = generateParenthesis(0);
        for (String string : res) {
            System.out.println(string);
        }
    }

    public static ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        solve(result, 0, 0, "", n);
        return result;
    }

    public static void solve(ArrayList<String> result, int numOfOne, int numOfZero, String currAns, int n) {
        if (currAns.length() == n) {
            result.add(currAns);
            return;
        }
        solve(result, numOfOne + 1, numOfZero, currAns + "1", n);
        if (numOfOne > numOfZero + 1) {
            solve(result, numOfOne, numOfZero + 1, currAns + "0", n);
        }
    }
}
