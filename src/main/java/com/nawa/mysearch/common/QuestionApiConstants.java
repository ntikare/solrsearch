package com.nawa.mysearch.common;

import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public class QuestionApiConstants {

	public static final String API_URL = ResourceBundle.getBundle(Constants.QUEST_API_PROP_FILE).getString(
			"quest.api.url");

	public static final String APP_ID = ResourceBundle.getBundle(Constants.QUEST_API_PROP_FILE).getString(
			"quest.api.appid");

	public static final int TIME_OUT;

	public static final String PROXY_HOST = ResourceBundle.getBundle(Constants.QUEST_API_PROP_FILE).getString(
			"quest.api.proxy.host");

	public static final int PROXY_PORT;

	static {
		int timeOut = 7000;
		String temp = ResourceBundle.getBundle(Constants.QUEST_API_PROP_FILE).getString("quest.api.timeout");
		if (StringUtils.isNotEmpty(temp)) {
			timeOut = Integer.valueOf(temp);
		}
		TIME_OUT = timeOut;

		int port = 80;
		temp = ResourceBundle.getBundle(Constants.QUEST_API_PROP_FILE).getString("quest.api.proxy.port");

		if (StringUtils.isNotEmpty(temp)) {
			port = Integer.valueOf(temp);
		}
		PROXY_PORT = port;
	}
}
