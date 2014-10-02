public class Percolation {
    
    private final int topIndex;
    private final int bottomIndex;
    
    private final int problemSize;
    private final CoordinatesConverter coordConverter;
    private final WeightedQuickUnionUF unionUF;
    private final boolean[] grid1D;   // grid with sites either open or blocked
    
    private enum CellPosition {
        LeftSideCell, 
        RightSideCell, 
        TopSideCell, 
        BottomSideCell,
        LeftTopCornerCell,
        LeftBottomCornerCell,
        RightTopCornerCell,
        RightBottomCorner,
        CommonCell
    }

    // Constructors
    
    public Percolation(int N) throws IllegalArgumentException {
  
        if (N <= 0) throw new IllegalArgumentException();
  
        problemSize = N;
        coordConverter = CoordinatesConverter(problemSize);

        topIndex    = problemSize * problemSize + 1;
        bottomIndex = problemSize * problemSize;
        
        int size1D = problemSize * problemSize + 2;
        
        grid1D = new boolean[size1D];
        for (int i = 0; i < size1D; i++) {
            grid1D[i] = false;
        }
        grid1D[bottomIndex] = true;
        grid1D[topIndex]    = true;
        
        // prepare union
        unionUF = new WeightedQuickUnionUF(size1D);
        // connect top and bottom items to 
        for (int i = 1; i <= problemSize; i++) {
            unionUF.union(bottomIndex, ZeroBased1DIndexFromOneBased2DIndexes(1, i)); 
            unionUF.union(topIndex,    ZeroBased1DIndexFromOneBased2DIndexes(problemSize, i));
        }       
    }
    
    // Public methods
    
    public void open(int i, int j) {
        int current1DIndex = ZeroBased1DIndexFromOneBased2DIndexes(i, j);

        // open the site
        grid1D[current1DIndex] = true;

        // connect it to all nearby opened sites
        CellPosition cellPosition = this.getCellPosition(i, j);
        openCell(cellPosition, current1DIndex);
    }
    
    public boolean isOpen(int i, int j) {
        return false; //grid2D[i][j];
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
    
    private int ZeroBased1DIndexFromOneBased2DIndexes(int i, int j) {
        /*
         *
         * Suppose i and j lies within [1...N]
         * Result index lies within [0...N)
         *
         */
        if (i < 1 || i > problemSize) throw new IndexOutOfBoundsException();
        if (j < 1 || j > problemSize) throw new IndexOutOfBoundsException();
       
        return (i - 1) * problemSize + (j - 1);
    }
    
    private CellPosition getCellPosition(int i, int j) {
        return CommonCell;
    }

    private void openCell(int cellIndex1D, CellPosition cellPosition) {
        /*
         *
         * Connect cell with all opened neighbour cells
         * 
         */
			
	        // , 
	        // RightSideCell, 
	        // TopSideCell, 
	        // BottomSideCell,
	        // LeftTopCornerCell,
	        // LeftBottomCornerCell,
	        // RightTopCornerCell,
	        // RightBottomCorner,
	        // CommonCell

            int topIndex1D = cellIndex1D + problemSize;
            int botIndex1D = cellIndex1D - problemSize;
            int lftIndex1D = cellIndex1D - 1;
            int rhtIndex1D = cellIndex1D + 1;
			
			switch (cellPosition) {
				case LeftSideCell:
				{
                    if (grid1D[topIndex]) {

                    }
				}
			}

        // // left
        // if (cellPosition == LeftSideCell
        //     || cellPosition == LeftTopCornerCell
        //     || cellPosition == LeftBottomCornerCell) 
        // {

        // }

        // // top

        // // right

        // // bottom

    }

    // main
    
    public static void main(String[] args) {
        
        Percolation percolationItem = new Percolation(3);
        StdOut.println(percolationItem.percolates());
    }
}

class CoordinatesConverter {
    
    public int x, y;

    private int dimension;

    // Constructors

    public CoordinatesConverter(int N) {

        if (N <= 0) throw new IllegalArgumentException();

        dimension = N;
    }

    // public methods

    public int D1FromD2(int x, int y) {
        return 0;
    }

    public Coord2D D2FromD1(int x) {
        return Coord2D(1,1);
    }
}

class Coord2D {
    public int x, y;
    public Coord2D (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
