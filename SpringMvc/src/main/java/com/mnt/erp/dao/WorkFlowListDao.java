/**

 *@copyright MNTSOFT  


 */
package com.mnt.erp.dao;

import java.util.List;

import com.mnt.erp.bean.PurchaseOrder;
import com.mnt.erp.bean.PurchaseReq;

/**
 * @author pvenkateswarlu
 *@version 1.0 11-10-2013
 */
public interface WorkFlowListDao {
	/*==========PurchaseRequisition WorkFlow Methods==================================================*/
	public List<PurchaseReq> getPurchaseReqDetails(String userId);
	public List<Object[]> getpurReqWithStepDetails(String userId,int workListId,String workFlowListId);
	public String updatePurReqWorkFlow(Object object);
	public List<Object[]> displayPurchaseReqLineDetails(int purReqLineId);
	public List<Object[]> getActionAndComments(String stepId,int stepNo);
	/*==========PurchaseOrder WorkFlow Methods==================================================*/
	public List<Object[]> getPurchaseOrderDetails(String userId);
	public List<Object[]> getpurOrderWithStepDetails(String userId,
			int workListId, String workFlowListId);
	public List<Object[]> getpurOredrActionAndComments(String stepId, int stepNo);
	public List<Object[]> displayPurchaseOrderLineDetails(
			int purOrderLineId);
	public String updatePurOrderWorkFlow(Object object,String userId);
	public List<Object[]> getStepUser();
	public String saveWorkFlowListDaoDetails(Object object);
	
	//Vocher WorkFlow Methods
	public List<Object[]> getVoucherDetails(String userId);
	public List<Object[]> getVoucherWithStepDetails(String userId,
			int voucherId, String workFlowListId);
	public String updatevoucherWorkFlow(Object object, String userId);
	
	
	
}
