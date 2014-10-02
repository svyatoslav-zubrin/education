public class PercolationStats {

    private double[] thresholds;
    private int iterationsNumber;

    // Public
    public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
    {
        if (N <= 0) throw new IllegalArgumentException();
        if (T <= 0) throw new IllegalArgumentException();

        iterationsNumber = T;
        thresholds = new double[iterationsNumber];

        for (int i = 0; i < iterationsNumber; i++) {
            double threshold = experiment(N);
            thresholds[i] = threshold;
        }
    }

    public double mean()                     // sample mean of percolation threshold
    {
        return StdStats.mean(thresholds);
    }
    public double stddev()                   // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(thresholds);
    }
    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    {
        return mean() - 1.96 * stddev() / Math.sqrt(iterationsNumber);
    }
    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        return mean() + 1.96 * stddev() / Math.sqrt(iterationsNumber);
    }

    // Private
    private double experiment(int N)
    {
        int counter = 0;
        Percolation p = new Percolation(N);
        boolean isPercolated = p.percolates();

        while (!isPercolated)
        {
            int x = StdRandom.uniform(N) + 1;
            int y = StdRandom.uniform(N) + 1;

            if (!p.isOpen(x, y))
            {
                p.open(x, y);
                counter += 1;
            }

            isPercolated = p.percolates();
        }

        return (double) counter / (N * N);
    }

    public static void main(String[] args)   // test client
    {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(N, T);

        StdOut.printf("mean                    = %.16f\n", ps.mean());
        StdOut.printf("stddev                  = %.16f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = %.16f, %.16f", ps.confidenceLo(), ps.confidenceHi());
    }
}
