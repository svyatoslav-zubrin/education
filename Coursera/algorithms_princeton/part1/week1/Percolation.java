public class Percolation {
    
    private final boolean[][] grid;   // grid with sites either open or blocked
    
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
  
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }
    
    // Public methods
    
    public void open(int i, int j) {
        grid[i][j] = true;
    }
    
    public boolean isOpen(int i, int j) {
        return grid[i][j];
    }
    
    public boolean isFull(int i, int j) {
        return true;
    }
    
    public boolean percolates() {
        return true;
    }
    
    public static void main(String[] args) {
        
        Percolation percolationItem = new Percolation(10);
        // System.out.println(percolationItem.isOpen(3, 3));
        StdOut.println(percolationItem.isOpen(3, 3));
        
        percolationItem.open(3, 3);
        // System.out.println(percolationItem.isOpen(3, 3));
        StdOut.println(percolationItem.isOpen(3, 3));
    }
    
    // Private methods
    
}

