/*
 * Rectangle class gives the functionality to create rectangles to be
 * printed to a matrix.
 * 
 * Vincent Atanasov, 2014
 */

public class Rectangle
{
    private int topLeftRow;
    private int topLeftCol;
    private int height;
    private int width;
    private char fillChar;
    
    /*
     * constructor
     * - calculates height and width from coordinates
     * and stores all necessary values 
     */
    public Rectangle(int topLeftRow, int topLeftCol, int bottomRightRow, int bottomRightCol, char fillChar) throws Exception
    {
        // calculate and  set height and width
        this.height = bottomRightRow - topLeftRow + 1;
        this.width  = bottomRightCol - topLeftCol + 1;
        
        // ensure we have a valid rectangle
        if (height <= 0 || width <= 0)
            throw new Exception("Invalid rectangle provided.");
        
        this.topLeftRow = topLeftRow;
        this.topLeftCol = topLeftCol;
        this.fillChar   = fillChar;
    }
    
    /*
     * draw rectangle to a given matrix
     * throws an index out of bounds exception if rectangle doesn't fit
     */
    public void drawTo(Matrix m) throws Exception
    {
        // loop over the drawing area, starting at top left of rectangle to bottom
        // right (just adding height to row, and width to column) filling each
        // space with the specified character
        for (int i = topLeftRow; i < topLeftRow+height; i++)
            for (int j = topLeftCol; j < topLeftCol+width; j++)
                m.matrix[i][j] = fillChar;
    }
} // end Rectangle