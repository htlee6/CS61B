package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private Percolation p;
    private double[] openSitesFrac;
    private double mean;
    private double stddev;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Invalid N or T value. ");
        }
        this.N = N;
        this.T = T;
        this.openSitesFrac = new double[T];

        startSimulation(pf);
    }

    public double mean() {
        mean = StdStats.mean(openSitesFrac);
        return mean;
    }
    public double stddev() {
        stddev = StdStats.stddev(openSitesFrac);
        return stddev;
    }
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    private void startSimulation(PercolationFactory pf) {
        for (int i = 0; i < T; i += 1) {
            p = pf.make(N);
            int openSites = 0;
            while (!p.percolates()) {
                int randRow = StdRandom.uniform(N),
                        randCol = StdRandom.uniform(N);
                if (!p.isOpen(randRow, randCol)) {
                    p.open(randRow, randCol);
                    openSites += 1;
                }
            }
            openSitesFrac[i] = ((double) openSites) / (double) (N * N);
        }
    }

}
