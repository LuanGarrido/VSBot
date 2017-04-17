package vsb.priorizer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BreadthFirstPriorizer implements Priorizer{
	
	private Queue<String> queue;
	
	private static final Logger LOGGER = Logger.getLogger( BreadthFirstPriorizer.class.getName() );
	
	public BreadthFirstPriorizer() {
		this.queue = new LinkedList<String>();
	}
	
	@Override
	public void push(String url) {			
		if(this.queue == null)
			throw new NullPointerException("The BreadthFirst Priorizer did not instantiate the queue.");
		
		LOGGER.log(Level.INFO, "Pushing url: {0} to queue.", url);
		this.queue.add(url);	
	}

	@Override
	public String pop() {
		if(this.queue == null)
			throw new NullPointerException("The BreadthFirst Priorizer did not instantiate the queue.");
		
		String url = this.queue.poll();
		LOGGER.log(Level.INFO, "Poping url {0} from queue.", url);
		return url;
	}
}
