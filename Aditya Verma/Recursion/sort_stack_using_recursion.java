
import java.util.*;

public class sort_stack_using_recursion {
    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(6);
        stack.push(3);
        stack.push(7);
        stack.push(1);

        stack = sort2(stack);
        stack.forEach(System.out::println);
    }

    public static Stack<Integer> sort2(Stack<Integer> stack) {
        if (stack.size() == 1)
            return stack;
        int temp = stack.pop();
        sort2(stack);
        insert2(stack, temp);
        return stack;
    }

    public static void insert2(Stack<Integer> stack, int temp) {
        if (stack.size() == 0 || stack.peek() >= temp) {
            stack.push(temp);
        } else {
            int temp2 = stack.pop();
            insert2(stack, temp);
            stack.push(temp2);
        }
    }

    public static Stack<Integer> sort(Stack<Integer> stack) {
        if (stack.size() == 1) {
            return stack;
        }
        int temp = stack.pop();
        stack = sort(stack);
        insert(stack, temp);
        return stack;
    }

    public static Stack<Integer> insert(Stack<Integer> stack, int temp) {
        if ((stack.size() == 0) || (stack.peek() <= temp))
            stack.push(temp);
        else {
            int temp1 = stack.pop();
            insert(stack, temp);
            stack.push(temp1);
        }
        return stack;
    }
}
