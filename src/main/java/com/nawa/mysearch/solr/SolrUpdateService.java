package com.nawa.mysearch.solr;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nawa.mysearch.beans.Question;

@Service
public class SolrUpdateService {

	private static final Logger logger = LoggerFactory.getLogger(SolrUpdateService.class);

	@Autowired
	private SolrServer solrServer;

	public boolean addQuestions(List<Question> list) {

		try {
			solrServer.addBeans(list);
			solrServer.commit();
			return true;
		} catch (SolrServerException | IOException e) {
			logger.error("exception in search", e);
			try {
				solrServer.rollback();
			} catch (Exception e1) {

			}
			return false;
		}

	}

	public boolean addQuestion(Question quest) throws SolrServerException, IOException {

		try {
			solrServer.addBean(quest);
			solrServer.commit();
			return true;
		} catch (SolrServerException | IOException e) {
			logger.error("exception in search", e);
			try {
				solrServer.rollback();
			} catch (Exception e1) {

			}
			return false;
		}


	}

}
