package vsb.fetcher;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import vsb.extractor.LinkExtractor;
import vsb.filter.UrlFilter;
import vsb.priorizer.Priorizer;

public class HTTPFetcher {
	
	private String url;
	private WebClient webClient;
	
	private UrlFilter urlFilter;
	private Priorizer priorizer;
	
	private static final Integer Javascript_Wait_Timeout = 1000; 
	
	private static final Logger LOGGER = Logger.getLogger( HTTPFetcher.class.getName() );
	
	public HTTPFetcher(String url, UrlFilter urlFilter, Priorizer priorizer){
		this.url = url;
		this.urlFilter = urlFilter;
		this.priorizer = priorizer;
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
	
	public void download(){
		try {
			LOGGER.log(Level.INFO, "Starting to fetch root url: {0}", this.url);
			HtmlPage page = this.webClient.getPage(url);
			(new LinkExtractor(this.urlFilter, this.priorizer)).extractLinks(page);
			LOGGER.log(Level.INFO, "\n*******\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
