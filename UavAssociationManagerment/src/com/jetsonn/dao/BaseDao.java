package com.jetsonn.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * ���ݲ�ѯ��
 * @author nipengge
 * ����:������
 * ʱ��:2017-1-22 ����05:56:59
 */
public class BaseDao extends SqlSessionDaoSupport{
	
	/**
	 * ��ѯ����+ģ����ѯ
	 * @param sql
	 * @param obj
	 * @return
	 */
	public List qureyAll(String sql,Object obj){
		return getSqlSession().selectList(sql, obj);
	}
	
	
	/**
	 * ��¼��֤�˺�����
	 * @param sql
	 * @param obj
	 * @return
	 */
	public Object LoginCheck(String sql,Object obj){
		return getSqlSession().selectOne(sql, obj);
	}
	/**
	 * ͳ������
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int getCount(String sql,Object obj){
		return getSqlSession().selectOne(sql, obj);
	}
	
	/**
	 * �޸�
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int update(String sql,Object obj){
		return getSqlSession().update(sql, obj);
	}
	
	
	/**
	 * ���
	 * @param sql
	 * @param obj
	 * @return
	 */
	
	public int insert(String sql,Object obj){
		return getSqlSession().insert(sql, obj);
	}
	
	/**
	 * ɾ��
	 * @param sql
	 * @param obj
	 * @return
	 */
	
	public int delete(String sql,Object obj){
		return getSqlSession().delete(sql, obj);
	}

}
