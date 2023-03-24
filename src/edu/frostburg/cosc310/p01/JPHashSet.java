
package edu.frostburg.cosc310.p01;
import edu.frostburg.cosc310.datastructures.hashset.HashSet;
import java.util.*;

/**
 * JPhashSet<T> implements methods from HashSet to create a working HashSet
 * @author Josh
 */
public class JPHashSet<T> implements HashSet<T> {
    
    private static final int DEFAULT_TABLE_SIZE = 10;
    private HashEntry<T>[] elements;            // Array of elements
    private int cells;                          // The number of cells occupied
    private int size;                           // Current size

    /**
     * Inserts element into the hash table. If the item is already present, it does do nothing.
     * returns true if successful, returns false otherwise.
     */
    @Override
    public boolean add(T element) {

        int position = findPosition(element);
        if (isActive(position)) {
            return false;
        }
        elements[position] = new HashEntry<>(element, true);
        if ((double) ++size > ((double) elements.length * .8)) {
            rehash();
        }
        return true;
    }

    /**
     * Removes element from the hash table.
     * returns true if successful, false if otherwise
     */
    
    @Override
    public boolean remove(T element) {
        int position = findPosition(element);
        if (isActive(position)) {
            elements[position].isActive = false;
            size--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Finds if an element is in the array
     * returns true if so, false if not.
     */
    @Override
    public boolean find(T element) {

        int position = findPosition(element);
        return isActive(position);
    }

    /**
     * returns the size of the HashSet
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns size of table
     */
    @Override
    public int tableSize() {

        return elements.length;
    }

    /**
     * Returns true if table is empty,
     * returns false otherwise
     */
    @Override
    public boolean isEmpty() {

        if (elements.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the name of the biggest slacker who ever lived.
     */
    @Override
    public String myName() {

        return "Joshua Pratchios, Programmer Extraordinaire";
    }

    public int hash(T key, int tableSize) {

        int hashVal = 0;

        hashVal = key.hashCode();
        hashVal %= tableSize;
        if (hashVal < 0) {
            hashVal += tableSize;
        }
        return hashVal;
    }

    /**
     * Method that performs quadratic probing resolution.
     *
     * returns the position where the search terminated.
     */
    private int findPosition(T element) {

        int offset = 1;
        int position = hash(element, tableSize());

        while (elements[position] != null && !elements[position].element.equals(element)) {
            position += offset; 
            offset += 2;
            if (position >= elements.length) {
                position -= elements.length;
            }

        }
        return position;
    }

    /**
     * Return true if position exists and is active.
     *
     * returns true if the current position is active.
     */
    private boolean isActive(int position) {
        return elements[position] != null && elements[position].isActive;
    }

    /**
     * Internal method to allocate array.
     *
     */
    private void allocateArray(int arraySize) {
        elements = new HashEntry[nextPrime(arraySize)];
    }

    /**
     * Default Constructor
     * calls the constructor with a size and just sets it to the default.
     */
    public JPHashSet() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Constructor, takes size as a parameter. 
     */
    public JPHashSet(int size) {
        allocateArray(size);
        Empty();
    }

    /**
     * Empties out the hash table
     */
    public void Empty() {
        size = 0;
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
    }

    /**
     * Internal method to find the next prime number
     */
    private static int nextPrime(int n) {
        if(n <= 1) {
            return 2;
        }
        int prime = n;
        boolean found = false;
        
        while(!found) {
            prime++;
            if(isPrime(prime)) {
                found = true;
            }
        }
        return prime; 
    }

    /**
     * Internal method to test if a number is prime. 
     * returns true if so. Returns false if not.
     */
    private static boolean isPrime(int n) {
        if(n <= 1) {
            return false;
        }
        if( n <= 3) {
            return true;
        }
        //purely done to skip the middle 5 numbers in the next loop
        if(n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i = i+6) {
            if (n % i == 0 || n % (i+2) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Expand the hash table.
     */
    private void rehash() {
        HashEntry<T>[] oldArray = elements;
        //System.out.printf("Test");
        // Create a new double-sized, empty table
        allocateArray(2 * oldArray.length);
        cells = 0;
        size = 0;

        for (HashEntry<T> entry : oldArray) {
            if (entry != null && entry.isActive) {
                add(entry.element);
            }
        }
    }

    private static class HashEntry<AnyType> {

        public AnyType element;   // element
        public boolean isActive;  // false if marked as deleted

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean i) {
            element = e;
            isActive = i;
        }
     
    }

    /**
     * toString method prints ALL of the table when called.
     * Dont accidently call this, Josh. 
     */
    @Override
    public String toString() {

        String toString;
        int size = size();

        toString = "\nThe List contains:\n";
        if (isEmpty()) {
            return toString + " 0 elements";
        }

        for (Object s : elements) {
            toString += s;
        }

        return toString;
    }

}
