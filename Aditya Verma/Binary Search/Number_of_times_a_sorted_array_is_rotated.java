
//Aditya Verma
// https://www.youtube.com/watch?v=4WmTRFZilj8&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=7

//https://practice.geeksforgeeks.org/problems/rotation4723/1/
// Pepcoding
// https://www.youtube.com/watch?v=vF7gk4iaklA
// The minimum element will always lie on the part of the array where the array is unsorted
// because whenever we go up and then the fall comes it will always hit the bottom 
// case 1: we are starting from bottom and keep going up (complete sorted array)
// in this case the first element is the smallest
// case 2: starting high then we keep decrementing until we hit bottom and then increase again
// in this scenario also the min will lie in the unsorted region

// The other cases can be found in the video
// ********************************************************************************
// we just have to make sure while calulating low and high we need to ensure mid is included so we 
// actually get an unsorted array

// e.g. - {30,40,50,10,20} 

// we are comparing 50 and 20 and we know this region 50-20 is unsorted so we are going to
// increment low to mid + 1(wrong approach) now low becomes 10 and high becomes 20 BUT now we 
// dont have the unsorted portion and we would not know where to go so include 50 as well
// now when we calculate mid that is 10 we are sure to go left in the unsorted region (the example 
// could be better but don't focus on that...) and again when we go towards unsorted region make sure to include mid again

// ********************************************************************************

public class Number_of_times_a_sorted_array_is_rotated {
    int findKRotation(int arr[], int n) {

        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            // to handle if we are on 0th index and when we do -1 while finding out mid then
            // we should go to n-1 th element
            int prev = (mid - 1 + n) % n;
            // similarly if we are on n-1th element and we do +1 then we should go to 0th
            // element
            int next = (mid + 1) % n;
            // if the element is smaller than both its left and right side element then
            // this is the answer since it is the minimum of the whole array
            if (arr[mid] < arr[prev] && arr[mid] < arr[next]) {
                return mid;
            } else if (arr[mid] < arr[high])
                high = mid;
            else
                low = mid + 1;
        }

        return 0;
    }
}
