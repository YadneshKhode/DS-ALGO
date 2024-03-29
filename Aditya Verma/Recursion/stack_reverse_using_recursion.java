
import java.util.*;

public class stack_reverse_using_recursion {
    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(6);
        stack.push(3);
        stack.push(7);
        stack.push(1);

        System.out.println("Before Reverse");

        stack.forEach(System.out::println);

        stack = reverse2(stack);

        System.out.println("After Reverse");

        stack.forEach(System.out::println);
    }

    public static Stack<Integer> reverse2(Stack<Integer> stack) {
        if (stack.size() == 1)
            return stack;
        int temp = stack.pop();
        reverse2(stack);
        insert2(stack, temp);
        return stack;
    }

    public static void insert2(Stack<Integer> stack, int temp) {
        if (stack.size() == 0) {
            stack.push(temp);
            return;
        }
        int temp2 = stack.pop();
        insert2(stack, temp);
        stack.push(temp2);
    }

    public static Stack<Integer> reverse(Stack<Integer> stack) {
        if (stack.size() == 1)
            return stack;
        int temp = stack.pop();

        stack = reverse(stack);

        stack = insert(stack, temp);

        return stack;
    }

    public static Stack<Integer> insert(Stack<Integer> stack, int temp) {
        if (stack.size() == 0) {
            stack.push(temp);
            return stack;
        }
        int temp1 = stack.pop();
        stack = insert(stack, temp);
        stack.push(temp1);

        return stack;
    }
}
