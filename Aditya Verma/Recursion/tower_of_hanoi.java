
class Hanoi {
    long count = 0;

    public long toh(int n, int from, int to, int aux) {
        // Your code here
        count++;

        if (n == 1) {
            System.out.println("move disk " + n + " from rod " + from + " to rod " + to);
            return count;
        }

        toh(n - 1, from, aux, to);

        System.out.println("move disk " + n + " from rod " + from + " to rod " + to);

        return toh(n - 1, aux, to, from);
    }
}
