/**
 * @Copyright MNTSOFT
 */
package com.mnt.erp.dao;

import java.util.List;

/**
 * @author Naresh
 *@version 1.0 05-02-2014
 *
 */
public interface InspOperationDao {
	
	public Long updateCheckInspOperation(String inspNo, int inspId);

	public Long checkInspOperationCout(String inspNo);

	public boolean saveInspOperationDetails(Object object);

	public List<Object[]> searchInspOperation();

	public List<Object> searchInspOperationWithId(int inspId);

	public boolean updateInspOperation(Object object);

	public boolean deleteInspOperation(int inspId);
	
	public boolean deleteInspOperStep(int stepId);

	public List<Object[]> basicSearchInspOperation(String label,
			String operator, String searchName);

}
