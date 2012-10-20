package com.nawa.mysearch.quest;

import java.text.ParseException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.nawa.mysearch.beans.Question;

public class QuestionApiClientTest {

	@Test
	public void test() throws ParseException {

		QuestionApiClient apiClient = new QuestionApiClient();
		List<Question> res = apiClient.getNewQuestionList();
		Assert.assertEquals(1000, res.size());

	}

}
