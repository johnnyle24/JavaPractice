package assignment10;

/**
 * 
 * @author Rithie Penn, Johnny Le
 *
 */

public class MediocreHashFunctor implements HashFunctor { 
    public int hash(String item) { 
    	int hashcode = 0;
    	for (int i=0; i < item.length(); i++) {
    		hashcode += (int)item.charAt(i);
    	}
    	
      return Math.abs(hashcode); 
    } 
  }