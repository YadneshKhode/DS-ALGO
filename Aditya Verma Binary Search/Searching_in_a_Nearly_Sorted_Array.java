// https://www.youtube.com/watch?v=W3-KgsCVH1U&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=9

public class Searching_in_a_Nearly_Sorted_Array {

    // Nearly sorted array means the number that should be present on index let's
    // say 5 can be present on 4 or 5 or 6
    // check if mid - 1, mid and mid + 1 is equal to key if yes then return the
    // appropriate index
    // if not then check if key is less than or equal to mid - 2 (because we already
    // checked mid -1) if yes then high = mid - 2 else low = mid + 2
    // make sure if curr index is 0 then we are not doing mid - 1
    // we can tackle this by calculating
    // int midLeft(mid -1 variable) = (mid - 1 + n) % n
    // int midRight(mid + 1 variable) = (mid + 1) % n
}
