package com.water.dao;

import java.util.List;

import com.water.table.TbAdmin;

public interface AdminDao {

	// TODO
	List<TbAdmin> queryAll();
	
	// TODO
	int queryNumByCount(String count);
	
	// TODO
	TbAdmin queryByCount(String count);
	
	// TODO
	int insert(TbAdmin admin);
	
	// TODO
	int deleteByCount(String count);
	
	// TODO
	int updateByCount(TbAdmin admin);
	
}
