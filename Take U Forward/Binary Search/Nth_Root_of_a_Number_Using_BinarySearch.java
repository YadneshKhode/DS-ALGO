package Take_U_Forward_Binary_Search;
// https://www.youtube.com/watch?v=WjpswYrS2nY&list=PLgUwDviBIf0qYbL4TBaEWgb-ljVdhkM7R&index=6

public class Nth_Root_of_a_Number_Using_BinarySearch {
    public static void main(String[] args) {
        double n = 2, m = 625, low = 1, high = m;
        double epsilon = 1e-6; // we are taking 1 / 10^6 because we need precision upto 5 decimal places,
        // If we needed precision upto 6 decimal places we would have to take 1 / 10^7

        // below while loop breaks when we have achieved precision upto 5 decimal places
        while (high - low > epsilon) {
            double mid = low + (high - low) / 2;
            // double multiplied = Math.pow(mid, n);
            if (multiplied(mid, n) > m) // For multiplied function use Binary Exponentiation
                // Not doing -1 Because we want answer in terms of decimal places, doing -1
                // will not help
                high = mid;
            else
                low = mid;
        }
        System.out.println(low);
        System.out.println(high);
        // below line calculates using pow function so we can compare our answer with it
        System.out.println(Math.pow(m, (double) (1.0 / (double) n)));
    }

    // https://www.youtube.com/watch?v=l0YC3876qxg
    public static double multiplied(double number, double n) {
        // calculate 2^5
        // since 5 is odd we store ans = 2; and n = n-1 -> n = 4
        // do ans = ans * number -> ans = 2 * 2 = 4 and n = n /2 -> 4/2 = 2
        // now again do same thing until n is 0 the final ans is 32
        double ans = 1.0;
        while (n != 0) {
            if ((n % 2) == 0)
                n = n / 2;
            else
                n = n - 1;
            ans = ans * number;
        }
        return ans;
    }
}
