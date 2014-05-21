/**
 * @Copyright MNTSOFT
 */
package com.mnt.erp.service;

import java.util.List;

import com.mnt.erp.bean.UserOrganizationBean;
import com.mnt.erp.dao.SalesOrderDao;

/**
 * @author Naresh
 * @version 1.0 20-11-2013
 */
public class SalesOrderServiceImpl implements SalesOrderService {
	String sus = "success";
	String del;
	Long l = 0l;
	List<Object[]> objects = null;
	List<Object> obj = null;

	SalesOrderDao salesOrderDao;

	public SalesOrderDao getSalesOrderDao() {
		return salesOrderDao;
	}

	public void setSalesOrderDao(SalesOrderDao salesOrderDao) {
		this.salesOrderDao = salesOrderDao;
	}

	@Override
	public List<Object[]> selectCustomerIds() {
		try {
			objects = salesOrderDao.selectCustomerIds();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object[]> selectSalesGroupIds() {
		try {
			objects = salesOrderDao.selectSalesGroupIds();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object[]> selectOrderTypeIds() {
		try {
			objects = salesOrderDao.selectOrderTypeIds();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object[]> selectPaymentTermIds() {
		try {
			objects = salesOrderDao.selectPaymentTermIds();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public int updateCheckSalesOrder(String salesNo, int siId) {
		int i = 0;
		try {
			i = salesOrderDao.updateCheckSalesOrder(salesNo, siId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Long checkSalesOrder(String salesNo) {
		try {
			l = salesOrderDao.checkSalesOrder(salesNo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public String saveSalesOrder(Object object) {
		try {

			sus = salesOrderDao.saveSalesOrder(object);

		} catch (Exception e) {
			e.printStackTrace();
			sus = "F";
		}
		return sus;
	}

	@Override
	public List<Object[]> searchSalesOrder() {
		try {
			objects = salesOrderDao.searchSalesOrder();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object> searchSalesOrderWithId(int sId) {
		try {
			obj = salesOrderDao.searchSalesOrderWithId(sId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String updateSalesOrder(Object object) {
		try {
			sus = salesOrderDao.updateSalesOrder(object);
		} catch (Exception e) {
			e.printStackTrace();
			sus = "F";
		}
		return sus;
	}

	@Override
	public String deleteSalesOrder(int dId) {
		try {
			del = salesOrderDao.deleteSalesOrder(dId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}

	@Override
	public List<Object[]> basicSearchSalesOrder(String label, String operator,
			String searchName) {
		try {
			objects = salesOrderDao.basicSearchSalesOrder(label, operator,
					searchName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object[]> populateCurrencyIds() {
		try {
			objects = salesOrderDao.populateCurrencyIds();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object[]> populateMaterialIds() {
		try {
			objects = salesOrderDao.populateMaterialIds();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object[]> populateUOMIds() {
		try {
			objects = salesOrderDao.populateUOMIds();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public String deleteSalesOrderLine(int slId) {
		try {
			del = salesOrderDao.deleteSalesOrderLine(slId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}

	@Override
	public String deleteSalesOrderSch(int scId) {
		try {
			del = salesOrderDao.deleteSalesOrderSch(scId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}

	@Override
	public String getOrganizationId(String userId) {
		String org = null;
		List<UserOrganizationBean> user = null;
		try {
			user = salesOrderDao.getOrganizationId(userId);
			for (UserOrganizationBean u : user) {
				org = u.getOrgid();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return org;

	}

}
