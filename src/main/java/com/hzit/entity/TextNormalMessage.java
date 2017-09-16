package com.hzit.entity;

public class TextNormalMessage extends NormalMessage{

	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String toString() {
		return "TextNormalMessage [Content=" + Content + "]";
	}

	public TextNormalMessage(String msgId, String content) {
		super(msgId);
		Content = content;
	}

	public TextNormalMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TextNormalMessage(String msgId) {
		super(msgId);
		// TODO Auto-generated constructor stub
	}
	
	
}
