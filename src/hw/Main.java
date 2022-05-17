package hw;

import java.util.List;

import static hw.ReversedLinkedList.*;

public class Main {
    public static void main(String[] args) {
        var head = createLinkedList(4, 3, 9, 1);
        printReversedRecursively(head);
        printReversedUsingStack(head);
        System.out.println();

        List<User> users = getUsers();

        RandomFieldComparator<User> randomFieldComparator = new RandomFieldComparator<>(User.class, true);
        System.out.println(randomFieldComparator);
        getUsers().stream()
                .sorted(randomFieldComparator)
                .forEach(System.out::println);
    }

    private static List<User> getUsers() {
        return List.of(
                new User(433L, "Pavlo", "Khshanovskyi", 27, true, "fbdk", null, null, 66, null),
                new User(45849L, "Petro", "Pols", 34, false, "33fvd", 41L, "ffmd", 0, 442L),
                new User(43L, "Inna", "Yers", 15, false, "gskdsjfh", null, "d", 100, null),
                new User(46905L, "Olya", "Alfk", 59, true, "dknfhsjkd", 4444L, null, 432, 1L),
                new User(1343L, "Rita", "Pkjg", 22, true, "fbdk", 1884L, "thjfdhjsk", 0, 5999L),
                new User(null, "Ihor", "Lika", 43, false, "fbdk", null, "adre", 1, null));
    }
}
