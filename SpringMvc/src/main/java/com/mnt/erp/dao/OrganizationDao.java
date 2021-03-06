/**
 * @Copyright MNTSOFT

 * 
 */
package com.mnt.erp.dao;

import java.util.List;

import com.mnt.erp.bean.Organization;

/**
 * @author pvenkateswarlu
 * @version 1.0 18-09-2013
 */
public interface OrganizationDao {
	
	public List<Object[]> organizationAdvanceSearch(String name);
	
	public List<Object[]> setOrganizationSearch(String name);
	
	public String saveOrganizationDetails(Object object,String userId,String userName);

	public List<Object[]> getOrganizationIds();

	public List<Organization> searchOrganization();

	public List<Organization> searchOrganizationWithId(int id);

	public String updateOrganization(Object object);

	public String deleteOrganization(int id);

	public Long duplicateCheck(String orgName);

	public Long updateDuplicateCheck(String orgName, int orgId);
	
	public List<Object[]> selectOrganizationDetails();
	
	public List<Organization> basicSearchOrganization(String label, String operator,
			String searchName);
	
}
