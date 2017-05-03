package vsb.model;

import java.util.List;

import vsb.filter.UrlFilter;

public class Seed {
	
	private String url;
	private List<String> regexPatterns;
	private boolean useJavascript;
	private UrlFilter urlFilter;
	
	public Seed(){
		
	}
	
	public Seed(String url, List<String> regexPatterns, boolean userJavascript, UrlFilter urlFilter){
		this.url = url;
		this.regexPatterns = regexPatterns;
		this.useJavascript = userJavascript;
		this.urlFilter = urlFilter;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getRegexPatterns() {
		return regexPatterns;
	}

	public void setRegexPatterns(List<String> regexPatterns) {
		this.regexPatterns = regexPatterns;
	}

	public boolean isUseJavascript() {
		return useJavascript;
	}

	public void setUseJavascript(boolean useJavascript) {
		this.useJavascript = useJavascript;
	}

	public UrlFilter getUrlFilter() {
		return urlFilter;
	}

	public void setUrlFilter(UrlFilter urlFilter) {
		this.urlFilter = urlFilter;
	}
	
	@Override
	public Seed clone(){
		Seed seed = new Seed();
		seed.setUrl(this.getUrl());
		seed.setRegexPatterns(this.getRegexPatterns());
		seed.setUseJavascript(this.isUseJavascript());
		seed.setUrlFilter(this.getUrlFilter());
		return seed;
	}
}
