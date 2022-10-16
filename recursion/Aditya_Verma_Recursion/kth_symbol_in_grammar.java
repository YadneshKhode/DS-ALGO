package recursion.Aditya_Verma_Recursion;
//https://leetcode.com/problems/k-th-symbol-in-grammar/
public class kth_symbol_in_grammar {
    public int kthGrammar(int n, int k) {
        if(n == 1 && k == 1) return 0;
        int mid = ((int)Math.pow(2,n-1)) >> 1;
        if(k <= mid) return kthGrammar(n-1,k);
        else return kthGrammar(n-1,k-mid) == 0 ? 1 : 0;
    }
}