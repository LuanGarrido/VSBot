package vsb.priorizer;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import vsb.model.Seed;

public class DeapthFirstPriorizer implements Priorizer {

	private Stack stack;

	private static final Logger LOGGER = Logger.getLogger( DeapthFirstPriorizer.class.getName() );
	
	public DeapthFirstPriorizer() {
		this.stack = new Stack();
	}
	
	@Override
	public void push(Seed seed) {		
		if(this.stack == null)
			throw new NullPointerException("The DeapthFirst Priorizer did not instantiate the stack.");
		
		LOGGER.log(Level.INFO, "Pushing url: {0} to stack.", seed.getUrl());
		this.stack.push(seed);
	}

	@Override
	public Seed pop() {
		if(this.stack == null)
			throw new NullPointerException("The DeapthFirst Priorizer did not instantiate the stack.");
		
		try {
			Seed seed = (Seed) this.stack.pop();
			LOGGER.log(Level.INFO, "Poping url: {0} from stack.", seed.getUrl());
			return seed;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.stack.isEmpty();		
	}
}
