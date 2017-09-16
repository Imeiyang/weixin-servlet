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
	 * ��ͨ�ı�-->��ͨ�ı�
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
	 * ����˵��¼�-->��ͨ�ı�
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
				str = "����";
			}else if(random == 2){
				str = "ʯͷ";
			}else if(random == 3){
				str = "��";
			}
			System.out.println("�����Ϊ��"+random);
			int sub = random-eventkey;
			if(sub == 0){
				replaym.setContent("ϵͳ�����ǣ�"+str+",ƽ��������սһ�Σ�");
			}else if(sub == 1 || sub == -2){
				replaym.setContent("ϵͳ�����ǣ�"+str+",Sorry~ϵͳӮ����������ս��");
			}else if(sub == -1 || sub == 2){
				replaym.setContent("ϵͳ�����ǣ�"+str+",��ϲ��Ӯ��������һ�ְɣ�");
			}
		}
		XStream xStream  = new XStream(new DomDriver());
		
		xStream.alias("xml", TextNormalMessage.class);
		
		return xStream.toXML(replaym);
	}
	
	

}
