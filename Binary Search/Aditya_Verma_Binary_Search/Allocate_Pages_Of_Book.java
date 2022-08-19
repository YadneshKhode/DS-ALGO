package Aditya_Verma_Binary_Search;

/*
 * Related Problems For Practice :
 * Book Allocation Problem (GFG)
 * Aggressive cow (spoj)
 * Prata and roti (spoj)
 * EKO (spoj)
 * Google kickstart A Q-3 2020
 */
public class Allocate_Pages_Of_Book {
    // https://www.youtube.com/watch?v=2JSQIhPcHQg&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=40
    // ye wala mast hai ekdum (comments) ->
    // https://www.youtube.com/watch?v=gYmWHvRHu-s
    class Solution {
        // Function to find minimum number of pages.
        public int findPages(int[] arr, int n, int m) {
            // Your code here

            /*
             * IMPORTANT POINTS IN QUESTION
             * 
             * 1) The book allocation done is contiguous
             * for eg arr = [40,50,10,55]
             * 
             * student1 can get 40,50 or 50,10,55 and so on he cannot get 40,10 or 50,55
             * that is in discontinuous manner
             * 
             * 2) The pages in the book cannot be distributed
             */
            if (m > n)
                return -1; // If Number of students exceed number of books then return -1
            // because as per question we need to assign at least 1 book
            int low = 0, high = 0, mid = 0;

            // low will always be the max of the array and highest would be the sum of all
            // the array elements (in striver video he said low will be minimum of array but
            // it should be max of array)
            // low will be max of array because lets say we have array = {1,90,2,3} student
            // = 4, so since the rule states each and every book in the array should be
            // alloted and every student should get
            // at least 1 book we can safely say 1 student will be allocated book with 90
            // pages and this 90 will be our answer
            // because we have to return number of max pages allocated to a student in such
            // a way that the allocation of pages
            // makes sure there is no other combination of allocation in which the student
            // would have been allocated less number of pages (for e.g. student = 2 arr =
            // {10,20,30,40} student 1 allocated 10 and student 2 allocated (20+30+40) this
            // is not valid as there exists other allocation combination student 1 =
            // 10,20,30 and student 2 = 40 here student 2 gets max of 40 pages and no other
            // allocation combination gives less than 40 )

            // for more clarity of above you can watch striver video's comments and NOT the
            // video itself

            // The number of pages will never exceed the total number of pages hence max is
            // sum of all array
            // for e.g. student = 1 arr = {1,2,3,4} here the maximum can be 10 (1+2+3+4)
            // pages allocated to 1 student
            // there is no other possible combination

            // hence low = max(array) and high = sum(array)

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
            /*
             * return low because take e.g. of N = 4 arr={12,34,67,90} m = 2
             * Here mid is 112 mid might not necessarily point to the answer
             * But when the condition breaks the high will be behind low and low will be our
             * answer or we can simply have a variable ans and check ans = Math.min(ans,mid)
             * in the if block where we are checking isValid
             */

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
