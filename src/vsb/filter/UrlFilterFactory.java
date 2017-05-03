package vsb.filter;

import java.util.List;

public abstract class UrlFilterFactory {

	public static UrlFilter build(String priorizerType, List<String> regexPatterns){
		if(priorizerType == null || priorizerType.trim().equals(""))
			throw new NullPointerException("Unkown filter type");		
		
		switch (priorizerType){
			case "SimpleUrlFilter":
				return new SimpleUrlFilter(regexPatterns);
			case "BloomUrlFilter":
				return new BloomUrlFilter(regexPatterns);
			default:
				throw new NullPointerException("Unkown filter type");
		}
	}
	
	protected static String[] loadRegexPatterns(){
		return null;
	}
}
