package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// TODO Backwash Problem is not solved
public class Percolation {
    // if the value in a gird is 1, meaning open
    // 0 means closed otherwise
    private int[][] grids;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufOrigin;
    private int N;
    private boolean percolates;
    private int openSites;

    public Percolation(int N) throws IllegalArgumentException {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be positive.");
        }
        grids = new int[N][N];
        // +2 for virtual top & virtual bottom
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufOrigin = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        percolates = false;
        openSites = 0;
    }

    public void open(int row, int col) {
        if (grids[row][col] == 0) {
            grids[row][col] = 1;
            openSites += 1;
        }
        /* check the orthogonally adjacent grids, if open, then connect them */
        // the grid around should be open, not on the edge & not connected with (row, col)
        // TODO - ArrayIndexOutOfBoundsEexcepetion occurs here, not solved
        int thisPoint = xyTo1D(row, col, N);
        if (col >= 1) {
            int leftPoint = xyTo1D(row, col - 1, N);
            if (isOpen(row, col - 1) && !ufOrigin.connected(thisPoint, leftPoint)) {
                uf.union(thisPoint, leftPoint);
                ufOrigin.union(thisPoint, leftPoint);
            }
        }
        if (col <= N - 2) {
            int rightPoint = xyTo1D(row, col + 1, N);
            if (isOpen(row, col + 1) && !ufOrigin.connected(thisPoint, rightPoint)) {
                uf.union(thisPoint, rightPoint);
                ufOrigin.union(thisPoint, rightPoint);
            }
        }
        if (row >= 1) {
            int upperPoint = xyTo1D(row - 1, col, N);
            if (isOpen(row - 1, col) && !ufOrigin.connected(thisPoint, upperPoint)) {
                uf.union(thisPoint, upperPoint);
                ufOrigin.union(thisPoint, upperPoint);
            }
        }
        if (row <= N - 2) {
            int underPoint = xyTo1D(row + 1, col, N);
            if (isOpen(row + 1, col) && !ufOrigin.connected(thisPoint, underPoint)) {
                uf.union(thisPoint, underPoint);
                ufOrigin.union(thisPoint, underPoint);
            }
        }
        // If you're opening a top-row site, then connect it with the virtual top
        if (row == 0) {
            uf.union(N * N, thisPoint);
            ufOrigin.union(N * N, thisPoint);
        }
        // Likewise, when opening a bottom-row site, connect it with the virtual bottom
        if (row == N - 1) {
            uf.union(N * N + 1, thisPoint);
        }
        percolates = uf.connected(N * N, N * N + 1);
    }

    public boolean isOpen(int row, int col) {
        return grids[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        // is connected with the virtual top?
        int thisPoint = xyTo1D(row, col, N);
        return ufOrigin.connected(thisPoint, N * N);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        // if the virtual top & virtual bottom is connected?
        return percolates;
    }

    /* HELPER METHOD */
    private static int xyTo1D(int row, int col, int N) {
        return row * N + col;
    }
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 1);
        p.open(1, 1);
        System.out.println(p.isFull(1, 1));
    }
}
