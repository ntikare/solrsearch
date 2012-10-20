package com.nawa.mysearch.common;

import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public class SolrConstants {

	public static final String SOLR_SERVER_URL = ResourceBundle.getBundle(Constants.SOLR_PROP_FILE).getString(
			"solr.server.url");

	public static final int TIME_OUT;

	public static final String PROXY_HOST = ResourceBundle.getBundle(Constants.SOLR_PROP_FILE).getString(
			"solr.server.proxy.host");

	public static final int PROXY_PORT;

	static {
		int timeOut = 7000;
		String temp = ResourceBundle.getBundle(Constants.SOLR_PROP_FILE).getString("solr.server.timeout");
		if (StringUtils.isNotEmpty(temp)) {
			timeOut = Integer.valueOf(temp);
		}
		TIME_OUT = timeOut;

		int port = 80;
		temp = ResourceBundle.getBundle(Constants.SOLR_PROP_FILE).getString("solr.server.proxy.port");

		if (StringUtils.isNotEmpty(temp)) {
			port = Integer.valueOf(temp);
		}
		PROXY_PORT = port;
	}

}
