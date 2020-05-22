package hw2;

public class PercolationFactory {

    public PercolationFactory() {

    }

    public Percolation make(int N) {
        return new Percolation(N);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        Percolation p = pf.make(3);
    }
}
