package com.jetsoon.bean;

import com.jetsonn.dao.BaseDao;

/**
 * ��ҳ��  �����ҳҳ����Ϣ
 * @author nipengge
 * ����:������
 * ʱ��:2017-2-14 ����05:24:31
 */

public class PagerBean {
	
	private int newPage;//��ǰ�ڼ�ҳ
	
	private int allPage;//һ������ҳ
	
	private int num =20;//ÿҳ��ʾ����
	
	private int count;//һ������ҳ
	
	private int how;//�ӵڼ�����ʼ��ѯ
	
	private int bars;//������������
	
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
