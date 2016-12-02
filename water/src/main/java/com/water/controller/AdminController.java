package com.water.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.water.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private AdminService adminService;
	
	// TODO 按需实现
	public String query() {
		return null;
	}
	
	// TODO
//	@ResponseBody
//	@RequestMapping("/add")
//	public Object add(@RequestParam String count, @RequestParam String passwd) {
//		
//	}
	
	// TODO
//	@ResponseBody
//	@RequestMapping("/delete")
//	public Object delete(@RequestParam String count) {
//		
//	}
	
}
