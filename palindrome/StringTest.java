/*
 * StringTest gives the functionality to
 * test if strings against different criteria.
 * There is an iterative and a recursive method.
 * 
 * Vincent Atanasov, 2014
 */

public class StringTest
{
    // instance variables - original string, cleaned string and length of string
    private final String originalString;
    private final String cleanedString;
    private final int cleanedLength;
    
    /*
     * contructor
     * - cleans string and stores string and length
     */
    StringTest(String str)
    {
        originalString = str;
        cleanedString = str.toLowerCase().replaceAll("\\W", "");
        cleanedLength = cleanedString.length();
    }
    
    // method to return cleaned string
    public String getCleanedString()
    {
        return cleanedString;
    }
    
    /* 
     * isPalindromeIterative:
     * Works out if string is a palindrome using iterative method.
     * Returns true of false.
     */
    public boolean isPalindromeIterative()
    {
        // return false if string is empty
        if (cleanedLength == 0)
            return false;
        
        int halfway = cleanedLength / 2;
        char first, last;
        
        // loop through string to halfway, comparing next char with reverse
        for (int i = 0; i <= halfway; i++)
        {
            first = cleanedString.charAt(i);
            last = cleanedString.charAt((cleanedLength - 1) - i);
            
            // compare the chars
            if (first != last)
                return false;
        }
        
        // if we get here, it's a palindrome
        return true;
    }
    
    /* 
     * isPalindromeRecursive:
     * When called with no arguments, it simply calls the same
     * method but with the string and length attributes of object
     */
    public boolean isPalindromeRecursive()
    {
        // return false if string is empty
        if (cleanedLength == 0)
            return false;
        
        // call with cleaned string
        return isPalindromeRecursive(cleanedString);
    }
    
    /* 
     * isPalindromeRecursive:
     * Works out if string is a palindrome using recursive method.
     * Returns true of false.
     */
    private boolean isPalindromeRecursive(String str)
    {
        // store length of string
        int length = str.length();
        
        // base case to end recursion
        if (length <= 1)
            return true;
        
        // if first and last same, call itself again with substring
        if (str.charAt(0) == str.charAt(length - 1))
            return (isPalindromeRecursive(str.substring(1, length - 1)));
        
        // if we get here, it's not a palindrome
        return false;
    }
    
} // end StringTest
