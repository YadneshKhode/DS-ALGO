
//https://www.interviewbit.com/problems/generate-all-parentheses-ii/

import java.util.*;

public class Generate_All_paranthesis {
    List<String> list = new ArrayList<>();

    public String[] generateParenthesis(int n) {
        String output = "";
        int open = n;
        int close = n;
        solve(output, open, close);
        String[] strings = list.stream().toArray(String[]::new);
        return strings;
    }

    public void solve(String output, int open, int close) {
        String s1 = "";
        String s2 = "";
        if (open == 0 && close == 0) {
            list.add(output);
            return;
        }
        if (open == close) {
            s1 = output + "(";
            solve(s1, --open, close);
        } else if (open == 0) {
            s1 = output + ")";
            solve(s1, open, --close);
        } else {
            s1 = output + "(";
            s2 = output + ")";
            int tempOpen = open;
            solve(s1, --open, close);
            solve(s2, tempOpen, --close);
        }
    }
}
