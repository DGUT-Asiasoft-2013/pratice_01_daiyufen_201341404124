package com.example.tabFragment;

import java.util.Date;

public class Article {

	String title; // 文章标题
	String text; // 文章内容

	User author; // 文章作者
	Date createDate; // 文章创建时间
	Date editDate; // 文章编辑时间

	// 获得文章作者
	public String getAuthorName() {
		return author.getName();
	}

	public String getAuthorImage() {
		return author.getAvatar();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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
}
