package hw;

import java.util.Objects;
import java.util.Stack;

public class ReversedLinkedList {

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * Creates a list of linked {@link Node} objects based on the given array of elements and returns a head of the list.
     *
     * @param elements an array of elements that should be added to the list
     * @param <T>      elements type
     * @return head of the list
     */
    public static <T> Node<T> createLinkedList(T... elements) {
        Objects.requireNonNull(elements, "The [elements] should be not null");
        Node<T> head = null;
        Node<T> current = null;
        for (T element : elements) {
            if (head == null) {
                head = current = new Node<>(element);
            } else {
                current.next = new Node<>(element);
                current = current.next;
            }
        }
        return head;
    }

    /**
     * Prints a list in a reserved order using a recursion technique. Please note that it should not change the list,
     * just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedRecursively(Node<T> head) {
        Objects.requireNonNull(head, "The [head] should be not null");
        recursivePrint(head.next);
        System.out.print(head.element + "\n");
    }

    private static void recursivePrint(Node<?> head) {
        if (head.next == null) {
            System.out.print(head.element + " -> ");
            return;
        } else {
            recursivePrint(head.next);
            System.out.print(head.element + " -> ");
        }
    }

    /**
     * Prints a list in a reserved order using a {@link java.util.Stack} instance. Please note that it should not change
     * the list, just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedUsingStack(Node<T> head) {
        Objects.requireNonNull(head, "The [head] should be not null");
        Stack<T> stack = new Stack<>();
        Node<T> current = head;
        while (current.next != null) {
            stack.push(current.element);
            current = current.next;
        }
        stack.push(current.element);

        while (!stack.isEmpty()) {
            if (stack.size() > 1) {
                System.out.print(stack.pop() + " -> ");
            } else {
                System.out.print(stack.pop() + "\n");
            }
        }
    }
}
