package com.hzit.service;

import java.util.Date;
import java.util.Random;

import com.hzit.entity.ClickEventMessage;
import com.hzit.entity.Message;
import com.hzit.entity.TextNormalMessage;
import com.hzit.utils.RandomUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MessageService {
	
	/**
	 * 普通文本-->普通文本
	 * @param msg
	 * @param replayStr
	 * @return
	 */
	public String replayTextMessage(Message msg,String replayStr) {
		// TODO Auto-generated method stub
		XStream xStream  = new XStream(new DomDriver());
		TextNormalMessage m = new TextNormalMessage();
		m.setFromUserName(msg.getToUserName());
		m.setToUserName(msg.getFromUserName());
		m.setMsgType("text");
		m.setContent(replayStr);
		m.setCreateTime(new Date().getTime());
		xStream.alias("xml", TextNormalMessage.class);
		
		return xStream.toXML(m);
	}
	
	/**
	 * 点击菜单事件-->普通文本
	 * @param msg
	 * @param replayStr
	 * @return
	 */
	public String replayClickEventMessage(Message msg,String eventKey) {
		// TODO Auto-generated method stub
		TextNormalMessage replaym = new TextNormalMessage();
		replaym.setFromUserName(msg.getToUserName());
		replaym.setToUserName(msg.getFromUserName());
		replaym.setMsgType("text");
		replaym.setCreateTime(new Date().getTime());
		
		ClickEventMessage clickmsg = (ClickEventMessage)msg;
		if("CLICK".equalsIgnoreCase(clickmsg.getEvent())){
			int eventkey = Integer.parseInt(eventKey);
			Random r = new Random();
			int random = r.nextInt(3)+1;
			String str = "";
			if(random == 1){
				str = "剪刀";
			}else if(random == 2){
				str = "石头";
			}else if(random == 3){
				str = "布";
			}
			System.out.println("随机数为："+random);
			int sub = random-eventkey;
			if(sub == 0){
				replaym.setContent("系统出的是："+str+",平局啦，再战一次！");
			}else if(sub == 1 || sub == -2){
				replaym.setContent("系统出的是："+str+",Sorry~系统赢啦，不服再战！");
			}else if(sub == -1 || sub == 2){
				replaym.setContent("系统出的是："+str+",恭喜你赢啦，再来一局吧！");
			}
		}
		XStream xStream  = new XStream(new DomDriver());
		
		xStream.alias("xml", TextNormalMessage.class);
		
		return xStream.toXML(replaym);
	}
	
	

}
