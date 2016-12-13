package com.example.tabFragment;

import java.util.Date;

public class Article {

	String title; // ���±���
	String text; // ��������

	User author; // ��������
	Date createDate; // ���´���ʱ��
	Date editDate; // ���±༭ʱ��

	// �����������
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
