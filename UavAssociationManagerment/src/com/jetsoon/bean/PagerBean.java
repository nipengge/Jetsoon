package com.jetsoon.bean;

import com.jetsonn.dao.BaseDao;

/**
 * 分页类  储存分页页数信息
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-14 下午05:24:31
 */

public class PagerBean {
	
	private int newPage;//当前第几页
	
	private int allPage;//一共多少页
	
	private int num =20;//每页显示几条
	
	private int count;//一共多少页
	
	private int how;//从第几条开始查询
	
	private int bars;//到多少条结束
	
	private String sql;
	
	private Object obj;
	
	private BaseDao baseDao;
	
	public PagerBean(){}
	
	public PagerBean(int num,BaseDao baseDao){
		this.num = num;
		this.baseDao = baseDao;
	}

	public int getNewPage() {
		return newPage;
	}

	public void setNewPage(int newPage) {
		
		this.newPage = newPage;
	}

	public int getAllPage() {
		int count = getCount();
		int num = getNum();
		this.allPage = count%num==0?count/num:(count/num)+1;
		return allPage;
	}

	public int getNum() {
		return num;
	}

	public int getCount() {
		this.count = baseDao.getCount(sql, obj);
		return count;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
		int allPage = getAllPage();
		
		if(newPage<1){
			newPage = 1;
		}else if(allPage<newPage){
			newPage = allPage;
		}
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public String getSql() {
		return sql;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getHow() {
		how = (newPage-1)*num;
		if(how<0){
			how=0;
		}
		return how; 
	}

	public void setHow(int how) {
		this.how = how;
	}

	public int getBars() {
		return bars = num;
	}

	public void setBars(int bars) {
		this.bars = bars;
	}
	
	
	
}
