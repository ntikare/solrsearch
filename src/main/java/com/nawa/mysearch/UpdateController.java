package com.nawa.mysearch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nawa.mysearch.beans.Question;
import com.nawa.mysearch.quest.QuestionApiClient;
import com.nawa.mysearch.solr.SolrUpdateService;

/**
 * Controller which handles update request
 * 
 * @author navneet
 * 
 */
@Controller
@RequestMapping(value = "/update")
public class UpdateController {

	private static final Logger logger = LoggerFactory.getLogger(UpdateController.class);

	private static String RES = "updateRes";

	@Autowired
	private SolrUpdateService solrUpdateService;

	@Autowired
	private QuestionApiClient questionApiClient;

	/**
	 * updates solr index
	 * 
	 * @param model
	 * @return update res view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String update(Model model) {
		try {
			List<Question> questList = questionApiClient.getNewQuestionList();
			boolean sucess = solrUpdateService.addQuestions(questList);
			if (sucess) {
				model.addAttribute("msg", "Updated solr index.");
			} else {
				model.addAttribute("msg", "Error occured in updating solr index.");
			}
		} catch (Exception e) {
			logger.error("Error occured in updating solr index", e);
			model.addAttribute("msg", "Unexpected error occured in updating solr index.");
		}
		return RES;
	}
}
