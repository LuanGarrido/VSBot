package vbs.extractor;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import vbs.filter.UrlFilter;
import vbs.priorizer.Priorizer;

public class LinkExtractor {
	
	private UrlFilter urlFilter;
	
	private Priorizer priorizer;
	
	public LinkExtractor(UrlFilter urlFilter, Priorizer priorizer){
		this.urlFilter = urlFilter;
		this.priorizer = priorizer;
	}
	
	public void extractLinks(HtmlPage page){
		this.addAnchors(page);
		this.addButtons(page);
	}
	
	private void addAnchors(HtmlPage page){
		List<HtmlAnchor> anchors = page.getAnchors();
		for(HtmlAnchor anchor : anchors){
			String url = anchor.getHrefAttribute();
			if(this.urlFilter.isValid(url))
				this.priorizer.push(url);
			else
				continue;
		}
	}
	
	private void addButtons(HtmlPage page){
		
	}
}
