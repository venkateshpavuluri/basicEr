/**

 *@copyright MNTSOFT  


 */
package com.mnt.erp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.mnt.erp.bean.PurchaseOrder;
import com.mnt.erp.bean.PurchaseOrderLine;
import com.mnt.erp.bean.PurchaseReq;
import com.mnt.erp.bean.Status;
import com.mnt.erp.bean.Vendor;
import com.mnt.erp.bean.Voucher;
import com.mnt.erp.bean.VoucherTypeBean;
import com.mnt.erp.bean.WFAction;
import com.mnt.erp.bean.WorkFlowList;
import com.mnt.erp.dao.WorkFlowListDao;

/**
 * @author pvenkateswarlu
 *@version 1.0 11-10-2013
 */
public class WorkFlowListServiceImpl implements WorkFlowListService {
	
	Logger logger=Logger.getLogger(WorkFlowListServiceImpl.class);

	WorkFlowListDao dao;

	/**
	 * @return the dao
	 */
	public WorkFlowListDao getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(WorkFlowListDao dao) {
		this.dao = dao;
	}
	List<Object[]> objects = null;
	Object[] objectslist = null;
	Iterator<Object[]> iterator = null;
	@Override
		// TODO Auto-generated method stub
	public List<PurchaseReq> getPurchaseReqDetails(String userId) {
		List<PurchaseReq> purchaseReqs = null;
		PurchaseReq purchaseReq = null;
		PurchaseReq req = null;

		Object object = null;
		purchaseReqs=dao.getPurchaseReqDetails(userId);
				System.out.println(purchaseReqs.size());
	
		return purchaseReqs;
	}
	@Override
	public List<Object[]> getpurReqWithStepDetails(String userId,
			int workListId, String workFlowListId) {
		// TODO Auto-generated method stub
		List<Object[]> list = null;
		try {
			list = dao.getpurReqWithStepDetails(userId, workListId,
					workFlowListId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String updatePurReqWorkFlow(Object object, String userId) {
		// TODO Auto-generated method stub
		WorkFlowList workFlowList = null;
		PurchaseReq purchaseReq = null;
		String msg = null;
		try {
			purchaseReq = (PurchaseReq) object;
			workFlowList = new WorkFlowList();
			workFlowList.setWorkFlowListId(purchaseReq.getWorkFlowListId());
			workFlowList.setActionBy(userId);
			workFlowList.setActionComments(purchaseReq.getComments());
			workFlowList.setActionCode(purchaseReq.getActionNames());
			workFlowList.setActionType("PR");
			msg = dao.updatePurReqWorkFlow(workFlowList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<PurchaseReq> displayPurchaseReqLineDetails(int purReqLineId) {
		// TODO Auto-generated method stub
	
		List<PurchaseReq> list = null;
		List<Object[]> objects = null;
		Iterator<Object[]> iterator = null;
		Object[] objectsList = null;
		PurchaseReq purchaseReq = null;
		try {

			objects = dao.displayPurchaseReqLineDetails(purReqLineId);
			
			if(objects!=null)
			{

			iterator = objects.iterator();
			list = new ArrayList<PurchaseReq>();
			while (iterator.hasNext()) {
				// sql="select p.PurchaseReqLine_Id,p.qty,p.requiredDate,p.material.materialName,p.uomDetails.uom,p.statusDetails.status,p.plantDetails.plantName,p.storageLocationDetails.storageLocation from PurchaseReqLine p where p.PurchaseReqLine_Id="+purReqLineId+"";
				objectsList = (Object[]) iterator.next();
				purchaseReq = new PurchaseReq();
				purchaseReq.setPurchaseReqLine_Id((Integer) objectsList[0]);
				purchaseReq.setPurReqLineqty(Integer.parseInt(objectsList[1].toString()));
				purchaseReq.setPurReqLineReqDate((String) objectsList[2]);
				purchaseReq.setMaterialName((String) objectsList[3]);
				purchaseReq.setUomName((String) objectsList[4]);
				purchaseReq.setStatus((String) objectsList[5]);
				purchaseReq.setPlantName((String) objectsList[6]);
				purchaseReq.setStorageLocName((String) objectsList[7]);
				list.add(purchaseReq);
			}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Object[]> getActionAndComments(String stepId, int stepNo) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		try
		{
			list=dao.getActionAndComments(stepId, stepNo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderDetails(String userId) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		PurchaseOrder purchaseOrder=null;
		List<PurchaseOrder> purchaseOrders=null;
		Vendor vendor=null;
		Status status=null;
		try
		{
			list=dao.getPurchaseOrderDetails(userId);
			purchaseOrders=new ArrayList<PurchaseOrder>();
			//sql="select p.purchaseOrderId,p.purchaseOrderNo,p.purchaseOrderDate,p.paymentTerms,p.memo,p.vendorDetails,p.satatusDetails,w.purchaseOrderValue,w.description,w.workFlowListId,w.step from WorkFlowList w com.mnt.erp.bean.PurchaseOrder p where w.userId='"+userId+"' and w.workListStatus='Queued' and w.workListContext='PO'";
			if(list!=null)
			{
			iterator=list.iterator();
			while(iterator.hasNext())
			{
				objectslist=(Object[])iterator.next();
				purchaseOrder=new PurchaseOrder();
				purchaseOrder.setPurchaseOrderId((Integer)objectslist[0]);
				purchaseOrder.setPurchaseOrderNo((String)objectslist[1]);
				purchaseOrder.setPurchaseOrderDate((String)objectslist[2]);
				purchaseOrder.setPaymentTerms((String)objectslist[3]);
				purchaseOrder.setMemo((String)objectslist[4]);
				purchaseOrder.setDescription((String)objectslist[6]);
				purchaseOrder.setPurchaseOrderValue((String)objectslist[5]);
				vendor=(Vendor)objectslist[7];
				status=(Status)objectslist[8];
				purchaseOrder.setVendorName(vendor.getVendorName());
				purchaseOrder.setStatusName(status.getStatus());
				purchaseOrder.setWorkFlowListId((String)objectslist[9]);
				
				
				
				
				purchaseOrders.add(purchaseOrder);
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return purchaseOrders;
	}

	@Override
	public List<Object[]> getpurOrderWithStepDetails(String userId,
			int workListId, String workFlowListId) {
		// TODO Auto-generated method stub
		try
		{
			objects=dao.getpurOrderWithStepDetails(userId, workListId, workFlowListId);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object[]> getpurOredrActionAndComments(String stepId, int stepNo) {
		// TODO Auto-generated method stub
		try
		{
			objects=dao.getpurOredrActionAndComments(stepId, stepNo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<PurchaseOrderLine> displayPurchaseOrderLineDetails(
			int purOrderLineId) {
		// TODO Auto-generated method stub
		List<PurchaseOrderLine > list = null;
		List<Object[]> objects = null;
		Iterator<Object[]> iterator = null;
		Object[] objectsList = null;
		PurchaseOrderLine purchaseOrder= null;
		try {

			objects = dao.displayPurchaseOrderLineDetails(purOrderLineId);
if(objects!=null)
{
			iterator = objects.iterator();
			list = new ArrayList<PurchaseOrderLine>();
			while (iterator.hasNext()) {
				// sql="select p.PurchaseReqLine_Id,p.qty,p.requiredDate,p.material.materialName,p.uomDetails.uom,p.statusDetails.status,p.plantDetails.plantName,p.storageLocationDetails.storageLocation from PurchaseReqLine p where p.PurchaseReqLine_Id="+purReqLineId+"";
				objectsList = (Object[]) iterator.next();
				purchaseOrder = new PurchaseOrderLine();
				purchaseOrder.setPurchaseOrderId((Integer)objectsList[0]);
			/*	purchaseOrder.setLineNumber((Integer)objectsList[1]);*/
				purchaseOrder.setUnitPrice((Float)objectsList[1]);
				purchaseOrder.setMaterialName((String)objectsList[2]);
				purchaseOrder.setUomName((String) objectsList[3]);
				purchaseOrder.setQuantity((Float)objectsList[4]);
				purchaseOrder.setLineAmt((Float)objectsList[5]);
				purchaseOrder.setDueDate((String)objectsList[6]);
				list.add(purchaseOrder);
			}
}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String updatePurOrderWorkFlow(Object object, String userId) {
		// TODO Auto-generated method stub
		String msg=null;
		try
		{
			msg=dao.updatePurOrderWorkFlow(object, userId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public String saveWorkFlowListDaoDetails(Object object){
		
		String list=null;
		try
		{
			list=dao.saveWorkFlowListDaoDetails(object);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return list;
		}
		

	public List<Object[]> getStepUser(){
		// TODO Auto-generated method stub
				List<Object[]>  list=null;
				try
				{
					list=dao.getStepUser();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return list;
	}

	@Override
	public List<Voucher> getVoucherDetails(String userId) {
		// TODO Auto-generated method stub
	
		Voucher voucher=null;
		List<Voucher> vouchers=null;
		
		try
		{
			objects=dao.getVoucherDetails(userId);
			if(objects!=null)
			{
				
				vouchers=new ArrayList<Voucher>();
//sql="select p.voucherId,p.voucherDT,p.employeeId,p.amount,p.voucherNo,p.voucherTypeIdDetails,p.satatusDetails,w.workFlowListId,w.step from WorkFlowList w ,com.mnt.erp.bean.Voucher p where w.workListId=p.voucherNo and w.userId='"+userId+"' and w.workListStatus='Queued' and w.workListContext='VO'";						
			Iterator<Object[]> iterator=objects.iterator();
			while(iterator.hasNext())
			{
			 objectslist=(Object[])iterator.next();
				 voucher=new Voucher();
				 voucher.setVoucherId((Integer)objectslist[0]);
				 voucher.setVoucherDT((String)objectslist[1]);
				 voucher.setEmployeeId((String)objectslist[2]);
				 voucher.setAmount((String)objectslist[3]);
				 voucher.setVoucherNo((String)objectslist[4]);
				 VoucherTypeBean voucherTypeBean=(VoucherTypeBean)objectslist[5];
				 voucher.setVoucherTypeName(voucherTypeBean.getVouchertype());
				 Status status=(Status)objectslist[6];
				 voucher.setStatusName(status.getStatus());
				 voucher.setWorkFlowListId((String)objectslist[7]);
				 
				 logger.info("status Name iss=="+status.getStatus());
				 vouchers.add(voucher);
				 
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return vouchers;
	}

	@Override
	public List<Object[]> getVoucherWithStepDetails(String userId,
			int voucherId, String workFlowListId) {
		// TODO Auto-generated method stub
		
		try
		{
			objects=dao.getVoucherWithStepDetails(userId, voucherId, workFlowListId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public String updatevoucherWorkFlow(Object object, String userId) {
		// TODO Auto-generated method stub
		String msg=null;
		try
		{
			msg=dao.updatevoucherWorkFlow(object, userId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return null;
	}
	
}
