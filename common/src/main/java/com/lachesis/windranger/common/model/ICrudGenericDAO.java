package com.lachesis.windranger.common.model;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.io.Serializable;
import java.util.List;


public interface ICrudGenericDAO<K extends Serializable, T> {

	/**
	 * @param record
	 */
	int insert(T record);

	/**
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);

	/**
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * @param primaryKey
	 * @return
	 */
	T selectByPrimaryKey(K primaryKey);
	
	List<T> selectByBean(T bean);

	List<T> selectByBean(T bean, PageBounds pageBounds);

	int countByBean(T bean);

	/**
	 * @param primaryKey
	 * @return
	 */
	int deleteByPrimaryKey(K primaryKey);
	
	
	/**
	 * @param bean
	 * @return
	 */
	int deleteByBean(T bean);

	/**
	 * 
	 * @param value
	 * @return
	 */
	int insertAndReturnKey(T value);

	/**
	 * 
	 * @param keys
	 */
	void removeKeysWithSession(List<T> keys);
	
	List<T> selectAll();

}
