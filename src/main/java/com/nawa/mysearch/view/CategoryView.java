package com.nawa.mysearch.view;

import java.io.Serializable;

public class CategoryView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8085262858584765360L;
	private String name;
	private Long hits;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

}
