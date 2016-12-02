package com.water.dao;

import java.util.List;

import com.water.table.TbRecord;

public interface RecordDao {

	// TODO
	int insert(TbRecord tbRecord);
	
	// TODO 根据查询需求实现
	List<TbRecord> query();
	
}
