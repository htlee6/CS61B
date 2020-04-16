public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }


    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }


    /** Test the ArrayDeque constructor. */
    public static void addisEmptySizeTest() {
        System.out.println("Running add/isEmpty/size test.");
        ArrayDeque<String> deque = new ArrayDeque<>("I");
        boolean passed = checkEmpty(false, deque.isEmpty());
        deque.addFirst("and");
        deque.addLast("go");
        deque.addLast("hiking");
        deque.addFirst("You");
        passed = checkSize(5, deque.size()) && passed;
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        passed = checkEmpty(true, deque2.isEmpty()) && passed;
        printTestStatus(passed);
    }

    public static void addRemoveTest() {
        System.out.println("Running add/remove test.");
        ArrayDeque<Integer> deque1 = new ArrayDeque<>(5);
        deque1.addLast(10);
        deque1.addLast(20);
        deque1.addLast(40);
        deque1.addFirst(1);
        int last1 = deque1.removeLast();
        int last2 = deque1.removeLast();
        int first1 = deque1.removeFirst();
        int first2 = deque1.removeFirst();
        if (last1 == 40 && last2 == 20 && first1 == 1 && first2 == 5) {
            printTestStatus(true);
        }
    }

    public static void getTest() {
        System.out.println("Running get test.");
        ArrayDeque<Integer> deque1 = new ArrayDeque<>(5);
        deque1.addLast(10);
        deque1.addLast(20);
        deque1.addLast(40);
        deque1.addFirst(1);
        int get1 = deque1.get(0);
        int get2 = deque1.get(1);
        int get3 = deque1.get(7);
        if(get1 == 5 && get2 == 10 && get3 == 1 && deque1.get(5) == null) {
            printTestStatus(true);
        }
    }

    public static void resizeTest() {
        System.out.println("Running get test.");
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        int i = 0;
        while (i < 100) {
            deque1.addLast(i);
            i++;
        }
        System.out.println(deque1.size());
        deque1.printDeque();
        System.out.println();
        while (i > 10) {
            deque1.removeLast();
            i--;
        }
        System.out.println(deque1.size());
        deque1.printDeque();
    }

    public static void main(String[] args) {
        addisEmptySizeTest();
        addRemoveTest();
        getTest();
        resizeTest();

    }
}
