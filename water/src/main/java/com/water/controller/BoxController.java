package com.water.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.water.controller.entity.ACK_CODE;
import com.water.controller.entity.ComAckEntity;
import com.water.service.BoxService;
import com.water.table.TbBox;

@Controller
@RequestMapping("/box")
public class BoxController {

	@Resource
	private BoxService boxService;
	
	// TODO 腕带状态显示
	
	@ResponseBody
	@RequestMapping(value = "/updateCardNo", method = RequestMethod.GET)
	public Object updateCardNo(@RequestParam int cabNo, @RequestParam int boxNo, @RequestParam String cardNo) {
		TbBox tbBox = boxService.queryByCabNoAndBoxNo(cabNo, boxNo);
		if (tbBox == null) {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "box not exist.");
		}
		if (boxService.updateCardNo(cabNo, boxNo, cardNo)) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
		} else {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "fail");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/open", method = RequestMethod.GET)
	public Object open(@RequestParam int cabNo, @RequestParam int boxNo) {
		if (boxService.open(cabNo, boxNo)) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
		} else {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "fail");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/bind", method = RequestMethod.GET)
	public Object bind(@RequestParam int cabNo, @RequestParam int boxNo) {
		if (boxService.bind(cabNo, boxNo)) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
		} else {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "fail");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/unbind", method = RequestMethod.GET)
	public Object unbind(@RequestParam int cabNo, @RequestParam int boxNo) {
		if (boxService.unbind(cabNo, boxNo)) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
		} else {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "fail");
		}
	}
	
}
