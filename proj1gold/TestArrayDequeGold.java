import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class TestArrayDequeGold {
    /*
    @Test
    public void testGet() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        sad.addLast(1);
        sad.addLast(2);
    }

     */

    @Test
    public void testAddLastAndLast() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        String studentArrayDequeOperations = "";

        for (int i = 0; i < 5; i += 1) {
            double randomNumber = StdRandom.uniform();
            if (randomNumber <= 0.5) {
                sad1.addFirst(i);
                ads.addFirst(i);
                studentArrayDequeOperations += "addFirst(" + String.valueOf(i) + ")\n";
            } else {
                sad1.addLast(i);
                ads.addLast(i);
                studentArrayDequeOperations += "addLast(" + String.valueOf(i) + ")\n";
            }
            int randomIndex = StdRandom.uniform(sad1.size());
            int expected =  ads.getRecursive(randomIndex);
            int actual = sad1.get(randomIndex);
            assertEquals(studentArrayDequeOperations, expected, actual);
        }
    }

    @Test
    public void testRemoveFirstAndLast() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        String studentArrayDequeOperations = "";

        for (int i = 0; i < 10; i += 1) {
            double randomNumber = StdRandom.uniform();
            if (randomNumber <= 0.5) {
                sad1.addFirst(i);
                ads.addFirst(i);
                studentArrayDequeOperations += "addFirst(" + String.valueOf(i) + ")\n";
            } else {
                sad1.addLast(i);
                ads.addLast(i);
                studentArrayDequeOperations += "addLast(" + String.valueOf(i) + ")\n";
            }
        }

        if (ads.size() == sad1.size()) {
            for (int i = 0; i < ads.size(); i += 1) {
                double randomNumber = StdRandom.uniform();
                if (randomNumber < 0.5) {
                    Integer expected = ads.removeFirst();
                    Integer actual = sad1.removeFirst();
                    studentArrayDequeOperations += "removeFirst(" + String.valueOf(i) + ")\n";
                    assertEquals(expected, actual);
                } else {
                    Integer expected1 = ads.removeLast();
                    Integer actual1 = sad1.removeLast();
                    studentArrayDequeOperations += "removeLast(" + String.valueOf(i) + ")\n";
                    assertEquals(expected1, actual1);
                }
            }
        }
    }
}
