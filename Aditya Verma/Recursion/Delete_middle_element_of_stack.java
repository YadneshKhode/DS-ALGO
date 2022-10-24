
import java.util.*;

public class Delete_middle_element_of_stack {
    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(6);
        stack.push(3);
        stack.push(7);
        stack.push(1);

        int n = (stack.size() >> 1) + 1;

        stack = delMidStac(stack, n);

        stack.forEach(System.out::println);
    }

    public static Stack<Integer> delMidStac(Stack<Integer> stack, int n) {
        if (n == 1) {
            stack.pop();
            return stack;
        }
        int temp = stack.pop();
        stack = delMidStac(stack, --n);
        stack.push(temp);
        return stack;
    }
}
