package com.nawa.mysearch.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http utility class
 * 
 * @author navneet
 * 
 */
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * makes a http post request
	 * 
	 * @param client
	 * @param url
	 * @param nameValuePairs
	 * @param proxyHost
	 * @param proxyPort
	 * @param timeOut
	 * @return {@link InputStream}
	 * @throws Exception
	 */
	public static InputStream connectPost(HttpClient client, String url, List<NameValuePair> nameValuePairs,
			String proxyHost, int proxyPort, int timeOut) throws Exception {

		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		return connect(client, post, proxyHost, proxyPort, timeOut);

	}

	private static InputStream connect(HttpClient client, HttpUriRequest request, String proxyHost, int proxyPort,
			int timeOut) throws Exception {

		try {

			logger.debug(request.toString());

			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, timeOut);
			HttpConnectionParams.setSoTimeout(params, timeOut);
			if (StringUtils.isNotEmpty(proxyHost)) {
				HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");
				client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			}
			HttpResponse response = client.execute(request);
			logger.debug(response.toString());
			if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
				throw new Exception("Invalid http status code");
			}
			return response.getEntity().getContent();
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage(), e);
			throw e;
		}

	}

}
