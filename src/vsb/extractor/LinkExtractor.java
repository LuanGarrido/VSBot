package vsb.extractor;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import vsb.filter.UrlFilter;
import vsb.model.Seed;

public class LinkExtractor {
	
	private Seed seed;
	private List<Seed> seeds;	
	
	public LinkExtractor(Seed seed){
		this.seed = seed;
		this.seeds = new ArrayList<Seed>();
	}
	
	public List<Seed> extractLinks(HtmlPage page){
		this.addAnchors(page);
		this.addButtons(page);
		
		return this.seeds;
	}
	
	private void addAnchors(HtmlPage page){
		List<HtmlAnchor> anchors = page.getAnchors();
		for(HtmlAnchor anchor : anchors){
			String url = anchor.getHrefAttribute();
			if(this.seed.getUrlFilter().isValid(url))
				this.addSeed(url);
			else
				continue;
		}
	}
	
	private void addButtons(HtmlPage page){
		
	}
	
	private void addSeed(String url){
		Seed newSeed = this.seed.clone();
		newSeed.setUrl(url);
		this.seeds.add(newSeed);
	}
}
