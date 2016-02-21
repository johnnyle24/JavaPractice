package assignment3;
 
import java.util.ArrayList;
import java.util.Collection;

import assignment6.Timer;
 
public class SetTimer<E> {
       
    public static void main(String[] args) {
    	
    	/**
    	 * This is the test used for determining the run time of the method contain()
    	 */
           
        Collection<Integer> intCollection = new ArrayList<Integer>();
        
        int randomNum;
        Timer t = new Timer();
       
        // We changed i <= j, for 100000j for j from 1 to 20
        
        for (int i = 1; i <= 2000000; i++) {
            intCollection.add(i);
        }
       
        int average = 0;
        
        for (int j = 0; j < 1000; j++) {
        	
            // We changed i <= j, for 100000j for j from 1 to 20
        	
            randomNum = 1 + (int) (Math.random() * 2000000);
            t.start();
            intCollection.contains(randomNum);
            average = (int) (average + t.stop());
        }
        
        System.out.print(average/1000);
        
    }
}