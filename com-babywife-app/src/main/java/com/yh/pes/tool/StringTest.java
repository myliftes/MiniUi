package com.yh.pes.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

	public static void main(String[] args) {

		String str = "ASSET_TYPE_CODE";
        str = str.toLowerCase();
        final StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("_(\\w)");
        Matcher m = p.matcher(str);
        while (m.find()){
            m.appendReplacement(sb,m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
		
		
	}

	public void  underscoreName(String name) {
		
		 StringBuilder[] result = {};
		   for (int i = 0; i < result.length; i++) {
			
		}
	}
}
