package com.water.cabinet.core;

public interface WristUtil {

	/**
	 * 
	 * @param wristNo
	 * @return
	 * 	null, if wristNo is illegal.
	 */
	String getPre(String wristNo);
	
	/**
	 * 
	 * @param wristNo
	 * @return
	 * 	<=0, if wristNo is illegal.
	 */
	int getSeqNo(String wristNo);
	
	String getProjectName();
	
}
