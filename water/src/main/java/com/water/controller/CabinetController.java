package com.water.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.water.controller.entity.ACK_CODE;
import com.water.controller.entity.ComAckEntity;
import com.water.service.CabinetService;
import com.water.table.TbCabinet;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

	@Resource
	private CabinetService cabinetService;
	
	@RequestMapping("/queryAll")
	public String queryAll() {
		// TODO
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@RequestBody TbCabinet tbCabinet) {
		if (cabinetService.addCabinet(tbCabinet)) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
		} else {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "fail");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Object delete(@RequestParam int cabNo) {
		if (cabinetService.delCabinet(cabNo)) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
		} else {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "fail");
		}
	}
	
	/**
	 * 仅可修改柜子的ip，其余参数的变更必须通过del后再add。
	 * @param cabNo
	 * @param ip
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyIp", method = RequestMethod.GET)
	public Object modifyIp(@RequestParam int cabNo, @RequestParam String ip) {
		if (cabinetService.modifyIp(cabNo, ip)) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
		} else {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "fail");
		}
	}
	
}
