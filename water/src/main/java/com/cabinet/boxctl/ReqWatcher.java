package com.cabinet.boxctl;

/**
 * 负责tcp途径的请求的观察器。
 * @author Administrator
 *
 */
public class ReqWatcher {

	private static ReqWatcher watcher;
	
	private ReqWatcher() {}
	
	public static ReqWatcher init() {
		if (watcher == null) {
			// TODO
			
			
			
			watcher = new ReqWatcher();
		}
		
		return watcher;	
	}
	
	
	
}
