package vbs.main;

import vbs.filter.UrlFilterFactory;
import vbs.priorizer.PriorizerFactory;
import vsb.fetcher.HTTPFetcher;

public class Main {
	
	public static void main(String[] args) {
		HTTPFetcher fetcher = new HTTPFetcher("http://noticias.gov.br/noticias/", UrlFilterFactory.build("SetUrlFilter"), PriorizerFactory.build("BreadthFirstPriorizer"));
		fetcher.download();
	}
}
