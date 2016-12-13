package com.example.tabFragment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//设置了JsonIgnoreProperties之后就可以忽略除了下列的东西了，服务器运行后，android运行就不会出现还需要其他的东西了
@JsonIgnoreProperties(ignoreUnknown=true)
public class Page<Article> {
	//id
	Integer number;
	List<Article> content;
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public List<Article> getContent() {
		return content;
	}
	public void setContent(List<Article> content) {
		this.content = content;
	}
	
	
	

}
