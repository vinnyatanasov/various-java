/*
 * Matrix class gives functionality to simply create a matrix.
 * 
 * Vincent Atanasov, 2014
 */

public class Matrix
{
    private int height;
    private int width;
    public char[][] matrix;
    
    /*
     * constructor
     * - stores local values, and fills matrix
     */
    public Matrix(int height, int width) throws Exception
    {
        // throw exception if height or width are not positive ints
        if (height <= 0 || width <= 0)
            throw new Exception("Invalid drawing area dimensions provided.");
        else if (height > 1024 || width > 1024)
            throw new Exception("Drawing area dimensions are too large. Please don't exceed 1024.");
        
        // set up height, width and matrix
        this.height = height;
        this.width  = width;
        matrix = new char[this.height][this.width];
        
		// fill with visible character to see grid better
        fill('-');
    }
    
    /*
     * fills the matrix with specified character
     */
    private void fill(char c)
    {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                matrix[i][j] = c;
    }
    
    /*
     * converts matrix to string for printing
     */
    public String toString()
    {
        StringBuffer string = new StringBuffer();
        
        // loop through matrix, appending each element to the string
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
                string.append(matrix[i][j]);
            
            // add new line for all but last line
            if (i != height-1)
                string.append("\r\n");
        }
        
        return string.toString();
    }
} // end Matrix
