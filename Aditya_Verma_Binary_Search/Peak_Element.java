package Aditya_Verma_Binary_Search;

// https://www.youtube.com/watch?v=OINnBJTRrMU&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=17
public class Peak_Element {

    // to find a peak element if the element(curr mid) is present at 0th position we
    // just need
    // to check
    // element at 1st index (since element at -1 index will be out of bounds) and if
    // the peak element is present at n-1 index we need to check
    // n-2 element (since n index element will be out of bound)
    // else we need to compare mid - 1 and mid + 1 with mid

    // if(mid>0&&mid<n-1){

    // if (arr[mid] > arr[mid+1] && arr[mid] < arr[mid-1]) return mid;
    // We are selecting that part of the array where mid was greater
    // for e.g. curr arr is {1,2,3,5,10,20,30,40,8} mid is 10 and we checked mid + 1
    // is greater than 10
    // so we are going to move our curr low to mid + 1, so low will be 20 and high
    // will be 8
    // This works because since 20 is already greater than 10 there is a chance of
    // finding something lower than 20 on right and hence our condition of peak will
    // be satisfied
    // in case if we opt to move to left here 10 is already greater than 5 and hence
    // finding a peak on left side of 5 is unlikely and possibility is very less and
    // on right we will either find a peak or reach the end of array and if we dont
    // find peak then the last index will be our peak as per the definiton of peak
    // because from mid the array kept increasing and hence we did not find peak and
    // if we did not find peak it means the last element is greater than second last
    // element and hence the last element is our peak and if we found somthing that
    // is less than mid or any number after mid then that number is the peak
    // e.g. {1,2,3,5,10,20,30,8,}
    // after 20 we found 1 number that satisfies peak element criteria and hence it
    // is peak if 30 was not present then the last number would have been peak
    // else if(arr[mid-1] > arr[mid]) high = mid-1;
    // else low = mid + 1
    // }

    /*
     * else if(mid == 0){
     * (arr[0] > arr[1]) ? return 0 : 1;
     * }
     * 
     * else if(mid == n-1){
     * (arr[n-1] > arr[n-2]) return n-1 : n-2
     * }
     * 
     * 
     * 
     */

}
