package com.jetsoon.reference;

import java.util.HashMap;


/**
 * 储存每个飞控客户端独立的消息序列
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-3-27 上午11:59:45
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
