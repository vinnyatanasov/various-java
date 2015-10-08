/*
 * Program that takes input from user and tests
 * whether it's a palindrome two ways.
 * It will continue as long as the user wishes.
 * 
 * Vincent Atanasov, 2014
 */
 
import java.util.Scanner;

public class PalindromeUser
{
    /* main method:
     * reads in string from user and
     * calculates if the string is a palindrome
     * - will repeat until user decides they've had enough
     */
    public static void main(String argv[]) 
    {
        final String IS_PALINDROME_MESSAGE = "This is a palindrome";
        final String NOT_PALINDROME_MESSAGE = "This is not a palindrome";
        
        // print title
        System.out.println("- PALINDROME PROGRAM -");
        System.out.println("This program will test your input strings to see if they are palindromes.");
        System.out.println("Note: Enter an empty string to exit program.");
        
        // for user input
        Scanner scanner = new Scanner(System.in);
        
        // declare vars use in loop
        StringTest palindromeTest;
        String userString;
        
        // keep the program running until user inputs empty string
        while (true)
        {
            // for aesthetics
            System.out.println("--");
            
            // read in string from the user
            System.out.println("Enter a line of text:");
            userString = scanner.nextLine();
            
            // exit if string entered is empty
            if (userString.equals(""))
                break;
            
            // instantiate a new test object
            palindromeTest = new StringTest(userString);
            
            // find and print results
            // iterative results
            System.out.print("Iterative version... ");
            if (palindromeTest.isPalindromeIterative())
                System.out.println(IS_PALINDROME_MESSAGE);
            else
                System.out.println(NOT_PALINDROME_MESSAGE);
            
            // recursive results
            System.out.print("Recursive version... ");
            if (palindromeTest.isPalindromeRecursive())
                System.out.println(IS_PALINDROME_MESSAGE);
            else
                System.out.println(NOT_PALINDROME_MESSAGE);
        }
        
        // print goodbye message and close scanner
        System.out.println("Goodbye, user.");
        scanner.close();
        
    } // end main
    
} // end PalindromeUser
