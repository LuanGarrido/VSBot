package vsb.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseUrlFilter {
	
	private List<Pattern> regexPatterns;
	
	protected void compilePatterns(List<String> regexPatterns){
		this.regexPatterns = new ArrayList<Pattern>();
		for(String regexPattern : regexPatterns){
			Pattern pattern = Pattern.compile(regexPattern);
			this.regexPatterns.add(pattern);
		}		
	}
	
	protected Boolean isValidByRegex(String url){
		for(Pattern pattern : this.regexPatterns){
			Matcher matcher = pattern.matcher(url);
			if(matcher.matches())
				return true;
		}
		return false;
	}
}
