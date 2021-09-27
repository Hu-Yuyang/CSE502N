package hash;

import java.util.LinkedList;

//
// STRINGTABLE.JAVA
// A hash table mapping Strings to their positions in the the pattern sequence
// You get to fill in the methods for this part.
//
public class StringTable {
    
    private LinkedList<Record>[] buckets;
    private int nBuckets;

    //
    // number of records currently stored in table --
    // must be maintained by all operations
    //
    public int size;
    
    
    //
    // Create an empty table with nBuckets buckets
    //
    @SuppressWarnings("unchecked")
	public StringTable(int nBuckets)
    {
    	this.nBuckets = nBuckets;
    	buckets = new LinkedList[nBuckets];
	
    	// TODO - fill in the rest of this method to initialize your table
    	this.size = 0;
    	for (int i=0; i < nBuckets; i++) {
    		buckets[i]=null;
    	}
    }
    
    
    /**
     * insert - inserts a record to the StringTable
     *
     * @param r
     * @return true if the insertion was successful, or false if a
     *         record with the same key is already present in the table.
     */
    public boolean insert(Record r) 
    {  
    	// TODO - implement this method
    	
    	
    	int hash = stringToHashCode(r.key);
    	int index = toIndex(hash);
     	LinkedList<Record> datas = buckets[index];

    	
    	if (datas == null) {
    		datas = new LinkedList<Record>();
    		Record data = r;
    		datas.add(data);
    		size++;
    		buckets[index] = datas;
    		return true;
    	}
    	else {
    		for (Record data:datas) {
    			if (data.key.equals(r.key)) {
    				return false;
    			}
    		}
            Record data = r;
    		datas.add(data);
    		size++;
    		return true;
    	}
    }
    
    
    /**
     * find - finds the record with a key matching the input.
     *
     * @param key
     * @return the record matching this key, or null if it does not exist.
     */
    public Record find(String key) 
    {
    	// TODO - implement this method
    	int hash = stringToHashCode(key);
    	int index = toIndex(hash);
    	LinkedList<Record> datas = buckets[index];
    	if (datas==null) {
    		return null;
    	}
    	for(Record data:datas) {
    		if(data.key.equals(key)) {
    			return data;
    		}
    	}
    	
	
    	return null;
    }
    
    
    /**
     * remove - finds a record in the StringTable with the given key
     * and removes the record if it exists.
     *
     * @param key
     */
    public void remove(String key) 
    {
    	// TODO - implement this method
    	int hash = stringToHashCode(key);
    	int index = toIndex(hash);
    	
    	LinkedList<Record> datas = buckets[index];
    	
    	if (datas==null) {
    		return;
    	}
    	
    	for(Record data:datas) {
    		if(data.key.equals(key)) {
    			datas.remove(data);
    			size--;
    			return;
    		}
    	}
    }
    

    /**
     * toIndex - convert a string's hashcode to a table index
     *
     * As part of your hashing computation, you need to convert the
     * hashcode of a key string (computed using the provided function
     * stringToHashCode) to a bucket index in the hash table.
     *
     * You should use a multiplicative hashing strategy to convert
     * hashcodes to indices.  You can use the floating-point computation.
     */
    private int toIndex(int hashcode)
    {
    	// Fill in your own hash function here
    	
    	double A= 0.9887;
    	double tmp=(hashcode*A)%1;
    	int index=(int)(tmp*nBuckets);
    	index=Math.abs(index);
    	return index;
    	
//    	double tmp = hashcode % (nBuckets - 1);
//    	int index = (int)tmp;
//    	index = Math.abs(index);
//    	return index;
    }
    
    
    /**
     * stringToHashCode
     * Converts a String key into an integer that serves as input to
     * hash functions.  This mapping is based on the idea of integer
     * polynomial hashing, where we do multiplies for successive
     * characters of the key (adding in the position to distinguish
     * permutations of the key from each other).
     *
     * @param string to hash
     * @returns hashcode
     */
    int stringToHashCode(String key)
    {
    	int A = 1952786893;
	
    	int v = A;
    	for (int j = 0; j < key.length(); j++)
	    {
    		char c = key.charAt(j);
    		v = A * (v + (int) c + j) >> 16;
	    }
	
    	return v;
    }

    /**
     * Use this function to print out your table for debugging
     * purposes.
     */
    public String toString() 
    {
    	StringBuilder sb = new StringBuilder();
	
    	for(int i = 0; i < nBuckets; i++) 
	    {
    		sb.append(i+ "  ");
    		if (buckets[i] == null) 
		    {
    			sb.append("\n");
    			continue;
		    }
    		for (Record r : buckets[i]) 
		    {
    			sb.append(r.key + "  ");
		    }
    		sb.append("\n");
	    }
    	return sb.toString();
    }
}
