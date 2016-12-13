package com.example.tabFragment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//������JsonIgnoreProperties֮��Ϳ��Ժ��Գ������еĶ����ˣ����������к�android���оͲ�����ֻ���Ҫ�����Ķ�����
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
