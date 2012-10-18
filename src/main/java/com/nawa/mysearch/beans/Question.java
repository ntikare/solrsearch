package com.nawa.mysearch.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305345616847342546L;

	@Field
	private Long questionId;
	@Field
	private String content;
	@Field
	private Long categoryId;
	@Field
	private String category;
	@Field
	private String bestAnswer;
	@Field
	private Date updatedDate;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBestAnswer() {
		return bestAnswer;
	}

	public void setBestAnswer(String bestAnswer) {
		this.bestAnswer = bestAnswer;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", content=" + content + ", categoryId=" + categoryId
				+ ", category=" + category + ", bestAnswer=" + bestAnswer + ", updatedDate=" + updatedDate + "]";
	}

}
