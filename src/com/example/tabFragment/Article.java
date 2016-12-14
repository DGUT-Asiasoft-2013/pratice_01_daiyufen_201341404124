package com.example.tabFragment;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{
	/*
	 * Article里下面的东西一定要与网页上的标签一样
	 */

	Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	String title; // 文章标题
	String text; // 文章内容

	User author; // 文章作者
	Date createDate; // 文章创建时间
	Date editDate; // 文章编辑时间
	String authorImage;           //作者头像
	/*public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}*/
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
	public String getAuthorImage() {
		return authorImage;
	}
	public void setAuthorImage(String authorImage) {
		this.authorImage = authorImage;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	String authorName;        //作者姓名
	

	
}
