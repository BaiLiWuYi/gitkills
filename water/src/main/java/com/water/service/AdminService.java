package com.water.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.water.dao.AdminDao;
import com.water.table.TbAdmin;

@Service
public class AdminService {

	@Resource
	private AdminDao adminDao;
	
	List<TbAdmin> queryAll() {
		return adminDao.queryAll();
	}
	
	public boolean isCountExist(String count) {
		if (adminDao.queryNumByCount(count) <= 0) {
			return false;
		} 
		return true;
	}
	
	public TbAdmin queryByCount(String count) {
		TbAdmin admin = adminDao.queryByCount(count);
		if (admin != null) {
			return admin;
		}
		return null;
	}
	
	public boolean add(TbAdmin admin) {
		if (isCountExist(admin.getCount())) {
			return false;
		}
		if (adminDao.insert(admin) <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean deleteByCount(String count) {
		if (adminDao.deleteByCount(count) <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean modifyPasswd(TbAdmin admin) {
		if (adminDao.updateByCount(admin) <= 0) {
			return false;
		}
		return true;
	}
	
}
