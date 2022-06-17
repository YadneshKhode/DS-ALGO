package Take_U_Forward_Binary_Search;

public class Kth_element_of_two_sorted_array {
    public long kthElement(int arr1[], int arr2[], int n, int m, int k) {
        if (n > m)
            return kthElement(arr2, arr1, m, n, k);
        int low = Math.max(0, k - m), high = Math.min(n, k);

        /*
         * Edge cases
         * arr1 = [7,12,14,15]
         * arr2 = [1,2,3,4,9,11]
         * 
         * 
         * low = (min no. of element) high = (max elements we can pick from arr1) for
         * given K
         * low = 0, high = 3 for k = 3 (look at arr1 since we are applying binary search
         * there)
         * 
         * low = 1, high = 4 for k = 7 (look at arr1 since we are applying binary search
         * there)
         * low is 1 because even if we pick all 6 elements from array2 we still need 1
         * element from array1
         * hence we have to select at least 1 element from array 1
         * 
         */

        while (low <= high) {
            int cut1 = (low + high) >> 1;
            int cut2 = k - cut1;

            // cut = 3 means before the cut there are 3 elements

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r1 = (cut1 == n) ? Integer.MAX_VALUE : arr1[cut1];
            int r2 = (cut2 == m) ? Integer.MAX_VALUE : arr2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return 0;

    }
}
