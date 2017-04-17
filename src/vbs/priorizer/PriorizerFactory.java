package vbs.priorizer;

public abstract class PriorizerFactory {
	
	public static Priorizer build(String priorizerType){
		if(priorizerType == null || priorizerType.trim().equals(""))
			throw new NullPointerException("Unkown priorizer type");
		
		switch (priorizerType){
			case "BreadthFirstPriorizer":
				return new BreadthFirstPriorizer();
			case "DeapthFirstPriorizer":
				return new DeapthFirstPriorizer();
			default:
				throw new NullPointerException("Unkown priorizer type");
		}
	}
	
}
