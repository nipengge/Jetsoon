package com.jetsonn.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 数据查询类
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-1-22 下午05:56:59
 */
public class BaseDao extends SqlSessionDaoSupport{
	
	/**
	 * 查询所有+模糊查询
	 * @param sql
	 * @param obj
	 * @return
	 */
	public List qureyAll(String sql,Object obj){
		return getSqlSession().selectList(sql, obj);
	}
	
	
	/**
	 * 登录验证账号密码
	 * @param sql
	 * @param obj
	 * @return
	 */
	public Object LoginCheck(String sql,Object obj){
		return getSqlSession().selectOne(sql, obj);
	}
	/**
	 * 统计数据
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int getCount(String sql,Object obj){
		return getSqlSession().selectOne(sql, obj);
	}
	
	/**
	 * 修改
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int update(String sql,Object obj){
		return getSqlSession().update(sql, obj);
	}
	
	
	/**
	 * 添加
	 * @param sql
	 * @param obj
	 * @return
	 */
	
	public int insert(String sql,Object obj){
		return getSqlSession().insert(sql, obj);
	}
	
	/**
	 * 删除
	 * @param sql
	 * @param obj
	 * @return
	 */
	
	public int delete(String sql,Object obj){
		return getSqlSession().delete(sql, obj);
	}

}
