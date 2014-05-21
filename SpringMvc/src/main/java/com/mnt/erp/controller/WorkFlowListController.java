/**

 *@copyright MNTSOFT  

 */
package com.mnt.erp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mnt.erp.bean.Organization;
import com.mnt.erp.bean.PurchaseOrder;
import com.mnt.erp.bean.PurchaseOrderLine;
import com.mnt.erp.bean.PurchaseReq;
import com.mnt.erp.bean.Status;
import com.mnt.erp.bean.Vendor;
import com.mnt.erp.bean.Voucher;
import com.mnt.erp.bean.VoucherTypeBean;
import com.mnt.erp.bean.WFAction;
import com.mnt.erp.bean.WFStep;
import com.mnt.erp.service.PurchaseRequisitionService;
import com.mnt.erp.service.WorkFlowListService;

/**
 * @author pvenkateswarlu
 * @version 1.0 11-10-2013
 */
@Controller
public class WorkFlowListController {

	public static final Logger logger = Logger
			.getLogger(WorkFlowListController.class);

	@Autowired
	PurchaseRequisitionService prservice;
	@Autowired
	WorkFlowListService workFlowListService;
	
	
	//All work flows Home
	
	@RequestMapping(value = "/taskListHome", method = RequestMethod.GET)
	public String purchaseRequisitionSearch(
			@ModelAttribute("purReqWorkFlow") PurchaseReq purchaseReq,
			HttpServletRequest request, HttpServletResponse response) {
		List<PurchaseReq> purchaseReqs = null;
		HttpSession session = null;
		List<PurchaseOrder> purchaseOrders = null;
		List<Voucher> voucherslist=null;
		String userId = null;
		try {
			session = request.getSession(false);
			userId = session.getAttribute("userId").toString();

			response.setCharacterEncoding("UTF-8");

			// here We Get the PurchaseReqiuisition Details

			
			purchaseReqs = workFlowListService.getPurchaseReqDetails(userId);
			// here We Get the PurchaseOrder Details
		
			purchaseOrders = workFlowListService
					.getPurchaseOrderDetails(userId);
			
			voucherslist=workFlowListService.getVoucherDetails(userId);
			
			logger.info("vouchers list size iss=="+voucherslist.size());
		

			if (purchaseReqs.size() != 0)
				request.setAttribute("purchasReqTable", "display");
			request.setAttribute("purchaseRequisition", purchaseReqs);
			request.setAttribute("purchaseOrderDetails", purchaseOrders);
			request.setAttribute("voucherDetails",voucherslist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loginHome";
	}
	
	
//Purchase Requisition View
	
	@RequestMapping(value = "/purReqView", method = RequestMethod.GET)
	public String purchaseRequisitionView(
			@ModelAttribute("purReqWorkFlow") PurchaseReq purchaseReq,
			HttpServletRequest request) {
		
		int purReqId = 0;
		List<PurchaseReq> purchaseReqs = null;
		List<Object[]> list = null;
		Object[] objects = null;
		HttpSession session = null;
		Set<WFAction> actions = null;
		WFStep wfStep = null;
		Iterator<Object[]> iterator = null;
		PurchaseReq purReqDetails = null;
		String workFlowListId = null;
		List<PurchaseReq> list2 = null;
		List<PurchaseReq> purReqDisplay = null;
		List<Object[]> actionAndComm = null;
		List<String> actionComments = null;
		Iterator<Object[]> actioncommItr = null;
		Object[] actioncomm = null;

		try {
			session = request.getSession(false);
			list2 = new ArrayList<PurchaseReq>();
			purReqId = Integer.parseInt(request.getParameter("purReqviewId"));

			workFlowListId = request.getParameter("workFlowListId").toString();

			list = workFlowListService.getpurReqWithStepDetails(session
					.getAttribute("userId").toString(), purReqId,
					workFlowListId);
			iterator = list.iterator();
			while (iterator.hasNext()) {
				objects = (Object[]) iterator.next();
				//purReqDetails = (PurchaseReq) objects[0];
				purchaseReq.setPurchaseReq_Id((Integer)objects[0]);
				purchaseReq.setPurchaseReqNo((String)objects[1]);
				purchaseReq.setRequestedBy((String)objects[2]);
				purchaseReq.setRequestedDate((String)objects[3]);
				purchaseReq.setReqDate((String)objects[4]);
				purchaseReq.setDescription((String)objects[5]);
				purchaseReq.setRefNo((String)objects[6]);
				
				
				//BeanUtils.copyProperties(purchaseReq, purReqDetails);
				Organization organization=(Organization)objects[7];
				Status status=(Status)objects[8];
				purchaseReq.setStatus_Id(status.getStatusId());
				
				purchaseReq.setWorkFlowListId((String) objects[10]);
				wfStep = (WFStep) objects[9];
				
				if (Integer.parseInt(wfStep.getWfstepStep()) > 1)
					actionAndComm = workFlowListService.getActionAndComments(
							wfStep.getWfstepid(),
							Integer.parseInt(wfStep.getWfstepStep()));
				

				actions = wfStep.getWfActionDetailski();
				list2.add(purchaseReq);
			}
			if (actionAndComm != null) {
				actionComments = new ArrayList<String>();
				actioncommItr = actionAndComm.iterator();
				while (actioncommItr.hasNext()) {
					actioncomm = (Object[]) actioncommItr.next();

					actionComments.add(actioncomm[1].toString());

				}
				if(actioncomm[0]!=null)
				{
				request.setAttribute("actionBy", actioncomm[0]);
				}
				request.setAttribute("comments", actionComments);
			}

			purchaseReqs = workFlowListService.getPurchaseReqDetails(session
					.getAttribute("userId").toString());
			if (purchaseReqs.size() != 0)
				request.setAttribute("purchasReqTable", "display");
			request.setAttribute("purchaseRequisition", purchaseReqs);
			// It is used to display the allworkflow details as a displaytag

			purReqDisplay = workFlowListService
					.displayPurchaseReqLineDetails(purReqId);

			request.setAttribute("purchaseReqDisplayOnly", purReqDisplay);
			// It is used to display the purReqLine details as a Display tag

			request.setAttribute("purchaseRequisitionView", list2);
			// It is used for iteration purpose..at to view the purreq details
			// view
			request.setAttribute("WFActions", actions);
			// It Is Used to Iterate the Actions in purchaseReq.jsp page
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myWorkListPopUp";
		// return "loginHome";
	}



	@RequestMapping(value = "/purReqWorkFlowUpdate", method = RequestMethod.POST)
	public String purReqWorkFlowUpdate(
			@ModelAttribute("purReqWorkFlow") PurchaseReq purchaseReq,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = null;
		String msg = null;
		try {
			session = request.getSession(false);
			msg = workFlowListService.updatePurReqWorkFlow(purchaseReq, session
					.getAttribute("userId").toString());

			getAllWorkFlowLists(session, request);// here we get the all
													// WorkFlow Lists

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loginHome";
	}

	/* ================PurchasseOrder WorkFlow======================= */

	/*
	 * =======================PurchasseOrder
	 * WorkFlow=====================================
	 */

	@RequestMapping(value = "/purOrderView", method = RequestMethod.GET)
	public String purchaseOrderView(
			@ModelAttribute("purOrderWorkFlow") PurchaseOrder purchaseOrder,
			HttpServletRequest request) {
		int purorderId = 0;
		List<PurchaseOrder> purchaseReqs = null;
		List<Object[]> list = null;
		Object[] objects = null;
		HttpSession session = null;
		Set<WFAction> actions = null;
		WFStep wfStep = null;
		Iterator<Object[]> iterator = null;
		PurchaseOrder purOrderDetails = null;
		String workFlowListId = null;
		List<PurchaseOrder> list2 = null;
		List<PurchaseOrderLine> purOrderLines = null;
		List<Object[]> actionAndComm = null;
		List<String> actionComments = null;
		Iterator<Object[]> actioncommItr = null;
		Object[] actioncomm = null;

		try {
			session = request.getSession(false);
			list2 = new ArrayList<PurchaseOrder>();
			purorderId = Integer.parseInt(request
					.getParameter("purOrderviewId"));

			workFlowListId = request.getParameter("workFlowListId").toString();
			//sql="p.purchaseOrderId,p.purchaseOrderNo,p.purchaseOrderDate,p.paymentTerms,p.memo,p.purchaseOrderValue,p.description,p.vendorDetails,p.satatusDetails,w.wfActionDetails,w.workFlowListId,w.actionComments,w.step  from WorkFlowList w com.mnt.erp.bean.PurchaseOrder p, where w.userId='"+userId+"' and w.workListStatus='Queued' and w.workListContext='PO' and p.purchaseOrderNo="+workListId+" and w.workFlowListId='"+workFlowListId+"'";
			list = workFlowListService.getpurOrderWithStepDetails(session
					.getAttribute("userId").toString(), purorderId,
					workFlowListId);
if(list!=null)
{
			iterator = list.iterator();
			while (iterator.hasNext()) {
				objects = (Object[]) iterator.next();
				purchaseOrder=new PurchaseOrder();
				purchaseOrder.setPurchaseOrderId((Integer)objects[0]);
				purchaseOrder.setPurchaseOrderNo((String)objects[1]);
				purchaseOrder.setPurchaseOrderDate((String)objects[2]);
				purchaseOrder.setPaymentTerms((String)objects[3]);
				purchaseOrder.setMemo((String)objects[4]);
				purchaseOrder.setPurchaseOrderValue((String)objects[5]);
				purchaseOrder.setDescription((String)objects[6]);
				Vendor vendor=(Vendor)objects[7];
				Status status=(Status)objects[8];
				purchaseOrder.setVendorName(vendor.getVendorName());
				purchaseOrder.setStatusName(status.getStatus());
				purchaseOrder.setWorkFlowListId((String) objects[10]);
				logger.info("step iss=="+objects[12]+"wf step iss==="+objects[11]);
				wfStep = (WFStep) objects[9];
				logger.info("step iss=="+objects[12]);

				if (Integer.parseInt(wfStep.getWfstepStep()) > 1)
					actionAndComm = workFlowListService.getActionAndComments(
							wfStep.getWfstepid(),
							Integer.parseInt(wfStep.getWfstepStep()));

				actions = wfStep.getWfActionDetailski();
				list2.add(purchaseOrder);
			}
			if (actionAndComm != null) {
				actionComments = new ArrayList<String>();
				actioncommItr = actionAndComm.iterator();
				while (actioncommItr.hasNext()) {
					actioncomm = (Object[]) actioncommItr.next();

					actionComments.add(actioncomm[1].toString());

				}
				request.setAttribute("actionBy", actioncomm[0]);
				request.setAttribute("comments", actionComments);
			}

			purchaseReqs = workFlowListService.getPurchaseOrderDetails(session
					.getAttribute("userId").toString());
			if (purchaseReqs.size() != 0)
				request.setAttribute("purchasReqTable", "display");
			request.setAttribute("purchaseRequisition", purchaseReqs);
			// It is used to display the allworkflow details as a displaytag

			purOrderLines = workFlowListService
					.displayPurchaseOrderLineDetails(purorderId);

			request.setAttribute("purchaseOrderDisplayOnly", purOrderLines);
			// It is used to display the purReqLine details as a Display tag

			request.setAttribute("purchaseOrderView", list2);
			// It is used for iteration purpose..at to view the purreq details
			// view
			request.setAttribute("WFActions", actions);
			// It Is Used to Iterate the Actions in purchaseReq.jsp page
		}
		}
catch (Exception e) {
			e.printStackTrace();
		}
		return "myPurOrderPopUp";
		// return "loginHome";
	}

	@RequestMapping(value = "/purOrderWorkFlowUpdate", method = RequestMethod.POST)
	public String purOrderWorkFlowUpdate(
			@ModelAttribute("purOrderWorkFlow") PurchaseOrder purchaseOrderLine,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = null;
		String msg = null;
		try {
			session = request.getSession(false);
			msg = workFlowListService.updatePurOrderWorkFlow(purchaseOrderLine,
					session.getAttribute("userId").toString());

			getAllWorkFlowLists(session, request);// here we get the all
													// WorkFlowLists

			request.setAttribute("workflowupdatesuccess", msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loginHome";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/voucherWorkFlowView", method = RequestMethod.GET)
	public String voucherView(
			@ModelAttribute("voucherWorkFlow") Voucher voucher,
			HttpServletRequest request) {
		int purorderId = 0;
		List<PurchaseOrder> purchaseReqs = null;
		List<Object[]> list = null;
		Object[] objects = null;
		HttpSession session = null;
		Set<WFAction> actions = null;
		WFStep wfStep = null;
		Iterator<Object[]> iterator = null;
Voucher voucherdetails=null;
		String workFlowListId = null;
		List<Voucher> voucherlist = null;
		List<Object[]> actionAndComm = null;
		List<String> actionComments = null;
		Iterator<Object[]> actioncommItr = null;
		Object[] actioncomm = null;

		try {
			logger.info("refreshhhhhh iss==");
			session = request.getSession(false);
			voucherlist = new ArrayList<Voucher>();
			purorderId = Integer.parseInt(request
					.getParameter("voucherviewId"));

			workFlowListId = request.getParameter("workFlowListId").toString();

			list = workFlowListService.getVoucherWithStepDetails(session
					.getAttribute("userId").toString(), purorderId,
					workFlowListId);
			//sql="select  p.voucherId,p.voucherDT,p.employeeId,p.amount,p.voucherNo,p.voucherTypeIdDetails,p.statusDetails,w.wfActionDetails,w.workFlowListId,w.step from WorkFlowList w ,com.mnt.erp.bean.Voucher p where w.workListId=p.voucherNo and w.userId='"+userId+"' and w.workListStatus='Queued' and w.workListContext='PO'and w.workFlowListId='"+workFlowListId+"'";		
if(list!=null)
{
			iterator = list.iterator();
			while (iterator.hasNext()) {
				objects = (Object[]) iterator.next();
				voucherdetails=new Voucher();
				
				voucherdetails.setVoucherId((Integer)objects[0]);
				voucherdetails.setVoucherDT((String)objects[1]);
				voucherdetails.setEmployeeId((String)objects[2]);
				voucherdetails.setAmount((String)objects[3]);
				voucherdetails.setVoucherNo((String)objects[4]);
				VoucherTypeBean voucherTypeBean=(VoucherTypeBean)objects[5];
				voucherdetails.setVoucherTypeName(voucherTypeBean.getVouchertype());
				Status status=(Status)objects[6];
				voucherdetails.setStatusName(status.getStatus());
				voucherdetails.setWorkFlowListId((String)objects[8]);
				
				wfStep = (WFStep) objects[7];
				logger.info("step iss=="+wfStep.getWfstepid());

				if (Integer.parseInt(wfStep.getWfstepStep()) > 1)
					actionAndComm = workFlowListService.getActionAndComments(
							wfStep.getWfstepid(),
							Integer.parseInt(wfStep.getWfstepStep()));

				actions = wfStep.getWfActionDetailski();
				voucherlist.add(voucherdetails);
			}
			if (actionAndComm != null) {
				actionComments = new ArrayList<String>();
				actioncommItr = actionAndComm.iterator();
				while (actioncommItr.hasNext()) {
					actioncomm = (Object[]) actioncommItr.next();

					actionComments.add(actioncomm[1].toString());

				}
				request.setAttribute("actionBy", actioncomm[0]);
				request.setAttribute("comments", actionComments);
			}

		/*	purchaseReqs = workFlowListService.getPurchaseOrderDetails(session
					.getAttribute("userId").toString());
			if (purchaseReqs.size() != 0)
				request.setAttribute("purchasReqTable", "display");
			request.setAttribute("purchaseRequisition", purchaseReqs);*/
			// It is used to display the allworkflow details as a displaytag

	

		
			// It is used to display the purReqLine details as a Display tag

			request.setAttribute("vocherView", voucherlist);
			// It is used for iteration purpose..at to view the purreq details
			// view
			request.setAttribute("WFActions", actions);
			// It Is Used to Iterate the Actions in purchaseReq.jsp page
		}
		}
catch (Exception e) {
			e.printStackTrace();
		}
		return "voucherworkListPopUp";
		// return "loginHome";
	}	
	
	
	

	@RequestMapping(value = "/voucherWorkFlowUpdate", method = RequestMethod.POST)
	public String voucherWorkFlowUpdate(
			@ModelAttribute("voucherWorkFlow") Voucher voucher,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = null;
		String msg = null;
		try {
		
		
			session = request.getSession(false);
			msg = workFlowListService.updatevoucherWorkFlow(voucher,
					session.getAttribute("userId").toString());

			getAllWorkFlowLists(session, request);// here we get the all
													// WorkFlowLists

			request.setAttribute("workflowupdatesuccess", msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loginHome";
	}
	
	
	
	
	

	public void getAllWorkFlowLists(HttpSession session,
			HttpServletRequest request) {
		List<PurchaseOrder> purchaseOrder = null;
		List<PurchaseReq> purchaseReqs = null;
		List<Voucher> voucherslist=null;
		try {
			purchaseOrder = workFlowListService.getPurchaseOrderDetails(session
					.getAttribute("userId").toString());

			purchaseReqs = workFlowListService.getPurchaseReqDetails(session
					.getAttribute("userId").toString());
			voucherslist=workFlowListService.getVoucherDetails(session
					.getAttribute("userId").toString());
			request.setAttribute("purchaseRequisition", purchaseReqs);
			request.setAttribute("purchasReqTable", "display");
			request.setAttribute("purchaseOrderDetails", purchaseOrder);
			request.setAttribute("voucherDetails", voucherslist);
		} catch (Exception e) {
		logger.error(e.getMessage());
		}
	}
	
	
	
	
	
	
	

}
