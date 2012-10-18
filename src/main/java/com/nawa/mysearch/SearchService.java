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

@Service
public class SearchService {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SolrSearchService searchService;

	@Autowired
	private SearchQueryBuilder searchQueryBuilder;

	@Autowired
	private SearchViewCreator searchViewCreator;

	public ViewData search() {
		return search(null, null);
	}

	public ViewData search(String keyword) {
		return search(keyword, null);
	}

	public ViewData search(String keyword, String category) {

		QueryResponse res = searchService.search(searchQueryBuilder.build(keyword, category));
		logger.debug("search response="+res);
		List<Question> questions = null;
		FacetField field = null;
		if (res != null) {
			questions = res.getBeans(Question.class);
			logger.debug("questions="+questions);
			field = res.getFacetField("category");
			logger.debug(field.toString());
		}
		return searchViewCreator.create(questions, field);
	}

}
