package com.jetsoon.util;


import java.util.HashMap;
import java.util.Set;


/**
 * 自定义Map集合
 * 
 * 扩展根据value获取key
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-26 下午08:11:25
 */
public class HashBiMap<K, V> {

    private final HashMap<K, V> mKeyToValueMap = new HashMap<K, V>();
    private final HashMap<V, K> mValueToKeyMap = new HashMap<V, K>();

    public void put(K key, V value) {
        mKeyToValueMap.put(key, value);
        mValueToKeyMap.put(value, key);
    }

    public void removeKey(K key) {
        final V value = mKeyToValueMap.get(key);
        if (value != null) {
            mKeyToValueMap.remove(key);
            mValueToKeyMap.remove(value);
        }
    }

    public void removeValue(V value) {
        final K key = mValueToKeyMap.get(value);
        if (key != null) {
            mValueToKeyMap.remove(value);
            mKeyToValueMap.remove(key);
        }
    }

    public V getValue(K key) {
        return mKeyToValueMap.get(key);
    }

    public K getKey(V value) {
        return mValueToKeyMap.get(value);
    }

    public void clear() {
        mKeyToValueMap.clear();
        mValueToKeyMap.clear();
    }

    public Set<K> keySet() {
        return mKeyToValueMap.keySet();
    }

    public Set<V> valueSet() {
        return mValueToKeyMap.keySet();
    }
    
    
	public boolean containsValue(V value) {
		
	    boolean valueContains =	mKeyToValueMap.containsValue(value);
	    
	    boolean keyContains = mValueToKeyMap.containsKey(value);
	    
	    if(valueContains == false && keyContains == false){
	    	return false;
	    }
	    
	    if(valueContains == true && keyContains == true){
	    	return true;
	    }
		
		return false ;
	}
	

	public boolean containsKey(K key) {
		
		 boolean valueContains =	mKeyToValueMap.containsKey(key);
		    
		 boolean keyContains = mValueToKeyMap.containsValue(key);
		
		 if(valueContains == false && keyContains == false){
		    return false;
		  }
		    
		 if(valueContains == true && keyContains == true){
		    return true;
		 }
		 
		return false;
	}
}
