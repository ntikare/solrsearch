package com.nawa.mysearch;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nawa.mysearch.beans.SearchForm;
import com.nawa.mysearch.common.Constants;
import com.nawa.mysearch.view.ViewData;

/**
 * controller which handles search request
 * 
 * @author navneet
 * 
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	private static String SEARCH_FORM = "search";

	@Autowired
	private SearchService searchService;

	/**
	 * method for get request
	 * 
	 * @param response
	 * @param model
	 * @return search view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String search(HttpServletResponse response, Model model) {

		ViewData res = searchService.search();
		response.setCharacterEncoding(Constants.ENCODING);
		model.addAttribute("categories", res.getCategorys());
		return SEARCH_FORM;
	}

	/**
	 * method for post request
	 * 
	 * @param response
	 * @param searchForm
	 * @param model
	 * @return search view
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String search(HttpServletResponse response, SearchForm searchForm, Model model) {

		logger.info(searchForm.getQuery());
		logger.info(searchForm.getCategory());
		ViewData res = searchService.search(searchForm.getQuery(), searchForm.getCategory());
		response.setCharacterEncoding(Constants.ENCODING);
		if (res == null || res.getDocs() == null || res.getDocs().size() == 0) {
			model.addAttribute("msg", "No result found");
		} else {
			model.addAttribute("categories", res.getCategorys());
			model.addAttribute("docs", res.getDocs());
		}
		return SEARCH_FORM;

	}
}
