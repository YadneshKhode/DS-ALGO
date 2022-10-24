
// https://www.youtube.com/watch?v=VS0BcOiKaGI&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=20
public class Search_in_Row_wise_And_Column_wise_Sorted_Array {
    /*
     * start from upper right most corner if it is equal to key return
     * else check if the key is greater than curr element then go below
     * or else go left (if key is smaller than left go left)
     * 
     * Time complexity will be Number of Rows + Number of Columns
     * Because In worst case we are starting from top right and the element we need
     * to find is present in the bottom left and always the path taken to reach the
     * desired element from starting position(top-right) will be the shortest path
     * because we are not making unnecessary moves
     */
}
