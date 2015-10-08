/*
 * Takes input for a range, then finds
 * the primes in that range. Will continue
 * until the user has had enough.
 * 
 * Vincent Atanasov, 2014
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class PrimeUser
{
    /*
     * main method
     * takes input for range and
     * prints out primes in that range.
     */
    public static void main(String argv[]) 
    {
        final int EXIT_CONDITION = 2;
        
        // print title and instructions
        System.out.println("- PRIME PROGRAM -");
        System.out.println("This program will ask you for an upper bound for a range \n" +
                           "and then print out the prime numbers in that range.");
        System.out.println("Note: Enter a bound < 2 to exit program.");
        
        // for user input
        Scanner scanner = new Scanner(System.in);
        
        // variables for loop
        int upperBound;
        Range range;
        int[] primes;    // to hold returned primes
        
        // repeat until exit condition met
        while (true)
        {
            upperBound = getInput(scanner);
            
            // exit if input bound is less than exit condition
            if (upperBound < EXIT_CONDITION)
                break;
            
            // create new range
            range = new Range(upperBound);
            
            // use seive method on range to find primes
            primes = range.findPrimes();
            
            // print list of primes
            if (primes.length == 0)
            {
                System.out.println("Sorry, we didn't find any primes.");
            }
            else
            {
                System.out.print("Prime numbers up to " + upperBound + " are: ");
                for (int i = 0; i < primes.length; i++)
                    System.out.print(primes[i] + " ");
                System.out.println();
            }
        }
        
        // close scanner and print goodbye message
        scanner.close();
        System.out.println("Goodbye, user.");
        
    } // end main
    
    /*
     * gets and returns an integer from user
     * - catches InputMismatchExceptions
     */
    private static int getInput(Scanner s)
    {
        int input = 0;
        
        // instruction message
        System.out.print("Please enter range bound: ");
        
        // loops until we have successfully received an int
        while (true)
        {
            try
            {
                input = s.nextInt();
                break;
            }
            catch (InputMismatchException e)
            {
                System.out.print("Bound must be an integer. Try again: ");
                s.next();
            }
        }
        
        return input;
    }
    
} // end PrimeUser