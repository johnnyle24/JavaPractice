package assignment7;

import assignment6.Timer;

public class BalancedSymbolCheckerTimer {

	public static void main(String[] args) {
		Timer average = new Timer();
		
		for (int i = 15000; i <= 1000000; i += 10000) {
			
			int timesLooped = 15000;
			
			MyStack<Character> timingStack = new MyStack<Character>();
			for (int j = 0; j < i; j++) {
				timingStack.push('{');
			}

			average.start();
			
			for (int loop = 0; loop < timesLooped; loop++) {
				//timingStack.push('}');
				//timingStack.peek();
				timingStack.pop();
			}
			
			double timeForAverage = average.stop();
			
			System.out.println(i + "\t" + ((timeForAverage) / timesLooped));
		}
		
	}
}
