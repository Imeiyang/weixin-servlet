package weixin_servlet;

import org.junit.Test;

import com.hzit.servlet.WeiXinServlet;

public class ServletTest {
	
	WeiXinServlet s = new WeiXinServlet();
	
	@Test
	public void mytest(){
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[EVENTKEY]]></EventKey></xml>";
		System.out.println(s.transMessage(xml));
	}

}
