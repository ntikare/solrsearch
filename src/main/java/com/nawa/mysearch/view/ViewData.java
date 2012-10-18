package com.nawa.mysearch.view;

import java.io.Serializable;
import java.util.List;

public class ViewData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4165809277067175720L;

	private List<CategoryView> categorys;
	private List<DocView> docs;

	public List<CategoryView> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<CategoryView> categorys) {
		this.categorys = categorys;
	}

	public List<DocView> getDocs() {
		return docs;
	}

	public void setDocs(List<DocView> docs) {
		this.docs = docs;
	}

}
