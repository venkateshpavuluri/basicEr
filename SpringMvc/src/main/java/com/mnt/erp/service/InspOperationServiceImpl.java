/**
 * @Copyright MNTSOFT
 */
package com.mnt.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mnt.erp.dao.InspOperationDao;

/**
 * @author Naresh
 * @version 1.0 05-02-2014
 */
public class InspOperationServiceImpl implements InspOperationService {
	List<Object[]> objects = null;
	List<Object> obj = null;
	boolean flag = true;
	Long l = 0l;

	@Autowired
	InspOperationDao inspOprDao;

	@Override
	public Long updateCheckInspOperation(String inspNo, int inspId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long checkInspOperationCout(String inspNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveInspOperationDetails(Object object) {
		try {
			flag = inspOprDao.saveInspOperationDetails(object);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Object[]> searchInspOperation() {
		try {
			objects = inspOprDao.searchInspOperation();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object> searchInspOperationWithId(int inspId) {
		try {
			obj = inspOprDao.searchInspOperationWithId(inspId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean updateInspOperation(Object object) {
		try {
			flag = inspOprDao.updateInspOperation(object);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean deleteInspOperation(int inspId) {
		try {
			flag = inspOprDao.deleteInspOperation(inspId);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean deleteInspOperStep(int stepId) {
		try {
			flag = inspOprDao.deleteInspOperStep(stepId);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Object[]> basicSearchInspOperation(String label,
			String operator, String searchName) {
		try {
			objects = inspOprDao.basicSearchInspOperation(label, operator,
					searchName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

}
