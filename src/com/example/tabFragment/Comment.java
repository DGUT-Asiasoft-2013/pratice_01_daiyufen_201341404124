package com.example.tabFragment;

import java.util.Date;

public class Comment {

	String content;       //��������
	User commentor;        //������
	Date createDate;      //���۴���ʱ��
	Date editDate;      //���۱༭ʱ��
	Article article;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getCommentor() {
		return commentor;
	}
	public void setCommentor(User commentor) {
		this.commentor = commentor;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
}
