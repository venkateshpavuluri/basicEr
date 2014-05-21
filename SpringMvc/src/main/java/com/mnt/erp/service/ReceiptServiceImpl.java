/**
 *  @Copyright MNTSOFT
 */
package com.mnt.erp.service;

import java.util.List;

import com.mnt.erp.dao.ReceiptDao;

/**
 * @author Naresh
 * @version 1.0 21-01-2014
 */
public class ReceiptServiceImpl implements ReceiptService {

	List<Object[]> objects = null;
	List<Object> obj = null;
	boolean flag = true;
	Long l = 0l;
	ReceiptDao receiptDao;

	public ReceiptDao getReceiptDao() {
		return receiptDao;
	}

	public void setReceiptDao(ReceiptDao receiptDao) {
		this.receiptDao = receiptDao;
	}

	@Override
	public Long updateCheckReceipt(String recNo, int recId) {
		try {
			l = receiptDao.updateCheckReceipt(recNo, recId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public Long checkReceiptCout(String recNo) {
		try {
			l = receiptDao.checkReceiptCout(recNo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public boolean saveReceiptDetails(Object object) {
		try {
			flag = receiptDao.saveReceiptDetails(object);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Object[]> searchReceipt() {
		try {
			objects = receiptDao.searchReceipt();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object> searchReceiptWithId(int recId) {
		try {
			obj = receiptDao.searchReceiptWithId(recId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean updateReceipt(Object object) {
		try {
			flag = receiptDao.updateReceipt(object);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean deleteReceipt(int recId) {
		try {
			flag = receiptDao.deleteReceipt(recId);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Object[]> basicSearchReceipt(String label, String operator,
			String searchName) {
		try {
			objects = receiptDao
					.basicSearchReceipt(label, operator, searchName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

}
