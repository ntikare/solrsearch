package com.nawa.mysearch.solr;

import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.junit.Test;

import com.nawa.mysearch.beans.Question;

public class SolrUpdateServiceTest {

	@Test
	public void test() throws SolrServerException, IOException {

		SolrUpdateService service = new SolrUpdateService();
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8983/solr/");
		service.setSolrServer(solrServer);
		Question quest = new Question();
		quest.setBestAnswer("are you");
		quest.setCategory("gogo");
		quest.setCategoryId(246L);
		quest.setContent("<b>who is</b>");
		quest.setQuestionId(1111111L);
		quest.setUpdatedDate(DatatypeConverter.parseDateTime("2002-10-10T09:00:00.0Z").getTime());
		service.addQuestion(quest);

		Question quest1 = new Question();
		quest1.setBestAnswer("<div>売上情報</div>");
		quest1.setCategory("japan");
		quest1.setCategoryId(244L);
		quest1.setContent("<strong>楽天カード株式会社</strong>");
		quest1.setQuestionId(1111211L);
		quest1.setUpdatedDate(DatatypeConverter.parseDateTime("2002-10-10T09:00:00.0Z").getTime());
		service.addQuestion(quest1);

		Question quest2 = new Question();
		quest2.setBestAnswer("<div>詳細は、お客さまの口座にログインしてご確認ください</div>");
		quest2.setCategory("japan2");
		quest2.setCategoryId(245L);
		quest2.setContent("<a>このメールは送信専用です</a>");
		quest2.setQuestionId(1121211L);
		quest2.setUpdatedDate(DatatypeConverter.parseDateTime("2002-10-10T09:00:00.0Z").getTime());
		service.addQuestion(quest2);
	}

}
