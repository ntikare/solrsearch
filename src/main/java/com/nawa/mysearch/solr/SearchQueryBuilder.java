package com.nawa.mysearch.solr;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Handles logic for building solr query
 * 
 * @author navneet
 * 
 */
@Service
public class SearchQueryBuilder {

	private static final Logger logger = LoggerFactory.getLogger(SearchQueryBuilder.class);

	/**
	 * builds search query with and search of keyword and category .
	 * Also add facet request on category
	 * 
	 * @param keyword
	 * @param category
	 * @return
	 */
	public SolrQuery build(String keyword, String category) {
		StringBuilder qry = new StringBuilder();
		SolrQuery query = new SolrQuery();
		if (StringUtils.isNotBlank(keyword)) {
			qry.append("(");
			qry.append("content:").append(keyword);
			qry.append(" OR ");
			qry.append("bestAnswer:").append(keyword);
			qry.append(")");
		}

		if (StringUtils.isNotBlank(keyword) && StringUtils.isNotBlank(category)) {
			qry.append(" AND ");
		}
		if (StringUtils.isNotBlank(category)) {
			qry.append("category:").append(category);
		}
		if (StringUtils.isBlank(keyword) && StringUtils.isBlank(category)) {
			qry.append("*:*");
			query.setRows(0);
		}
		query.setQuery(qry.toString()).setFacet(true).addFacetField("category").setFacetMinCount(1);

		logger.debug(query.toString());

		return query;
	}
}
