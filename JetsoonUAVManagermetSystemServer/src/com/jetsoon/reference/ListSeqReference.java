package com.jetsoon.reference;

import java.util.HashMap;


/**
 * ����ÿ���ɿؿͻ��˶�������Ϣ����
 * @author nipengge
 * ����:������
 * ʱ��:2017-3-27 ����11:59:45
 */
public class ListSeqReference {

	private static HashMap<String, Integer> seqMap = new HashMap<String, Integer>();
	
	public static  void put(String key,int value){
		
		seqMap.put(key, value);
	}
	
	public static int get(String key){
	
		int value = seqMap.get(key);
		
		return value;
	}
	
	public static void rmove(String key){
		
		seqMap.remove(key);
	}
	
	public static boolean  containsKey(String key){
		
		return seqMap.containsKey(key);
	}
}
