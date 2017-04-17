package vsb.priorizer;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeapthFirstPriorizer implements Priorizer {

	private Stack stack;

	private static final Logger LOGGER = Logger.getLogger( DeapthFirstPriorizer.class.getName() );
	
	public DeapthFirstPriorizer() {
		this.stack = new Stack();
	}
	
	@Override
	public void push(String url) {		
		if(this.stack == null)
			throw new NullPointerException("The DeapthFirst Priorizer did not instantiate the stack.");
		
		LOGGER.log(Level.INFO, "Pushing url: {0} to stack.", url);
		this.stack.push(url);
	}

	@Override
	public String pop() {
		if(this.stack == null)
			throw new NullPointerException("The DeapthFirst Priorizer did not instantiate the stack.");
		
		try {
			String url = (String) this.stack.pop();
			LOGGER.log(Level.INFO, "Poping url: {0} from stack.", url);
			return url;
		} catch (Exception e) {
			return null;
		}
	}
}
