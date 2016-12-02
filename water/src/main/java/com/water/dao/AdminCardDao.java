package com.water.dao;

import java.util.List;

import com.water.table.TbAdminCard;

public interface AdminCardDao {

	// TODO
	int queryNumBySeqNo(int seqNo);
	
	// TODO
	int update(int seqNo, String cardNo, String code);
	
	// TODO
	List<TbAdminCard> queryBySeqNo(int seqNo);
	
}
