package vsb.priorizer;

import vsb.model.Seed;

public interface Priorizer {
	
	public void push(Seed seed);
	
	public Seed pop();
	
	public boolean isEmpty();
}
