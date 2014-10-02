public class Percolation {
    
    // Class that represents 2D coordinate
    private class Coord2D {
        public int x, y;
        public Coord2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Class that may be used to convert between 1D and 2D coordinates
    private class CoordinatesConverter {
    
        public int x, y;
        private int dimension;

        // Constructors
        public CoordinatesConverter(int N) {
            if (N <= 0) throw new IllegalArgumentException();
            dimension = N;
        }

        // public methods
        public int D1FromD2(int x, int y) {
            
            if (x < 1 || x > dimension) throw new IndexOutOfBoundsException();
            if (y < 1 || y > dimension) throw new IndexOutOfBoundsException();

            return (x - 1) * dimension + (y - 1);
        }

        public Coord2D D2FromD1(int z) {
            
            if (z < 0 || z >= dimension * dimension) throw new IndexOutOfBoundsException();

            int x = Math.abs(z / dimension) + 1;
            int y = z % dimension + 1;
            return new Coord2D(x, y);
        }
    }

    private final int topMostFakeIndex;
    private final int bottomMostFakeIndex;
    
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
        RightBottomCornerCell,
        CommonCell
    }

    // Constructors
    
    public Percolation(int N) throws IllegalArgumentException {
  
        if (N <= 0) throw new IllegalArgumentException();
  
        problemSize = N;
        coordConverter = new CoordinatesConverter(problemSize);

        topMostFakeIndex    = problemSize * problemSize + 1;
        bottomMostFakeIndex = problemSize * problemSize;
        
        int size1D = problemSize * problemSize + 2;
        
        grid1D = new boolean[size1D];
        for (int i = 0; i < size1D; i++) {
            grid1D[i] = false;
        }
        grid1D[bottomMostFakeIndex] = true;
        grid1D[topMostFakeIndex]    = true;
        
        // prepare union
        unionUF = new WeightedQuickUnionUF(size1D);
        // connect top and bottom items to 
        for (int i = 1; i <= problemSize; i++) {
            unionUF.union(bottomMostFakeIndex, coordConverter.D1FromD2(1, i)); 
            unionUF.union(topMostFakeIndex,    coordConverter.D1FromD2(problemSize, i));
        } 
    }
    
    // Public methods
    
    public void open(int i, int j) {
        int current1DIndex = coordConverter.D1FromD2(i, j);

        // open the site
        grid1D[current1DIndex] = true;

        // connect it to all nearby opened sites
        CellPosition cellPosition = this.getCellPosition(i, j);
        openCell(cellPosition, current1DIndex);

        if (cellPosition == CellPosition.TopSideCell
            || cellPosition == CellPosition.RightTopCornerCell
            || cellPosition == CellPosition.LeftTopCornerCell) 
        {
            // connect to the top most fake site
            unionUF.union(current1DIndex, topMostFakeIndex);
        }
        else if (cellPosition == CellPosition.BottomSideCell
                 || cellPosition == CellPosition.RightBottomCornerCell
                 || cellPosition == CellPosition.LeftBottomCornerCell) 
        {
           // connect to the bottom most fake site 
            unionUF.union(current1DIndex, bottomMostFakeIndex);
        }
    }
    
    public boolean isOpen(int i, int j) {
        return grid1D[coordConverter.D1FromD2(i,j)];
    }
    
    public boolean isFull(int i, int j) {
        int current1DCoordinate = coordConverter.D1FromD2(i,j);
        return unionUF.connected(current1DCoordinate, topMostFakeIndex);
    }
    
    public boolean percolates() {
        return unionUF.connected(bottomMostFakeIndex, topMostFakeIndex);
    }
    
    // Private methods
    private CellPosition getCellPosition(int i, int j) {
        if (j == 1) {
            if (i == 1) {
                return CellPosition.LeftBottomCornerCell;
            } else if (i == problemSize) {
                return CellPosition.LeftTopCornerCell;
            } else {
                return CellPosition.LeftSideCell;
            }
        } else if (j == problemSize) {
            if (i == 1) {
                return CellPosition.RightBottomCornerCell;
            } else if (i == problemSize) {
                return CellPosition.RightTopCornerCell;
            } else {
                return CellPosition.RightSideCell;
            }
        } else {
            if (i == 1) {
                return CellPosition.BottomSideCell;
            } else if (i == problemSize) {
                return CellPosition.TopSideCell;
            } else {
                return CellPosition.CommonCell;
            }
        }
    }

    private void openCell(CellPosition cellPosition, int cellIndex1D) {
        /*
         *
         * Connect cell with all opened neighbour cells
         * 
         */
        int topIndex1D      = cellIndex1D + problemSize;
        int bottomIndex1D   = cellIndex1D - problemSize;
        int leftIndex1D     = cellIndex1D - 1;
        int rightIndex1D    = cellIndex1D + 1;
   
        switch (cellPosition) {
            case LeftSideCell:
                {
                    connectCites(cellIndex1D, topIndex1D);
                    connectCites(cellIndex1D, rightIndex1D);
                    connectCites(cellIndex1D, bottomIndex1D);
                }
                break;
            case RightSideCell:
                {
                    connectCites(cellIndex1D, topIndex1D);
                    connectCites(cellIndex1D, leftIndex1D);
                    connectCites(cellIndex1D, bottomIndex1D);
                }
                break;
            case TopSideCell:
                {
                    connectCites(cellIndex1D, leftIndex1D);
                    connectCites(cellIndex1D, bottomIndex1D);
                    connectCites(cellIndex1D, rightIndex1D);
                }
                break;
            case BottomSideCell:
                {
                    connectCites(cellIndex1D, leftIndex1D);
                    connectCites(cellIndex1D, topIndex1D);
                    connectCites(cellIndex1D, rightIndex1D);
                }
                break;
            case LeftTopCornerCell:
                {
                    connectCites(cellIndex1D, rightIndex1D);
                    connectCites(cellIndex1D, bottomIndex1D);
                }
                break;
            case LeftBottomCornerCell:
                {
                    connectCites(cellIndex1D, rightIndex1D);
                    connectCites(cellIndex1D, topIndex1D);
                }
                break;
            case RightTopCornerCell:
                {
                    connectCites(cellIndex1D, leftIndex1D);
                    connectCites(cellIndex1D, bottomIndex1D);
                }
                break;
            case RightBottomCornerCell:
                {
                    connectCites(cellIndex1D, leftIndex1D);
                    connectCites(cellIndex1D, topIndex1D);
                }
                break;
            case CommonCell:
            default:
                {
                    connectCites(cellIndex1D, leftIndex1D);
                    connectCites(cellIndex1D, topIndex1D);
                    connectCites(cellIndex1D, rightIndex1D);
                    connectCites(cellIndex1D, bottomIndex1D);
                }
                break;
        }
    }

    private void connectCites(int first1DCoordinate, int second1DCoordinate) {
        Coord2D first2DCoordinate  = coordConverter.D2FromD1(first1DCoordinate);
        Coord2D second2DCoordinate = coordConverter.D2FromD1(second1DCoordinate);

        if (isOpen(first2DCoordinate.x, first2DCoordinate.y) 
            && isOpen(second2DCoordinate.x, second2DCoordinate.y))
        {
            unionUF.union(first1DCoordinate, second1DCoordinate);
        }
    }
}