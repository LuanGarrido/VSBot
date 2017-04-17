package vbs.filter;

public abstract class UrlFilterFactory {

	public static UrlFilter build(String priorizerType){
		if(priorizerType == null || priorizerType.trim().equals(""))
			throw new NullPointerException("Unkown filter type");
		
		String[] regexPatterns = UrlFilterFactory.loadRegexPatterns();
		
		switch (priorizerType){
			case "SetUrlFilter":
				return new SetUrlFilter(regexPatterns);
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
