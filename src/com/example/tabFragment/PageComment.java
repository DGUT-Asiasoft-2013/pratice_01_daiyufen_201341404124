package com.example.tabFragment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//������JsonIgnoreProperties֮��Ϳ��Ժ��Գ������еĶ����ˣ����������к�android���оͲ�����ֻ���Ҫ�����Ķ�����
@JsonIgnoreProperties(ignoreUnknown=true)
public class PageComment<Comment> {

	    //id
		Integer number;
		List<Comment> content;
		public Integer getNumber() {
			return number;
		}
		public void setNumber(Integer number) {
			this.number = number;
		}
		public List<Comment> getContent() {
			return content;
		}
		public void setContent(List<Comment> content) {
			this.content = content;
		}
}
