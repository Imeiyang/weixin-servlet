package com.hzit.utils;

import java.util.Random;

public class RandomUtil {
	
	public static String getRandom(){
		String str = "";
		Random random = new Random();
		int num = random.nextInt(3)+1;
		if(num == 1){
			str = "����";
		}else if(num == 2){
			str = "ʯͷ";
		}else if(num == 3){
			str = "��";
		}
		return str;
	}

}
