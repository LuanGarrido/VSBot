package vsb.filter;

import java.util.HashSet;
import java.util.Set;

public class SetUrlFilter extends BaseUrlFilter implements UrlFilter {
	
	private Set<String> set;
	private String[] regexPatterns;
	
	public SetUrlFilter(String[] regexPatterns) {
		this.set = new HashSet<String>();
	}
	
	@Override
	public Boolean isValid(String url) {		
		if(!this.isValidByRegex(url, this.regexPatterns))
			return false;
		if(!this.isInherentlyValid(url))
			return false;
		
		return true;
	}

	public Boolean isInherentlyValid(String url) { 
		return !this.set.contains(url);
	}
}
