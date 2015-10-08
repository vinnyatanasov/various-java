/*
 * Matrix class handles most of the work here.
 * It provides the functionality to create a matrix and
 * populate it, locate letters and print it to the screen.
 *
 * The matrix can be used as part of an encrypt object,
 * but the matrix and the attributes will always be private
 * to the matrix.
 * 
 * Vincent Atanasov, 2014
 */

public class Matrix
{
    private int size = 5;
    private String keyword;
    private char keyletter;
    private char[][] matrix;
    
    /*
     * constructor
     * - sets up some variables, creates and fills matrix
     * - throws exception if keyword/keyletter aren't valid
     */
    public Matrix(String keyword, char letter) throws Exception
    {
        // convert keyword and keyletter to uppercase
        keyword = keyword.toUpperCase();
        letter = Character.toUpperCase(letter);
        
        // check keyword is valid, if not throw exception
        if (!isValidKeyword(keyword))
            throw new Exception("The keyword must contain five distinct letters.");
        
        // store keyword if it's valid
        this.keyword = keyword;
        
        // check keyletter is not in keyword
        if (!isValidKeyletter(letter))
            throw new Exception("The keyletter must be a letter, that isn't in the keyword.");
        
        // store keyletter if it's valid
        keyletter = letter;
        
        // create the matrix and fill it
        matrix = new char[size][size];
        
        fill();
    }
    
    /*
     * fills the array up with the right letters based on the keyword and keyletter
     */
    private void fill()
    {
        // fill first line with keyword
        for (int i = 0; i < size; i++)
            matrix[0][i] = keyword.charAt(i);
        
        // fill rest of the letters, from row 1 using isAllowed to
        // only put the correct letter in
        int row = 1;
        int col = 0;
        for (char i = 'A'; i <= 'Z'; i++)
        {
            if (isAllowed(i))
            {
                // put the letter in the matrix
                // and move to the next position
                matrix[row][col] = i;
                
                col++;
                if (col == size)
                {
                    col = 0;
                    row++;
                }
            }
        }
    }
    
    /*
     * locates a specific letter in the matrix and
     * returns the coordinates, or {-1,-1} if not found
     */
    protected int[] findLetter(char c)
    {
        // loop through matrix, looking for c
        int row = -1;
        int col = -1;
        for (int j = 0; j < size; j++)
        {
            for (int k = 0; k < size; k++)
            {
                // store position if we find a match
                if (matrix[j][k] == c)
                {
                    row = j;
                    col = k;
                }
            }
        }
        
        // create array of coordinates and return it
        int[] position = {row, col};
        return position;
    }
    
    /*
     * returns the letter at a given location in matrix
     */
    protected char returnLetter(int[] loc)
    {
        return matrix[loc[0]][loc[1]];
    }
    
    /*
     * checks that keyword contains distinct letters
     * and is the correct length
     */
    private boolean isValidKeyword(String word)
    {
        // ensure we have only letters
        if (!word.matches("[a-zA-Z]+"))
            return false;
        
        // split word to char array - makes it easier to work with
        char[] wordArray = word.toCharArray();
        
        // ensure length is correct
        if (wordArray.length != 5)
            return false;
        
        // check each letter against each other letter
        // return false if any are the same
        for (int i = 0; i < wordArray.length; i++)
            for (int j = i+1; j < wordArray.length; j++)
                if (wordArray[i] == wordArray[j])
                    return false;
        
        // if it gets here, it's valid
        return true;
    }
    
    /*
     * checks that keyletter is a letter that isn't in the keyword
     */
    private boolean isValidKeyletter(char c)
    {
        // ensure it's a letter
        if (!Character.isLetter(c))
            return false;
        
        // ensure it isn't in the keyword
        for (int i = 0; i < 5; i++)
            if (keyword.charAt(i) == c)
                return false;
        
        return true;
    }
    
    /*
     * checks if the current letter is
     * in the allowable range for this matrix (ie, is not
     * either the keyletter or in the keyword. Is used when
     * populating the array...
     */
    private boolean isAllowed(char c)
    {
        if (c == keyletter)
            return false;
        
        for (int i = 0; i < size; i++)
        {
            if (keyword.charAt(i) == c)
                return false;
        }
        
        // if here, the letter isn't in either the
        // keyword, or the keyletter
        return true;
    }
    
    /*
     * prints out matrix to the screen for debugging reasons
     */
    protected void print()
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
                System.out.print(matrix[i][j] + "|");
            System.out.println();
        }
    }
} // end Matrix