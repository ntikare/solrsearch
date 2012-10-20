package com.nawa.mysearch.quest;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nawa.mysearch.beans.Question;
import com.nawa.mysearch.common.QuestionApiConstants;
import com.nawa.mysearch.quest.xjc.ResultSet;
import com.nawa.mysearch.util.HttpUtil;
import com.nawa.mysearch.util.JaxbUtil;

/**
 * Handles connection logic with question API
 * 
 * @author navneet
 * 
 */
@Service
public class QuestionApiClient {
	private static final Logger logger = LoggerFactory.getLogger(QuestionApiClient.class);

	private static String CONDITION_SOLVED = "solved";

	private static int MAX_QUESTIONS = 1000;

	/**
	 * Fetches new question from yahoo API in more than one calls
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<Question> getNewQuestionList() throws ParseException {
		Set<Question> set = new HashSet<>();

		int nRead = 0;
		int cursor = 0;
		final int READ_IN_ONE_REQ = 20;
		while (nRead < MAX_QUESTIONS) {
			int readSize = READ_IN_ONE_REQ;
			if (MAX_QUESTIONS - nRead < READ_IN_ONE_REQ) {
				readSize = MAX_QUESTIONS - nRead;
			}
			ResultSet res = getNewQuestionListFromApi(cursor + 1, readSize);
			cursor = cursor + res.getTotalResultsReturned().intValue();
			set.addAll(readQuestions(res));
			nRead = set.size();
		}
		return new ArrayList<>(set);
	}

	private List<Question> readQuestions(ResultSet res) throws ParseException {
		List<Question> list = new ArrayList<>();

		for (ResultSet.Result result : res.getResult()) {

			Question q = new Question();
			q.setBestAnswer(result.getBestAnswer());
			q.setCategory(result.getCategory());
			q.setCategoryId(result.getCategoryId().longValue());
			q.setContent(result.getContent());
			q.setQuestionId(result.getQuestionId().longValue());
			q.setUpdatedDate(DatatypeConverter.parseDateTime(result.getUpdatedDate()).getTime());
			list.add(q);
		}

		return list;

	}

	/**
	 * Actually connects to question API and parse the XML result
	 * 
	 * @param offset
	 * @param res 
	 * @return {@link ResultSet}
	 */
	private ResultSet getNewQuestionListFromApi(int offset, int res) {

		HttpClient client = new DefaultHttpClient();

		try {
			String apiUrl = QuestionApiConstants.API_URL;
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("appid", QuestionApiConstants.APP_ID));
			params.add(new BasicNameValuePair("condition", CONDITION_SOLVED));
			params.add(new BasicNameValuePair("start", String.valueOf(offset)));
			params.add(new BasicNameValuePair("results", String.valueOf(res)));

			InputStream is = HttpUtil.connectPost(client, apiUrl, params, QuestionApiConstants.PROXY_HOST,
					QuestionApiConstants.PROXY_PORT, QuestionApiConstants.TIME_OUT);
			return (ResultSet) JaxbUtil.readFromXmlStream(ResultSet.class, is);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		} finally {
			client.getConnectionManager().shutdown();
		}

	}

}
