package com.mnt.erp.dao;

import java.util.List;

public interface OrganizationChartDao {
	public String saveOrganizationChart(Object object,String userId,String userName);

	public List<Object[]> searchOrganizationChart(int id);

	public List<Object[]> editOrganizationChartWithId(int id);

	public String updateOrganizationChart(Object object);

	public String OrganizationChartDelete(int id);

	public int OrganizationChartDuplicate(String OrganizationChart);

	public int OrganizationChartEditDuplicate(String OrganizationChart,int id);

	public List<Object[]> basicSearchOrganizationChart(String label,String operator,String searchName);
}
