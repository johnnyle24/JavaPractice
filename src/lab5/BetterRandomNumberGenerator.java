package lab5;

public class BetterRandomNumberGenerator implements RandomNumberGenerator {
	
	long seed;
	
	long const1 = 131;
	
	long const2 = 71;
	
	int num = (int) seed;

	@Override
	public int next_int(int max) {
		
		this.num = (int)(const1*this.num + const2)%max;
		
		return this.num;
	}

	@Override
	public void set_seed(long seed) {
		this.seed = seed;	
	}

	@Override
	public void set_constants(long const1, long const2) {
		
	}
	
}
	