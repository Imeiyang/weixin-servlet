package com.hzit.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hzit.entity.ClickEventMessage;
import com.hzit.entity.Message;
import com.hzit.entity.TextNormalMessage;
import com.hzit.service.MessageService;
import com.hzit.utils.Sha1Util;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Servlet implementation class WeiXinServlet
 */
@WebServlet("/wx")
public class WeiXinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String token="meiyang";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeiXinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		System.out.println("signature==>"+signature);
		System.out.println("timestamp==>"+timestamp);
		System.out.println("nonce==>"+nonce);
		System.out.println("echostr==>"+echostr);
		
		String[] info = {token,timestamp,nonce};
		Arrays.sort(info);
		String str1 = info[0]+info[1]+info[2];
		//shar1加密
		String str2 = Sha1Util.getSha1(str1);
		if(str2.equals(signature)){
			System.out.println("接入成功");
			out.print(echostr);//原样返回echostr参数内容
		}else{
			System.out.println("接入失败");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(new Date()+"post request...");
		InputStream in = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(in,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuffer xml = new StringBuffer();
		 String temp = br.readLine();
		 System.out.println("===用户消息===");
		while(temp != null){
			xml = xml.append(temp);
			temp = br.readLine();
		}
		
		System.out.println(xml);
		Message msg = transMessage(xml.toString());
		System.out.println(msg);
//		ClickEventMessage c = (ClickEventMessage)msg;
//		System.out.println("这是key:"+c.getEventKey());
		//客服回复的消息xml数据
		MessageService messageService = new MessageService();
//		String replayXml1 = messageService.replayTextMessage(msg, "客服自动回复你消息了！");
		
		try {
			Document document = DocumentHelper.parseText(xml.toString());
			Element root = document.getRootElement();
			String eventKey = root.elementText("EventKey");
			System.out.println("eventKey:"+eventKey);
			String replayXml2 = messageService.replayClickEventMessage(msg, eventKey);
			out.print(replayXml2);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		out.print(replayXml1);
		
		out.close();
		br.close();
		isr.close();
		in.close();
	}
	
	/**
	 * 将xml数据转换成对应的实体类
	 * @param xml
	 * @return
	 */
	public Message transMessage(String xml) {
		// TODO Auto-generated method stub
		XStream xStream = new XStream(new DomDriver());
		Message message = null;
		try {
			//为了获取MsgType,然后按实体类分类处理xml数据
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			String type = root.elementText("MsgType");
			//是否为普通文本消息
			if(type.equalsIgnoreCase("text")){
				xStream.alias("xml", TextNormalMessage.class);
				message = (TextNormalMessage) xStream.fromXML(xml);
				//是否为事件消息
			}else if(type.equalsIgnoreCase("event")){
				String event = root.elementText("Event");
				//是否为点击事件消息
				if(event.equalsIgnoreCase("CLICK")){
					xStream.alias("xml", ClickEventMessage.class);
					message = (ClickEventMessage) xStream.fromXML(xml);
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;

	}

}
