package com.nawa.mysearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nawa.mysearch.beans.SearchForm;
import com.nawa.mysearch.view.ViewData;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	private static String SUCCESS_RES = "search";

	@Autowired
	private SearchService searchService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String search(Model model) {

		ViewData res = searchService.search();
		model.addAttribute("categories", res.getCategorys());
		return SUCCESS_RES;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String search(SearchForm searchForm, Model model) {

		logger.info(searchForm.getQuery());
		logger.info(searchForm.getCategory());

		ViewData res = searchService.search(searchForm.getQuery(), searchForm.getCategory());
		
		model.addAttribute("categories", res.getCategorys());
		model.addAttribute("docs", res.getDocs());
		return SUCCESS_RES;
	}
}
