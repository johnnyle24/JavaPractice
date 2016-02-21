package assignment10;

/**
 * 
 * @author Rithie Penn, Johnny Le
 *
 */
public class GoodHashFunctor implements HashFunctor { 
    public int hash(String item) {
    	
    	int hashcode = 31;
    	for (int i = 0; i < item.length(); i++) {
    		hashcode = ((hashcode << 5) - hashcode) + (int)item.charAt(i);
    	}
      return Math.abs(hashcode); //if there is negative hash take abs val
    } 
  }