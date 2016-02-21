package assignment6;
 
public class Timer {
    private long startTime;
    private long endTime;
   
    public Timer(){
    }
   
    public void start(){
        startTime = System.nanoTime();
    }
   
    public long stop(){
        endTime = System.nanoTime();
        return (endTime - startTime);
    }
}