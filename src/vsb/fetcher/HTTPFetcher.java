package vsb.fetcher;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import vsb.extractor.LinkExtractor;
import vsb.filter.UrlFilter;
import vsb.model.Seed;

public class HTTPFetcher implements Callable<List<Seed>>{
		
	private WebClient webClient;
	private Seed seed;
	
	private static final Integer Javascript_Wait_Timeout = 1000; 
	private static final Logger LOGGER = Logger.getLogger( HTTPFetcher.class.getName() );
	
	public HTTPFetcher(Seed seed){
		this.seed = seed;
		initSimulatedBrowser();	
	}

	private void initSimulatedBrowser() {
		try {
			this.webClient = new WebClient(BrowserVersion.CHROME);
			this.webClient.getOptions().setCssEnabled(false);
			this.webClient.getOptions().setAppletEnabled(false);
			this.webClient.waitForBackgroundJavaScript(HTTPFetcher.Javascript_Wait_Timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Seed> download(){
		try {
			LOGGER.log(Level.INFO, "Starting to fetch root url: {0}", this.seed.getUrl());
			HtmlPage page = this.webClient.getPage(this.seed.getUrl());
			LinkExtractor linkExtractor = new LinkExtractor(this.seed);
			List<Seed> seeds = linkExtractor.extractLinks(page);
			LOGGER.log(Level.INFO, "\n*******\n\n");
			return seeds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Seed> call() throws Exception {
		return this.download();
	}
}
