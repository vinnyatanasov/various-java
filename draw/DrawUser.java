/*
 * DrawUser is the glue between the other classes. It handles the creation
 * of other objects, the communication between said objects, and the main logic
 * of the program.
 * 
 * Vincent Atanasov, 2014
 */

import java.io.FileNotFoundException;

public class DrawUser
{
    /*
     * main method - creates file handler, asks for lines from the file
     * and creates rectangles with responses until encounters a negative integer
     */
    public static void main(String args[]) throws Exception
    {
        // create new file handler
        FileHandler handler = null;
        // if we have two arguments, create a file handler with those passed in
        // if we don't, exit the program
        try
        {
            if (args.length == 2)
                handler = new FileHandler(args[0], args[1]);
            else
                throw new Exception("Usage - java User inputFile.txt outputFile.txt");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("IOError: " + e.getMessage());
            System.exit(1);
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
        
        // get the height and width from the file (first two ints)
        int areaHeight = handler.readInt();
        int areaWidth  = handler.readInt();
        
        // create a drawing area with the values from the file
        Matrix drawingArea = null;
        try
        {
            drawingArea = new Matrix(areaHeight, areaWidth);
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
        
        // create temporary variables to hold next values from file
        int[] rectCoords = new int[4];
        char rectChar = '-';
        int temp;           // for holding the integer when read
        int index = 0;      // to keep track of where we are in file
        
        // read next line, then loop through and read the rest of the file, until we hit
        // the end of file character
        while (true)
        {
            // store values in array or as char depending which element we're on
            // if index is 4, we're at the fifth line, so we are expecting a char
            // and we can then create a triangle and draw it to the matrix
            if (index == 4)
            {
                // read the next char from the file
                rectChar = handler.readChar();

                // create new rectangle and draw it to the matrix
                // there are possible exceptions to catch when creating and drawing rectangle 
                Rectangle rectangle;
                try
                {
                    rectangle = new Rectangle(rectCoords[0], rectCoords[1], rectCoords[2], rectCoords[3], rectChar);
                    rectangle.drawTo(drawingArea);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.err.println("Error: Rectangle ending on line " + handler.getLineNumber() +
                                       " did not fit in drawing area.");
                }
                catch (Exception e)
                {
                    System.err.println("Error: Rectangle ending on line " + handler.getLineNumber() +
                                       " is not a valid rectangle.");
                }
                
                // reset index to 0, for next iteration
                index = 0;
            }
            else
            {
                // get the next int from the file
                temp = handler.readInt();
                
                // if we hit a negative number, break out as it
                // signals the end of the file
                if (temp < 0)
                    break;
                
                // if not negative, put it in the array
                rectCoords[index] = temp;
                
                // increment index for next iteration
                index++;
            }
        }
        
        // close input stream
        handler.closeInputStream();
        
        // print the final drawing area to the file
        handler.printTo(drawingArea);
        
        // close output stream
        handler.closeOutputStream();
    }
} // end DrawUser
