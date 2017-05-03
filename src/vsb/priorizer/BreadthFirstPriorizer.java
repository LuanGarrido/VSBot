package vsb.priorizer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import vsb.model.Seed;


public class BreadthFirstPriorizer implements Priorizer{
	
	private Queue<Seed> queue;
	
	private static final Logger LOGGER = Logger.getLogger( BreadthFirstPriorizer.class.getName() );
	
	public BreadthFirstPriorizer() {
		this.queue = new LinkedList<Seed>();
	}
	
	@Override
	public void push(Seed seed) {			
		if(this.queue == null)
			throw new NullPointerException("The BreadthFirst Priorizer did not instantiate the queue.");
		
		LOGGER.log(Level.INFO, "Pushing url: {0} to queue.", seed.getUrl());
		this.queue.add(seed);	
	}

	@Override
	public Seed pop() {
		if(this.queue == null)
			throw new NullPointerException("The BreadthFirst Priorizer did not instantiate the queue.");
		
		Seed seed = this.queue.poll();
		LOGGER.log(Level.INFO, "Poping url {0} from queue.", seed.getUrl());
		return seed;
	}

	@Override
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}
}
