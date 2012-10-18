package com.nawa.mysearch.beans;

import java.io.Serializable;

public class SearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8916278823289449080L;
	private String query;
	private String category;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
