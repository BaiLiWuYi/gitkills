package com.water.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infrastructure.utils.Md5Encryption;
import com.water.service.AdminService;
import com.water.table.TbAdmin;

@Controller
public class LoginController {

	@Resource
	private AdminService adminService;
	
//	@RequestMapping("/login")
//	public String doLogin(@RequestParam String count, @RequestParam String passwd, @RequestParam String rdcode) {
//		TbAdmin admin = adminService.queryByCount(count);
//		if (admin == null) {
//			// TODO count not exist
//		}
//		
//		if (!Md5Encryption.encryption(admin.getPasswd()+ rdcode).equalsIgnoreCase(passwd)) {
//			// TODO passwd not match
//		}
//		
//		// TODO success
//	}
	
}
