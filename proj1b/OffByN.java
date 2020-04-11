public class OffByN implements CharacterComparator {

    private int off;

    public OffByN(int n) {
        off = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == off || y - x == off) {
            return true;
        }
        return false;
    }
}
