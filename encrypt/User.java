/*
 * User class controls the encrypt program,
 * taking input from the user, creating an instance
 * of the encrypt class and passing the message to be encrypted.
 * 
 * Vincent Atanasov, 2014
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class User
{
    /*
     * prints opening messages and creates new instance of user
     */
    public static void main(String argv[])
    {
        // print title and instructions
        System.out.println("- ENCRYPTION PROGRAM -");
        System.out.println("This program will ask for a keyword, keyletter and message \n" +
                           "and then encrypt that message using the keys.");
        System.out.println("Note: Keyword must contain distinct letters and be exactly 5 \n" +
                           "characters long. The keyletter must not be in the keyword.");
        
        // create new user and run
        User user = new User();
        user.run();
    }
    
    /*
     * runs the program: gets input, and makes calls to other
     * classes/objects
     */
    private void run()
    {
        // create scanner object
        Scanner scanner = new Scanner(System.in);
        
        // get input for keyword
        String keyword = getInputString(scanner, "Enter a five letter keyword: ");
        
        // get input for keyletter
        String tempLetter = getInputString(scanner, "Enter a single keyletter: ");
        
        // convert letter to a character
        char letter = tempLetter.charAt(0);
        
        // create new encrypter object using inputs
        Encrypter encrypter = null;
        while (true)
        {
            try
            {
                // try to create a new encrypter object
                encrypter = new Encrypter(keyword, letter);
                // if successful, break out of loop
                break;
            }
            catch (Exception e)
            {
                System.out.println("ERROR: " + e.getMessage());
                System.exit(1);
            }
        }
        
        // print matrix to screen for testing
        //encrypter.printMatrix();
        
        // get input for message
        String message = getInputString(scanner, "Enter a message to be encrypted: ");
        
        // encrypt message and print it out
        String encryptedMessage = encrypter.encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);
        
        // close scanner
        scanner.close();
    }
    
    /*
     * gets and returns input string, ensuring it is not empty
     */
    private String getInputString(Scanner s, String message)
    {
        // print given message and get input string
        System.out.print(message);
        String str = s.nextLine();
        
        // ensure string is not empty
        while (str.equals(""))
        {
            System.out.print("Oops. Please try again: ");
            str = s.nextLine();
        }
        
        return str;
    }
} // end User