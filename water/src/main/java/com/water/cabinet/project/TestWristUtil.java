package com.water.cabinet.project;

import com.water.cabinet.core.WristUtil;

/**
 * 腕带样式：len=5, [一位字母]+[四位字母], 例：A0001;
 * @author Administrator
 *
 */
public class TestWristUtil implements WristUtil {

	private static final int WRIST_NO_SIZE = 5;
	
	@Override
	public String getPre(String wristNo) {
		if (wristNo.length() != WRIST_NO_SIZE) {
			return null;
		}
		return wristNo.substring(0, 1);
	}

	@Override
	public int getSeqNo(String wristNo) {
		if (wristNo.length() != WRIST_NO_SIZE) {
			return 0;
		}
		String strSeqNo = wristNo.substring(1, WRIST_NO_SIZE);
		try {
			int seqNo = Integer.parseInt(strSeqNo);
			return seqNo;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getProjectName() {
		return TestProjectConf.PROJECT_NAME;
	}

}
