package vbs.filter;

public abstract class BaseUrlFilter {
	
	protected Boolean isValidByRegex(String url, String[] regexPatterns){
		return true;
	}
}
