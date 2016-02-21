package assignment3;
 
import java.util.ArrayList;
import java.util.Collection;

import assignment6.Timer;

/**
 * This is the timer utilized to calculate the run time of the add() method.
 */
 
public class SetTimer2 {
       
    public static void main(String[] args) {
           
        Collection<Integer> intCollection = new ArrayList<Integer>();
        int randomNum;
        Timer t = new Timer();
       
        // we changed i <= j, for 100000j for j from 1 to 20
           
        for (int i = 1; i <= 100000; i++) {
                intCollection.add(i);
        	}
        
        int average = 0;
        
        for(int i = 0; i < 1000; i++) {
        	randomNum = 1 + (int) (Math.random() * 100000);
	        intCollection.remove(randomNum);
	        t.start();
	        intCollection.add(randomNum);
	        average = (int) (average + t.stop());
        	}
        
        System.out.println(average/1000);
        
        }
	}

