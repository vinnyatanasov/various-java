/*
 * FileHandler class takes care of all of the interaction with
 * the files, both input and output. It provides a series of methods
 * to get types of data from the file and also allows for us to
 * print an object to the output file.
 * 
 * Vincent Atanasov, 2014
 */
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler
{
    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private int lineNumber = 0;
    
    /*
     * constructor method
     * - initialises the input and output streams to the supplied files
     */
    public FileHandler(String inputFile, String outputFile) throws Exception
    {
        // make sure we have something that may be a file name
        // match if we have any series of characters followed by a period followed
        // by some combination of letters
        String expression = ".*\\.[a-z]+";
        if (!inputFile.matches(expression) || !outputFile.matches(expression))
            throw new Exception("File name is not valid.");
        
        // initialise input stream on supplied file
        inputStream = new BufferedReader(new FileReader(inputFile));
        
        // initialise output stream on supplied file
        outputStream = new PrintWriter(new FileOutputStream(outputFile));
    }
    
    /*
     * reads the next int from the input file
     */
    public int readInt()
    {
        // reads the next line
        String temp = this.readLine();
        // create a temp integer
        int integer = 0;
        
        // try to parse the line as an int and if successful, return it
        // - if it isn't possible, we'll get a number format exception
        try
        {
            integer = Integer.parseInt(temp);
        }
        catch (NumberFormatException e)
        {
            System.err.println("Error: An invalid integer was found on line " + lineNumber +
                               " of input file.");
            System.exit(1);
        }
        
        return integer;
    }
    
    /*
     * reads the next char from the input file
     */
    public char readChar()
    {
        // read in the next line, and return the first char on the line
        String temp = this.readLine();
	
	    // looking for a following character (not allowed)
	    // this was needed to find a negative value on character lines
	    try
	    {
			// expecting to throw exception here because should only be one character
			// on this line - if no exception thrown, print and error and exit the program
			char second = temp.charAt(1);
			System.err.println("Error: More than one character was found on line " + lineNumber +
                               " of input file.");
            System.exit(1);
		}
		catch (StringIndexOutOfBoundsException e)
		{
			// just to catch expected exception
		}

        return temp.charAt(0);
    }
    
    /*
     * reads the next line from the input file and returns the result
     * throws an exception if it is null (no lines left)
     * - can be used to read/return string to user, and used in other methods (eg. readInt)
     */
    public String readLine()
    {
        // create new empty string to read line to
        String line = "";
        lineNumber++;
        
        // read in the next line and return it
        // - if it is empty or null, throw an exception
        try
        {
            line = inputStream.readLine();
            
            if (line == null)
                throw new Exception("The end of file was reached on line " + lineNumber + ".");
            else if (line.equals(""))
                throw new Exception("A blank line was found on line " + lineNumber + " of input file.");
        }
        catch (IOException e)
        {
            System.err.println("IOError: " + e.getMessage());
            System.exit(1);
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
        
        return line;
    }
    
    /*
     * prints an object to the output file
     */
    public void printTo(Object obj)
    {
        outputStream.print(obj);
    }
    
    /*
     * closes input stream
     * - to be called when finished reading from the file
     */
    public void closeInputStream()
    {
        try
        {
            inputStream.close();
        }
        catch (IOException e)
        {
            System.err.println("IOError: " + e.getMessage());
            System.exit(1);
        }
    }
    
    /*
     * closes output stream
     * - to be called when finished printing to the file
     */
    public void closeOutputStream()
    {
        outputStream.close();
    }
    
    /*
     * simply returns line number for use in user class
     */
    public int getLineNumber()
    {
        return lineNumber;
    }
} // end FileHandler
