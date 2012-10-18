package com.nawa.mysearch.solr;

import java.net.MalformedURLException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfiguration {

	@Bean
	public SolrServer solrServer() throws MalformedURLException {

		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8983/solr/");
		return solrServer;
	}

}
