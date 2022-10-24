package yetToBePlaced;

class GetMedian {
    public static void main(String[] args) {
        // int n = 4;
        // int[] arr1 = { 1, 3, 5, 7 };
        // int[] arr2 = { 2, 4, 6, 8 };

        // System.out.println(getMedian(arr1, arr2, n));
        System.out.println("hi");
        System.out.println(pow(9, 3));

    }

    public static double getMedian(int[] arr1, int[] arr2, int n) {
        int p1 = 0, p2 = 0;
        for (int count = 0; count < n - 1; count++) {
            if (arr1[p1] <= arr2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        return (arr1[p1] + arr2[p2]) / 2.0;
    }

    public static int pow(int base, int power) {
        int ans = 1;
        while (power > 0) {
            if ((power & 1) == 0) {
                power >>= 1;
                base *= base;
            } else {
                power -= 1;
                ans *= base;
            }
        }
        return ans;
    }
}

/*
 * 3^5
 * answer = 3;
 * while(power > 0){
 * if(power = odd){
 * ans *= 3;
 * power -= 1;
 * }
 * base *= base;
 * power >>> 1
 * }
 *
 * 
 */