package com.nawa.mysearch;

import java.util.List;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nawa.mysearch.beans.Question;
import com.nawa.mysearch.logic.SearchViewCreator;
import com.nawa.mysearch.solr.SearchQueryBuilder;
import com.nawa.mysearch.solr.SolrSearchService;
import com.nawa.mysearch.view.ViewData;

/**
 * Handles search service
 * 
 * @author navneet
 * 
 */
@Service
public class SearchService {

	private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

	@Autowired
	private SolrSearchService searchService;

	@Autowired
	private SearchQueryBuilder searchQueryBuilder;

	@Autowired
	private SearchViewCreator searchViewCreator;

	/**
	 * default search
	 * 
	 * @return {@link ViewData} containing all categories list
	 */
	public ViewData search() {
		return search(null, null);
	}

	/**
	 * search with keyword and category not specified
	 * 
	 * @param keyword
	 * @return {@link ViewData}
	 */
	public ViewData search(String keyword) {
		return search(keyword, null);
	}

	/**
	 * And search with keyword and category
	 * 
	 * @param keyword
	 * @param category
	 * @return {@link ViewData}
	 */
	public ViewData search(String keyword, String category) {

		QueryResponse res;
		try {
			res = searchService.search(searchQueryBuilder.build(keyword, category));
			List<Question> questions = null;
			FacetField field = null;
			if (res != null) {
				questions = res.getBeans(Question.class);
				field = res.getFacetField("category");
			}
			return searchViewCreator.create(questions, field);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

}
