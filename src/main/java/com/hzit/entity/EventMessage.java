package com.hzit.entity;

public class EventMessage extends Message{
	
	private String Event;

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public EventMessage(String event) {
		super();
		Event = event;
	}

	public EventMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EventMessage [Event=" + Event + "]";
	}
	
	

}
