package com.water.dao;

import java.util.List;

import com.water.table.TbBox;

public interface BoxDao {

	// TODO
	int queryNumByWristNo(String wristNo);
	
	// TODO
	int insert(TbBox tbBox);
	
	// TODO
	int deleteByCabNo(int cabNo);
	
	// TODO
	List<TbBox> queryByWristNo(String wristNo);
	
	// TODO
	int updateCardNo(int cabNo, int boxNo, String cardNo);
	
	// TODO
	int updateStatus(int cabNo, int boxNo, int status);
	
}
