package assignment6;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyLinkedListTiming {

	public static void main(String[] args) {
		Timer average = new Timer();
		Timer loopTime = new Timer();
		
		for (int i = 100; i <= 1000000; i *= 2) {
		
			int timesLooped = 15000;

			average.start();
			
			for (int loop = 0; loop < timesLooped; loop++) {
//				MyLinkedList<Integer> linkedList = new MyLinkedList<Integer>();
//				for (int j = 0; j < i; j++) {
//					linkedList.addFirst(0);
//					}
//				linkedList.remove((i/2));
				
				
//				ArrayList<Integer> arrL = new ArrayList<Integer>();
//				for (int j = 0; j < i; j++) {
//					arrL.add(0);
//				}
//				arrL.remove((i/2));
				
				
				LinkedList<Integer> linkedList = new LinkedList<Integer>();
				for (int j = 0; j < i; j++) {
					linkedList.addFirst(0);
					}
				linkedList.remove((i/2));
				
			}
			
			double timeForAverage = average.stop();
			
			// calculate loop time
			loopTime.start();

			for (int loop = 0; loop < timesLooped; loop++) {
//				MyLinkedList<Integer> linkedList2 = new MyLinkedList<Integer>();
//				for (int j = 0; j < i; j++) {
//					linkedList2.addFirst(0);
//					}
				
				
//				ArrayList<Integer> arrL2 = new ArrayList<Integer>();
//				for (int j = 0; j < i; j++) {
//					arrL2.add(0);
//				}
				
				LinkedList<Integer> linkedList = new LinkedList<Integer>();
				for (int j = 0; j < i; j++) {
					linkedList.addFirst(0);
					}
				
				
			}
			
			
			double timeForLoop = loopTime.stop();
			
			System.out.println(i + "\t" + ((timeForAverage - timeForLoop) / timesLooped));
		}
	}
	
}
