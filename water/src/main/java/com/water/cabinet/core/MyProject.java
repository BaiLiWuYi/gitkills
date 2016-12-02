package com.water.cabinet.core;

import java.util.List;

import org.apache.log4j.Logger;

import com.cabinet.boxctl.PhyCabinet;
import com.cabinet.boxctl.ReqWatcher;
import com.water.cabinet.project.TestProjectConf;
import com.water.service.BoxService;
import com.water.service.CabinetService;
import com.water.table.TbCabinet;

public class MyProject {

	/*configuration*/
	public static final int CABINET_NUM = TestProjectConf.CABINET_NUM;
	public static final String PROJECT_NAME = TestProjectConf.PROJECT_NAME;
	public static final int CARDNO_SIZE = 10;
	/******/
	
	private static Logger log = Logger.getLogger(MyProject.class);
	private static MyProject project;
	private static PhyCabinet[] phyCabinets;
	private static WristUtil wristUtil;
	
	private MyProject() {}
	
	public static MyProject getInstance() {
		return project;
	}
	
	public static MyProject init() {
		if (project == null) {	
			checkCabinets();
			
			initPhyCabinets();
			
			ReqWatcher.init();
			//初始化tcp途径的req观察器
			
			initWristUtils();
			//初始化不同项目的腕带定义，并获取当前项目的腕带定义
			
			project = new MyProject();
		}
		
		return project;
	}
	
	public PhyCabinet getPhyCabinet(int cabNo) {
		int location = getLocationByCabNo(cabNo);
		if (location<0 || location>=phyCabinets.length) {
			return null;
		}
		return phyCabinets[location];
	}
	
	public PhyCabinet[] getPhyCabinets() {
		return phyCabinets;
	}
	
	public WristUtil getWristUtil() {
		return wristUtil;
	}
	
	private static void initWristUtils() {
		wristUtil = MyWrist.init().queryByProject(PROJECT_NAME);
		if (wristUtil == null) {
			log.error("wristUtil not found, project:"+ PROJECT_NAME);
		}
	}
	
	private static void initPhyCabinets() {
		phyCabinets = new PhyCabinet[CABINET_NUM];
		CabinetService cabinetService = (CabinetService) SpringBeanFactoryUtils.getBean("Cabinet");
		List<TbCabinet> tbCabinets = cabinetService.queryAll();
		for (TbCabinet tbCabinet : tbCabinets) {
			int location = getLocationByCabNo(tbCabinet.getCabNo());
			if (location<0 || location>=phyCabinets.length) {
				log.error("location illegal, cabNo:"+ tbCabinet.getCabNo());
				continue;
			}
			if (phyCabinets[location] != null) {
				log.error("already exist, cabNo:"+ tbCabinet.getCabNo());
				continue;
			}
			phyCabinets[location] = new PhyCabinet(tbCabinet.getIp(), tbCabinet.getCabNo(), tbCabinet.getBoxNum());
		}
	}
	
	/**
	 * 检测柜子和箱子参数是否匹配
	 */
	private static void checkCabinets() {
		CabinetService cabinetService = (CabinetService) SpringBeanFactoryUtils.getBean("Cabinet");
		BoxService boxService = (BoxService) SpringBeanFactoryUtils.getBean("BoxService");
		
		List<TbCabinet> tbCabinets = cabinetService.queryAll();
		if (tbCabinets.size() != CABINET_NUM) {
			log.error("cabinets size not equal, size:"+ tbCabinets.size());
		}
		for (TbCabinet tbCabinet : tbCabinets) {
			int boxNum = boxService.queryNumByCabNo(tbCabinet.getCabNo());
			if (boxNum != tbCabinet.getBoxNum()) {
				log.error("boxNum not equal, cabNo:"+ tbCabinet.getCabNo());
			}
		}
	}

	private static int getLocationByCabNo(int cabNo) {
		return cabNo-1;
	}

}
