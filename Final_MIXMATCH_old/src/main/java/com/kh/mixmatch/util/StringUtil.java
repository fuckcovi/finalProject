package com.kh.mixmatch.util;

public class StringUtil {
	//HTML�� ������� �����鼭 �ٹٲ�
	public static String useBrNoHtml(String str){
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;")
				  .replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}
	//HTML�� ������� ����
	public static String useNoHtml(String str){
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;");
	}
}
