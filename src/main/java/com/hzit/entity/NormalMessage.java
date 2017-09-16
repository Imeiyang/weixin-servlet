package com.hzit.entity;

public class NormalMessage extends Message{
	private String MsgId;

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	@Override
	public String toString() {
		return "NormalMessage [MsgId=" + MsgId + "]";
	}

	public NormalMessage(String msgId) {
		super();
		MsgId = msgId;
	}

	public NormalMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
