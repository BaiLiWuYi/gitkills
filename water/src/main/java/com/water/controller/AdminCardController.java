package com.water.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.water.service.AdminCardService;

@Controller
@RequestMapping("/adminCard")
public class AdminCardController {

	@Resource
	private AdminCardService adminCardService;
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update(@RequestParam int seqNo, @RequestParam String cardNo, @RequestParam String code) {
		return adminCardService.update(seqNo, cardNo, code);
	}
	
	@ResponseBody
	@RequestMapping("/grant")
	public Object grant(@RequestParam int seqNo) {
		return adminCardService.grant(seqNo);
	}

	@ResponseBody
	@RequestMapping("/ungrant")
	public Object ungrant(@RequestParam int seqNo) {
		return adminCardService.ungrant(seqNo);
	}
	
}
