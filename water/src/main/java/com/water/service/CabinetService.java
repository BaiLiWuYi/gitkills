package com.water.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.water.cabinet.core.MyProject;
import com.water.cabinet.core.WristUtil;
import com.water.dao.BoxDao;
import com.water.dao.CabinetDao;
import com.water.table.TbBox;
import com.water.table.TbCabinet;

@Service("Cabinet")
public class CabinetService {

	@Resource
	private CabinetDao cabinetDao;
	
	@Resource
	private BoxDao boxDao;
	
	public List<TbCabinet> queryAll() {
		return cabinetDao.queryAll();
	}
	
	public boolean isCabNoExist(int cabNo) {
		if (cabinetDao.queryNumByCabNo(cabNo) >= 0) {
			//柜号已存在
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean addCabinet(TbCabinet tbCabinet) {
		WristUtil wristUtil = MyProject.getInstance().getWristUtil();
		String pre = wristUtil.getPre(tbCabinet.getStartWristNo());
		int startSeqNo = wristUtil.getSeqNo(tbCabinet.getStartWristNo()); 
		if (pre==null || startSeqNo<=0) {
			//腕带定义非法
			return false;
		}
		if (isCabNoExist(tbCabinet.getCabNo())) {
			//柜号已存在
			return false;
		}
		//先添加柜子再添加腕带，万一添加失败也能在柜子列表看到，从而可以将其删除。
		if (cabinetDao.insert(tbCabinet) <= 0) {
			//添加失败
			return false;
		}
		for (int i = startSeqNo; i < tbCabinet.getBoxNum(); i++) {
			String wristNo = pre+ i;
			if (boxDao.queryNumByWristNo(wristNo) > 0) {
				//腕带号已存在
				return false;
			}
			if (boxDao.insert(new TbBox(tbCabinet.getCabNo(), i-startSeqNo+1, wristNo, 
					tbCabinet.getBigZone(), tbCabinet.getSmallZone())) <= 0) {
				//添加箱子失败
				return false;
			}
		}
		return true;
	}
	
	@Transactional
	public boolean delCabinet(int cabNo) {
		cabinetDao.delete(cabNo);
		boxDao.deleteByCabNo(cabNo);
		return false;
	}
	
	public boolean modifyIp(int cabNo, String ip) {
		int ret = cabinetDao.updateIp(cabNo, ip);
		if (ret != 1) {
			return false;
		}
		return true;
	}
	
}
