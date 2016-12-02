package com.water.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.water.table.TbCabinet;

public interface CabinetDao {

	@Select("select * from cabinet")
	List<TbCabinet> queryAll();
	
	// TODO
	int queryNumByCabNo(int cabNo);
	
	// TODO
	List<TbCabinet> queryByCabNo(int cabNo);
	
	// TODO
	int insert(TbCabinet tbCabinet);
	
	// TODO
	int delete(int cabNo);
	
	// TODO
	int updateIp(int cabNo, String ip);
	
}
