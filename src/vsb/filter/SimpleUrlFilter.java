package vsb.filter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleUrlFilter extends BaseUrlFilter implements UrlFilter {
	
	private Set<String> set;	
	
	public SimpleUrlFilter(List<String> regexPatterns) {
		this.set = new HashSet<String>();		
		this.compilePatterns(regexPatterns);
	}
	
	
	@Override
	public Boolean isValid(String url) {		
		if(!this.isValidByRegex(url))
			return false;
		if(!this.isInherentlyValid(url))
			return false;
		
		return true;
	}

	public Boolean isInherentlyValid(String url) { 
		return !this.set.contains(url);
	}
}
