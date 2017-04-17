package vsb.main;

import vsb.fetcher.HTTPFetcher;
import vsb.filter.UrlFilterFactory;
import vsb.priorizer.PriorizerFactory;

public class Main {
	
	public static void main(String[] args) {
		HTTPFetcher fetcher = new HTTPFetcher("http://noticias.gov.br/noticias/", UrlFilterFactory.build("SetUrlFilter"), PriorizerFactory.build("BreadthFirstPriorizer"));
		fetcher.download();
	}
}
