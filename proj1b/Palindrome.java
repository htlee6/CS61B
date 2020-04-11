public class Palindrome {
    /** Convert a word to Deque<Character> type. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dCharacter = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            dCharacter.addLast(word.charAt(i));
        }
        return dCharacter;
    }

    /** Tell if the word is a palindrome. A palindrome is defined as a word that is
     * the same whether it is read forwards or backwards.
     * i.e. racecar, noon. Any word of length 1/0 is palindrome. */
    // TODO According to Josh, there is a really beautiful way of recursion, waiting to be found out
    public boolean isPalindrome(String word) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        if (word.equals(reverse(word))) {
            return true;
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i += 1) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    /* Helper Method */
    /** Reverse the word. */
    private String reverse(String word) {
        String reversedWord = "";
        for (int i = word.length() - 1; i >= 0; i -= 1) {
            reversedWord = reversedWord + word.charAt(i);
        }
        return reversedWord;
    }

    /** Check each character. */
    /*
    private boolean isOffBy(String word, OffByOne obo) {
        System.out.println("Using isOffByOne(). ");
        for (int i = 0; i < word.length()/2; i += 1) {
            if (!obo.equalChars(word.charAt(i), word.charAt(word.length()+1-i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isOffBy(String word, OffByN obN) {
        System.out.println("Using isOffByN(). ");
        for (int i = 0; i < word.length()/2; i += 1) {
            if (!obN.equalChars(word.charAt(i), word.charAt(word.length()+1-i))) {
                return false;
            }
        }
        return true;
    }
     */
}
