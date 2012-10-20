package com.nawa.mysearch.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.springframework.stereotype.Service;

import com.nawa.mysearch.beans.Question;
import com.nawa.mysearch.view.CategoryView;
import com.nawa.mysearch.view.DocView;
import com.nawa.mysearch.view.ViewData;

/**
 * Handles view data creator logic
 * 
 * @author navneet
 * 
 */
@Service
public class SearchViewCreator {

	private static final int SUMMARY_SIZE = 30;

	/** creates {@link ViewData} from questions and facet info
	 * @param questions
	 * @param field
	 * @return {@link ViewData} object
	 */
	public ViewData create(List<Question> questions, FacetField field) {

		ViewData view = new ViewData();
		List<DocView> docs = new ArrayList<>();
		if (questions != null) {
			for (Question question : questions) {
				docs.add(createDocView(question));
			}
		}
		view.setDocs(docs);

		List<CategoryView> categorys = new ArrayList<>();
		if (field != null && field.getValues() != null) {
			for (Count count : field.getValues()) {
				CategoryView categoryView = new CategoryView();
				categoryView.setName(count.getName());
				categoryView.setHits(count.getCount());
				categorys.add(categoryView);
			}
		}

		view.setCategorys(categorys);
		return view;

	}

	private DocView createDocView(Question question) {

		DocView docView = new DocView();
		docView.setBestAnswer(question.getBestAnswer());
		docView.setContent(question.getContent());
		docView.setCategory(question.getCategory());
		docView.setUpdatedDate(question.getUpdatedDate().toString());
		docView.setSummary(StringUtils.substring(question.getContent(), 0, SUMMARY_SIZE));
		return docView;
	}
}
