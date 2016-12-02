package com.water.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.water.service.RecordService;

@Controller
@RequestMapping("/record")
public class UseRecordController {

	@Resource
	private RecordService recordService;
	
	// TODO 按需实现
	public String query() {
		return null;
	}
	
}
