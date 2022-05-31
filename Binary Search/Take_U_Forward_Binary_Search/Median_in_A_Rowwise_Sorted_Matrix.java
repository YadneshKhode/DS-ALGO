// https://practice.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1#

// IMP->  https://www.youtube.com/watch?v=tFdBRcHLSGQ

// https://www.youtube.com/watch?v=63fPPOdIr2c&list=PLgUwDviBIf0qYbL4TBaEWgb-ljVdhkM7R&index=3

/*
1) We have to find out the median in a row wise sorted 2D array it means only the particular row is sorted and it has no relation with the other rows whatsoever.

2) We can put the whole 2D array in a 1D array sort it and return whatever is in the middle.

3) Time complexity for the above naive approach is O(n log n) that is the time required to sort the array, and space complexity is O(rows x column).

4) To solve in optimised way here notice that it is mentioned in question that the rows and columns of 2D Matrix will be always odd and hence whenever we have to find median, no of numbers on both side of the median will be same and half of length of array

5) E.g. arr = [1,2,3,3,5,6,7,8,9] -> here 5 is median since it lies in middle of the array and it has exactly 4 elements on left and 4 on right (9 / 2 = 4)

6) Now we have to use binary search in range 0 to 2000 because the elements in the matrix can be between 0 and 2000 but if no range is given use 0 to Integer.MAX_VALUE 

7) check the total number of elements present in matrix that are less than or EQUAL to the mid by applying binary search on each row separately

8) If total number of elements are more than 4 (since 9/2 = 4, possible values could be 5,6,7,8,9) then it means our median on left side of mid (for e.g. mid is currently 12 and all elements are less than 12 hence 12 cannot be median and median must lie on left side of 12) in such case we need to set high = mid - 1
else low = mid + 1

9) Keep doing this until search space is exhausted in end high will be at index 3 and low will be at index 4 (in terms of 1D array (imagine we converted 2D array to 1D array representation)) and hence we need to return low
*/

class Median_in_A_Rowwise_Sorted_Matrix {
    int median(int matrix[][], int r, int c) {
        // code here
        int low = 0, high = 2000, countLessThanEqualToMedian = (r * c) >>> 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (findNumberOfElementsLessThanEqualToKey(matrix, r, c, mid) > countLessThanEqualToMedian)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    int findNumberOfElementsLessThanEqualToKey(int matrix[][], int r, int c, int key) {

        int count = 0, row = 0;
        while (row < r) {
            int low = 0, high = c - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (matrix[row][mid] <= key)
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            row++;
            count += low;
        }
        return count;
    }
}