import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String input1 = "racecar";
        boolean actual1 = palindrome.isPalindrome(input1);
        assertTrue(actual1);

        String input2 = "a";
        boolean actual2 = palindrome.isPalindrome(input2);
        assertTrue(actual2);

        String input3 = "car";
        boolean actual3 = palindrome.isPalindrome(input3);
        assertFalse(actual3);
    }

    @Test
    public void testIsPalindromeOffByOne() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertFalse(palindrome.isPalindrome("Flake", offByOne));
    }

    @Test
    public void testIsPalindromeOffByN() {
        OffByN offByFive = new OffByN(5);
        assertTrue(palindrome.isPalindrome("anyway", offByFive));
    }
}
