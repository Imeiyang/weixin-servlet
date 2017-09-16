package com.hzit.entity;

public class ClickEventMessage extends EventMessage{

	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public ClickEventMessage(String eventKey) {
		super();
		EventKey = eventKey;
	}

	public ClickEventMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ClickEventMessage [EventKey=" + EventKey + "]";
	}
	
}
