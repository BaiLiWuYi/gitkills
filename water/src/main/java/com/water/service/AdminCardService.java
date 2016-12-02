package com.water.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cabinet.boxctl.PhyCabinet;
import com.water.cabinet.core.MyProject;
import com.water.controller.entity.ACK_CODE;
import com.water.controller.entity.ComAckEntity;
import com.water.dao.AdminCardDao;
import com.water.table.TbAdminCard;

@Service
@RequestMapping("/adminCard")
public class AdminCardService {

	@Resource
	private AdminCardDao adminCardDao;
	
	public Object update(int seqNo, String cardNo, String code) {
		if (adminCardDao.queryNumBySeqNo(seqNo) != 1) {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "seqNo not exist.");
		}
		if (adminCardDao.update(seqNo, cardNo, code) != 1) {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "update fail.");
		}
		return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
	}
	
	public Object grant(int seqNo) {
		List<TbAdminCard> adminCards = adminCardDao.queryBySeqNo(seqNo);
		if (adminCards.size() != 1) {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "seqNo not exist.");
		} 
		TbAdminCard adminCard = adminCards.get(0);
		String msg = new String();
		boolean res = true;
		PhyCabinet[] phyCabinets = MyProject.getInstance().getPhyCabinets();
		for (PhyCabinet phyCabinet : phyCabinets) {
			if (phyCabinet == null) {
				continue;
			}
			if (!phyCabinet.grant(seqNo, adminCard.getCardNo(), adminCard.getCode())) {
				res = false;
				msg += phyCabinet.getCabNo()+ ", ";//保存下发失败的柜号
				continue;
			}
		}
		if (res) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
 		} else {
 			return new ComAckEntity(ACK_CODE.ERR.getValue(), msg);
 		}
	}

	public Object ungrant(int seqNo) {
		if (adminCardDao.queryNumBySeqNo(seqNo) != 1) {
			return new ComAckEntity(ACK_CODE.ERR.getValue(), "seqNo not exist.");
		}
		PhyCabinet[] phyCabinets = MyProject.getInstance().getPhyCabinets();						
		String msg = new String();
		boolean res = true;
		for (PhyCabinet phyCabinet : phyCabinets) {
			if (phyCabinet == null) {
				continue;
			}
			if (!phyCabinet.ungrant(seqNo)) {
				res = false;
				msg += phyCabinet.getCabNo()+ ", ";//保存下发失败的柜号
				continue;
			}
		}
		if (res) {
			return new ComAckEntity(ACK_CODE.OK.getValue(), "success");
 		} else {
 			return new ComAckEntity(ACK_CODE.ERR.getValue(), msg);
 		}
	}
	
}
