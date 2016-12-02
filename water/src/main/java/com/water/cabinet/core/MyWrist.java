package com.water.cabinet.core;

import java.util.HashMap;

import com.water.cabinet.project.TestWristUtil;

public class MyWrist {

	private static MyWrist myWrist;
	private static HashMap<String, WristUtil> wristMap;
	
	
	private MyWrist() {}
	
	public static MyWrist init() {
		if (myWrist == null) {
			wristMap = new HashMap<>();
			addWristUtil(new TestWristUtil());
			
			myWrist = new MyWrist(); 
		}
		
		return myWrist;
	}
	
	public MyWrist getInstance() {
		return myWrist;
	}
	
	/**
	 * 
	 * @param project project name.
	 * @return
	 * 	null, if not exist.
	 */
	public WristUtil queryByProject(String project) {
		return wristMap.get(project);
	}
	
	
	private static void addWristUtil(WristUtil wristUtil) {
		wristMap.put(wristUtil.getProjectName(), wristUtil);
	}
	
}
