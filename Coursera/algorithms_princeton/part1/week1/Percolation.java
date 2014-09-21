public class Percolation {
    
    private final int topIndex;
    private final int bottomIndex;
    
    private final int problemSize;
    private final WeightedQuickUnionUF unionUF;
    private final boolean[][] grid2D;   // grid with sites either open or blocked
    private final boolean[] grid1D;   // grid with sites either open or blocked
    
    /*
     public Percolation(int N);                // create N-by-N grid, with all sites blocked
     public void open(int i, int j);           // open site (row i, column j) if it is not already
     public boolean isOpen(int i, int j);      // is site (row i, column j) open?
     public boolean isFull(int i, int j);      // is site (row i, column j) full?
     public boolean percolates();              // does the system percolate?
     public static void main(String[] args);   // test client, optional
     */
    // Constructors
    
    public Percolation(int N) throws IllegalArgumentException {
  
        if (N <= 0) throw new IllegalArgumentException();
  
        problemSize = N;
        topIndex    = problemSize * problemSize + 1;
        bottomIndex = problemSize * problemSize;
        
        /*
        grid2D = new boolean[N][N];
        for (int i = 0; i < problemSize; i++) {
            for (int j = 0; j < problemSize; j++) {
                grid2D[i][j] = false;
            }
        }
        */
        
        int 1DSize = problemSize * problemSize + 2;
        
        grid1D = new boolean[1DSize];
        for (int i = 0; i < 1DSize; i++) {
            grid1D[i] = false;
        }
        grid1D[bottomIndex] = true;
        grid1D[topIndex]    = true;
        
        // prepare union
        unionUF = new WeightedQuickUnionUF(1DSize);
        // connect top and bottom items to 
        for int (i = 1; i <= problemSize; i++) {
            unionUF.union(bottomIndex, 1DZeroBasedIndexFrom2DOneBasedIndexes(1, i)); 
            unionUF.union(topIndex, 1DZeroBasedIndexFrom2DOneBasedIndexes(problemSize, i));
        }       
    }
    
    // Public methods
    
    public void open(int i, int j) {
        grid2D[i][j] = true;
    }
    
    public boolean isOpen(int i, int j) {
        return grid2D[i][j];
    }
    
    public boolean isFull(int i, int j) {
        boolean result = false;
        
//        for (int k = 0; k < N; k++) {
//            if 
//        }
        
        return result;
    }
    
    public boolean percolates() {
        return unionUF.connected(bottomIndex, topIndex);
    }
    
    // Private methods
    
    private int 1DZeroBasedIndexFrom2DOneBasedIndexes(int i, int j) {
        /*
         * Suppose i and j lies within [1...N]
         * Result index lies within [0...N)
         */
        if (i < 1 || i > problemSize) throw new /*java.lang.*/IndexOutOfBoundsException();
        if (j < 1 || j > problemSize) throw new /*java.lang.*/IndexOutOfBoundsException();
       
        return (i - 1) * N + (j - 1);
    }
    
    // main
    
    public static void main(String[] args) {
        
        Percolation percolationItem = new Percolation(3);
        StdOut.println(percolationItem.percolates());
    }
}

