package com.water.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.water.dao.RecordDao;

@Service
public class RecordService {

	@Resource
	private RecordDao recordDao;
	
	
	
}
