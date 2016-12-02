package com.water.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabinet.boxctl.PhyCabinet;
import com.water.cabinet.core.MyProject;
import com.water.cabinet.define.BOX_STATUS;
import com.water.dao.BoxDao;
import com.water.table.TbBox;

@Service("BoxService")
public class BoxService {

	@Resource
	private BoxDao boxDao;
	
	public boolean open(int cabNo, int boxNo) {
		PhyCabinet phyCabinet = MyProject.getInstance().getPhyCabinet(cabNo);
		if (phyCabinet == null) {
			return false;
		}
		return phyCabinet.open(boxNo);
	}
	
	public boolean bind(int cabNo, int boxNo) {
		TbBox tbBox = queryByCabNoAndBoxNo(cabNo, boxNo);
		if (tbBox==null || tbBox.getCardNo()==null) {
			return false;
		}
		PhyCabinet phyCabinet = MyProject.getInstance().getPhyCabinet(cabNo);
		if (phyCabinet == null) {
			return false;
		}
		if (!phyCabinet.bind(boxNo, tbBox.getCardNo())) {
			return false;
		}
		if (BOX_STATUS.int2Enum(tbBox.getStatus()) != BOX_STATUS.USE) {
			boxDao.updateStatus(cabNo, boxNo, BOX_STATUS.USE.getValue());
		}
		return true;
	}
	
	public boolean unbind(int cabNo, int boxNo) {
		TbBox tbBox = queryByCabNoAndBoxNo(cabNo, boxNo);
		if (tbBox==null || tbBox.getCardNo()==null) {
			return false;
		}
		PhyCabinet phyCabinet = MyProject.getInstance().getPhyCabinet(cabNo);
		if (phyCabinet == null) {
			return false;
		}
		if (!phyCabinet.unbind(boxNo)) {
			return false;
		}
		if (BOX_STATUS.int2Enum(tbBox.getStatus()) != BOX_STATUS.FREE) {
			boxDao.updateStatus(cabNo, boxNo, BOX_STATUS.FREE.getValue());
		}
		return true;
	}
	
	public List<TbBox> queryByCabNo(int cabNo) {
		// TODO
		return null;		
	}
	
	/**
	 * 
	 * @param cabNo
	 * @param boxNo
	 * @return
	 * 	null, if not exist.
	 */
	public TbBox queryByCabNoAndBoxNo(int cabNo, int boxNo) {
		// TODO
		return null;
	}
	
	public int queryNumByCabNo(int cabNo) {
		// TODO
		return 0;
	}
	
	public boolean updateCardNo(int cabNo, int boxNo, String cardNo) {
		if (boxDao.updateCardNo(cabNo, boxNo, cardNo) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
}
