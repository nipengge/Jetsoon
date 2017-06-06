package com.jetsoon.reference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.MAVLink.common.msg_mission_item;

/**
 * �洢  ���˻��������
 * @author nipengge
 *
 */
public class ListDataReference {
	
	private static Map<String,List<msg_mission_item>>  sessioonMap = new HashMap<String,List<msg_mission_item>>();
	
	/**
	 * ���� ��������
	 * @param key
	 * @param list
	 */
	public static void put(String key,List<msg_mission_item> list){
		
		sessioonMap.put(key, list);
	}
	
	/**
	 * ȡ�������
	 * @param key
	 * @param index
	 * @return
	 */
    public static msg_mission_item getMission(String key,int index){
    	
    	List<msg_mission_item> listValue =	sessioonMap.get(key);
      
        msg_mission_item missionItem = listValue.get(index);
    	
        return missionItem;
    }
    
    /**
     * ɾ����������
     */
    public static void remove(String key){
    	sessioonMap.remove(key);
    }
    
    /**
     * ��պ���
     */
    public static void clear(){
    	sessioonMap.clear();
    }

}
