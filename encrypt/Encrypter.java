/*
 * Encrypter class provides the functionality to encrypt
 * messages using the matrix encryption method. The only attribute
 * of the encrypter is a matrix, which contains all of the necessary
 * information and methods to perform the encryption.
 *
 * Note: The encrypter skips over any pair which contains
 * the keyletter. I have decided to adopt this approach
 * as it still allows the user to encrypt a message containing
 * the keyletter, though this is actually not advised.
 * 
 * Vincent Atanasov, 2014
 */

import java.lang.Math;

public class Encrypter
{
    private Matrix matrix;
    
    /*
     * contructor method simply creates matrix
     * - throws exception if keyword/keyletter aren't valid
     */
    public Encrypter(String keyword, char letter) throws Exception
    {
        // create the matrix
        // note: keyword and keyletter are stored in matrix object
        matrix = new Matrix(keyword, letter);
    }
    
    /*
     * encrypts given string and returns encrypted string
     * - may return an error string if the given string contains no valid characters (letters)
     */
    public String encrypt(String string)
    {
        // convert to uppercase and remove everything except characters to work with matrix
        string = string.toUpperCase().replaceAll("[^A-Z]", "");
        
        // return error message if no valid characters to encrypt.
        if (string.length() == 0)
            return "ERROR! Your string didn't contain any letters.";
        
        // split input string into chars
        char[] stringArr = string.toCharArray();
        
        // find how many pairs we'll have
        int numPairs = (int) Math.ceil(stringArr.length / 2);
        
        // loop through and encode each pair
        char a, b;
        for (int i = 0; i < numPairs; i++)
        {
            // get current pair of characters
            a = stringArr[i*2];
            b = stringArr[i*2 + 1];
            
            // store positions of letters in matrix
            // if not there, we'll have -1, -1
            int[] aPos = matrix.findLetter(a);
            int[] bPos = matrix.findLetter(b);
            
            // if one of them is not in the matrix, then we should
            // skip to the next iteration, or the encryption won't work
            if (aPos[0] == -1 || bPos[0] == -1)
                continue;
            
            // store coordinates of new locations
            // based on the swapping algorithm
            int[] aNew = {aPos[0], bPos[1]};
            int[] bNew = {bPos[0], aPos[1]};
            
            // swap to letter in new locations
            stringArr[i*2] = matrix.returnLetter(aNew);
            stringArr[i*2 + 1] = matrix.returnLetter(bNew);
        }
        
        // change array back to string and return it
        String encryptedMessage = new String(stringArr);
        
        return encryptedMessage;
    }
    
    /*
     * simply prints out the matrix, by calling the matrix method
     */
    public void printMatrix()
    {
        matrix.print();
    }
} // end Encrypter