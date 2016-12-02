package com.water.loadinit;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.water.cabinet.core.MyProject;

public class Loadinit implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		MyProject.init();
		
		Logger log = Logger.getLogger(Loadinit.class);
		log.info("loadinit");	
	}	
	
}
