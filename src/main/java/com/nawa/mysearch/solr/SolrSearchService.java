package com.nawa.mysearch.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * solrj interface for solr search
 * 
 * @author navneet
 * 
 */
@Service
public class SolrSearchService {
	private static final Logger logger = LoggerFactory.getLogger(SolrSearchService.class);

	@Autowired
	private SearchQueryBuilder searchQueryBuilder;

	@Autowired
	private SolrServer solrServer;

	/**
	 * @param query
	 * @return
	 */
	public QueryResponse search(SolrQuery query) {
		try {
			QueryResponse rsp = solrServer.query(query);
			return rsp;
		} catch (SolrServerException e) {
			logger.error("exception in search", e);
			return null;
		}

	}
}
