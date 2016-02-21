package timingstuff;

import assignment6.Timer;

public class GenericTiming {

	public static void main(String[] args) {
		Timer average = new Timer();
		Timer loopTime = new Timer();
		
		for (int i = 100; i <= 1000000; i *= 2) {
			
			int timesLooped = 15000;

			average.start();
			
			for (int loop = 0; loop < timesLooped; loop++) {
				
			}
			
			double timeForAverage = average.stop();
			
			// calculate loop time
			loopTime.start();

			for (int loop = 0; loop < timesLooped; loop++) {
				
			}
			
			double timeForLoop = loopTime.stop();
			
			System.out.println(i + "\t" + ((timeForAverage - timeForLoop) / timesLooped));
		}
		
	}
}