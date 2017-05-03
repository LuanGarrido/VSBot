package vsb.crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import vsb.fetcher.HTTPFetcher;
import vsb.filter.UrlFilter;
import vsb.filter.UrlFilterFactory;
import vsb.model.Seed;
import vsb.priorizer.Priorizer;
import vsb.priorizer.PriorizerFactory;

public class WebCrawler {
	
	private Properties prop;
	private List<Seed> seeds;	
	private Priorizer priorizer;
	
	public WebCrawler(){				
		this.initConfiguration();
		this.initSeeds();
		this.initPriorizer();
	}
	
	private void initConfiguration(){
		try {			
			this.prop = new Properties();
			InputStream input = new FileInputStream(new File("config/config.properties"));
			this.prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	private void initSeeds(){
		try {
			this.seeds = new ArrayList<Seed>();
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(new File("seed/seeds.json")));
			
			JSONArray seeds = (JSONArray) jsonObject.get("seeds");
			Iterator iterator = seeds.iterator();
			while(iterator.hasNext()){
				this.extractSeed((JSONObject) iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}		
	}
	
	private void extractSeed(JSONObject seed){		
		String url = (String) seed.get("url");
		boolean useJavascript = Boolean.parseBoolean((String) seed.get("useJavascript"));
		String urlFilter = (String) seed.get("urlFilter");
		
		JSONArray regexPatterns = (JSONArray) seed.get("regexPatterns");
		if(regexPatterns == null) return;
		Iterator regexIterator = regexPatterns.iterator();
		List<String> regexPatternsList = new ArrayList<String>();
		while(regexIterator.hasNext()){
			regexPatternsList.add((String) regexIterator.next());
		}
				
		this.seeds.add(new Seed(url, regexPatternsList, useJavascript, UrlFilterFactory.build(urlFilter, regexPatternsList)));
	}
	
	private void initPriorizer(){
		this.priorizer = PriorizerFactory.build(this.prop.getProperty("urlPriorizer"));
	}
	
	public void craw(){		
		int numThreads = Integer.parseInt(this.prop.getProperty("numThreads"));
		ExecutorService executor = Executors.newFixedThreadPool(numThreads);
		
		for(Seed seed : this.seeds)
			this.priorizer.push(seed);		
		
		while(!this.priorizer.isEmpty()){			
			Seed seed = this.priorizer.pop();
			Future<List<Seed>> future =  executor.submit(new HTTPFetcher(seed));
			try {
				for(Seed futureSeed : future.get())
					this.priorizer.push(futureSeed);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
	}
}
