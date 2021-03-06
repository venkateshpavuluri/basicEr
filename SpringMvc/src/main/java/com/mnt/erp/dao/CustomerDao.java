/**
 * @Copyright MNTSOFT
 */
package com.mnt.erp.dao;

import java.util.List;

/**
 * @author Naresh
 * @version 1.0 23-09-2013
 */
public interface CustomerDao {

	public int updateCheckCustomer(String custName, int custId);

	public Long checkCustomer(String custName);
	
	public String saveCustomerDetails(Object object);

	public List<Object[]> searchCustomer(String active);

	public List<Object[]> searchCustomerWithId(int cId, String active);

	public List<Object> editCustomerWithId(int cId, String active);

	public String updateCustomer(Object object);

	public String deleteCustomer(int cId);
	
	public String deleteCustomerBankDet(int custId);
	public String deleteCustomerAccount(int acId);

	public List<Object[]> selectCustomer(String active);

	public List<Object[]> selectCustomerGroup();

	public List<Object[]> selectSalesArea();

	public List<Object[]> getCustomerIds();

	public List<Object[]> basicSearchCustomer(String label, String operator,
			String searchName);

	public List<Object[]> setCustomerAdvanceSearch(String name);

	public List<Object[]> setCustomerSearch(String name);

	public List<Object[]> selectAccountGroupIds();

	public List<Object[]> selectPaymentTermIds();

	public List<Object[]> selectPaymentMethodIds();

}
