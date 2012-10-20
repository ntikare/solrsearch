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
	private String contentText;
	@Field
	private Long categoryId;
	@Field
	private String category;
	@Field
	private String bestAnswer;
	@Field
	private String bestAnswerText;
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

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getBestAnswerText() {
		return bestAnswerText;
	}

	public void setBestAnswerText(String bestAnswerText) {
		this.bestAnswerText = bestAnswerText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", content=" + content + ", contentText=" + contentText
				+ ", categoryId=" + categoryId + ", category=" + category + ", bestAnswer=" + bestAnswer
				+ ", bestAnswerText=" + bestAnswerText + ", updatedDate=" + updatedDate + "]";
	}

}
