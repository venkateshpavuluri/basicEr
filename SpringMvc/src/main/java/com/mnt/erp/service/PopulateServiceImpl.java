/**
 * Copyright MNTSOFT 
 */
package com.mnt.erp.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mnt.erp.dao.PoPulateDao;

/**
 * @author pvenkateswarlu
 * @version 1.0 09-10-2013
 */
public class PopulateServiceImpl implements PopulateService {

	public PoPulateDao getDao() {
		return dao;
	}

	public void setDao(PoPulateDao dao) {
		this.dao = dao;
	}

	PoPulateDao dao;

	@Override
	public List<Object[]> poPulate(String sql) {
		List<Object[]> list = null;
		try {
			list = dao.poPulate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Map<String, String> populatePopUp(String sql) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		List<Object[]> list = null;

		try {
			list = dao.poPulate(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((String) objects[0], (String) objects[1]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}

	public Map<Integer, String> populateSelectBox(String sql) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<Object[]> list = null;

		try {
			list = dao.poPulate(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((Integer) objects[0], (String) objects[1]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}

}
