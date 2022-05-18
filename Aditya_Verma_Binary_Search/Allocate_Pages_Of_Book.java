package Aditya_Verma_Binary_Search;

public class Allocate_Pages_Of_Book {
    // https://www.youtube.com/watch?v=2JSQIhPcHQg&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=40
    class Solution {
        // Function to find minimum number of pages.
        public int findPages(int[] arr, int n, int m) {
            // Your code here
            if (m > n)
                return -1; // If Number of students exceed number of books then return -1
            // because as per question we need to assign at least 1 book
            int low = 0, high = 0, mid = 0;

            // low will always be the max of the array and highest would be the sum of all
            // the array elements
            // watch video for better understanding
            // The number of pages will never exceed the total number of pages hence max is
            // sum of all array
            // Even i have doubts about low
            for (int i = 0; i < arr.length; i++) {
                low = Math.max(low, arr[i]);
                high += arr[i];
            }

            while (low <= high) {
                mid = (low + high) >>> 1;
                // System.out.print(mid);
                if (isValid(arr, mid, m))
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return low;
            // return low because take e.g. of N = 4 arr={12,34,67,90} m = 2
            // Here mid is 112 mid might not necessarily point to the answer
            // But when the condition breaks the high will be behind low and low will be our
            // answer

        }

        public boolean isValid(int[] arr, int mid, int m) {

            // count means number of students
            // mid is the max number of pages the student can read
            // m is the exact number of students we need to allocate book

            int sum = 0, count = 1;
            // Initialise count = 1 because count represents number of students and
            // initially we are allocating book to 1 student so the count should be 1
            for (int i = 0; i < arr.length; i++) {
                // Keep adding array elements until the allowed limit(mid) is reached
                sum += arr[i];
                if (sum > mid) {

                    // We reach here when the allocated limit is reached and increment count of
                    // students because now the limit of max number of pages that can be read by
                    // student
                    // (mid) has been reached

                    // System.out.println("SUM = "+ sum);
                    count++; // This count is incremented when 1st student can't read more
                    // number of books and it becomes count = 2
                    // remember we initialised count with 1 instead of 0 if we had initialised
                    // count with 0 then 1 student will always be counted less since we would be
                    // allocating books to 0th student first time
                    sum = arr[i];
                }
                if (count > m) {
                    // System.out.println("--in---"+count);
                    return false;
                }
            }
            // System.out.println("--out---"+count);
            return true;

        }
    };
}
