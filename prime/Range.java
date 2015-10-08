/*
 * PrimeTest class gives functionality to define
 * a range and find the prime numbers in the range.
 * 
 * Vincent Atanasov, 2014
 */

import java.lang.Math;

public class Range
{
    // instance variables
    private int upperBound;
    private boolean[] primeMarkers; // booleans to indicate prime
    private int nPrimes;            // count of primes in range
    
    /*
     * constructor - stores bound and
     * initialises markers array of booleans
     */
    public Range(int upperBound)
    {
        // make sure bound is at least 2, fallback to 2 if not
        if (upperBound >= 2)
            this.upperBound = upperBound;
        else
            this.upperBound = 2;
        
        // initialise markers array, one for each number
        primeMarkers = new boolean[upperBound + 1];
        
        // set all markers to true to begin with
        for (int i = 0; i < primeMarkers.length; i++)
            primeMarkers[i] = true;
    }
    
    /*
     * check factors (f) up to square root
     * afterwards we'll have trues in all of the primes
     * and nPrimes will hold the count of primes
     */
    public int[] findPrimes()
    {
        // vars for loop
        int boundSqrt = (int) Math.sqrt(upperBound);
        nPrimes = primeMarkers.length - 2; // - 2 to ignore the 0 & 1..
        
        // from 2 -> Sqrt, go through range and check if f divides n
        for (int f = 2; f <= boundSqrt; f++)
        {
            // only go if f is prime
            if (primeMarkers[f])
            {
                // go from f+1, checking if f is factor
                for (int n = f+1; n < primeMarkers.length; n++)
                {
                    // ignore non-primes
                    if (primeMarkers[n])
                    {
                        // not prime if n divides by f
                        if (n % f == 0)
                        {
                            primeMarkers[n] = false;
                            // take one from numPrimes count
                            nPrimes--;
                        }
                    }
                }
            }
        }
        
        return primesArray();
    }
    
    /*
     * creates and returns array of primes
     * using the markers array as reference
     */
    private int[] primesArray()
    {
        // create array to hold all found primes
        int[] primesArr = new int[nPrimes];
        
        // populate array with found primes
        int pos = 0;
        for (int i = 2; i < primeMarkers.length; i++)
        {
            if (primeMarkers[i]) {
                primesArr[pos] = i;
                pos++;
            }
        }
        
        return primesArr;
    }
    
} // end Range