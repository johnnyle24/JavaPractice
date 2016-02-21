package assignment10;

/**
 * 
 * @author Rithie Penn, Johnny le
 *
 */

public class BadHashFunctor implements HashFunctor {
	public int hash(String item) {
		return item.length();
	}
}
