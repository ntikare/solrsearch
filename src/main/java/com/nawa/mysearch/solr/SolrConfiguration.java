package com.nawa.mysearch.solr;

import java.net.MalformedURLException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nawa.mysearch.common.SolrConstants;

/**
 * Spring bean configuration class
 * 
 * @author navneet
 * 
 */
@Configuration
public class SolrConfiguration {

	/**
	 * @return bean instance of {@link SolrServer}
	 * @throws MalformedURLException
	 */
	@Bean
	public SolrServer solrServer() {

		HttpSolrServer solrServer = new HttpSolrServer(SolrConstants.SOLR_SERVER_URL);
		solrServer.setConnectionTimeout(SolrConstants.TIME_OUT);
		solrServer.setSoTimeout(SolrConstants.TIME_OUT);
		if (StringUtils.isNotEmpty(SolrConstants.PROXY_HOST)) {
			HttpHost proxy = new HttpHost(SolrConstants.PROXY_HOST, SolrConstants.PROXY_PORT, "http");
			solrServer.getHttpClient().getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		solrServer.getHttpClient();
		return solrServer;
	}

}
