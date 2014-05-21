/**
 * @Copyright MNTSOFT
 */
package com.mnt.erp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mnt.erp.bean.InspOperationBean;
import com.mnt.erp.bean.InspOperationStep;
import com.mnt.erp.bean.InspectionType;
import com.mnt.erp.bean.Material;
import com.mnt.erp.service.AuditLogService;
import com.mnt.erp.service.InspOperationService;
import com.mnt.erp.service.MenuService;
import com.mnt.erp.service.PopulateService;
import com.mnt.erp.service.XmlLabelsService;

/**
 * @author Naresh
 * @version 1.0 05-02-2014
 */
@Controller
public class InspOperationController {
	private static final Logger log = Logger
			.getLogger(InspOperationController.class);
	@Autowired
	XmlLabelsService xmlService;
	@Autowired
	InspOperationService inspOperService;
	@Autowired
	PopulateService populateService;
	@Autowired
	MenuService menuService;
	@Autowired
	AuditLogService auditLogService;

	List<Object[]> list = null;
	Iterator<Object[]> itr = null;
	Object[] objects = null;
	List<Object> obj = null;
	String message = null;
	HttpSession session = null;
	boolean flag = true;

	@RequestMapping(value = "/inspOperationHome", method = RequestMethod.GET)
	public ModelAndView inspOperationHome(
			@ModelAttribute("inspOperationCmd") InspOperationBean inspOperationBean,
			SessionStatus status, HttpServletResponse response,HttpServletRequest request) {
		response.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		List<String> list = menuService.getPrivilige(
				"inspOperationHome.mnt", session.getAttribute("userId")
						.toString());
		session.setAttribute("privilegeList", list);
		return new ModelAndView("inspOperationHome", "inspOperationCmd",
				inspOperationBean);
	}

	@ModelAttribute("materialSelect")
	public Map<Integer, String> populatMaterialIds() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select m.material_Id,m.materialName from Material m");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("inspTypeSelect")
	public Map<Integer, String> populatInspTypeIds() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select m.inspectionTypeId,m.inspectionType from InspectionType m");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/inspOperationAdd", method = RequestMethod.POST)
	public String saveInspOperation(
			@ModelAttribute("inspOperationCmd") InspOperationBean inspOperationBean,
			HttpServletRequest request, HttpServletResponse response) {
		String inspOperationSave = null;
		response.setCharacterEncoding("UTF-8");
		List<InspOperationStep> inspOperationStep = new ArrayList<InspOperationStep>();

		String stepNo[] = inspOperationBean.getInspOperStepNo();
		String sam[] = inspOperationBean.getNoOfSamples();

		InspOperationBean inspBean = (InspOperationBean) inspOperationBean;

		if (stepNo != null) {
			for (int m = 0; m < stepNo.length; m++) {
				InspOperationStep dnd = new InspOperationStep();
				dnd.setInspOperStepNo(stepNo[m]);
				dnd.setNoOfSamples(sam[m]);
				inspOperationStep.add(dnd);
			}
		}
		try {
			inspBean.setInspOprStep(inspOperationStep);
			flag = inspOperService.saveInspOperationDetails(inspBean);

			if (flag == true) {
				session = request.getSession(false);
				Date date = new Date();
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
				auditLogService.setAuditLogSave(session.getAttribute("userId")
						.toString(),"A","Inspection Operation","ROW" ,String.valueOf(inspBean.getInspOperationId()),"1",modifiedDate,session.getAttribute("userName").toString());
				inspOperationSave = "Inspection Operation Data Saved Successfully";

			} else {
				inspOperationSave = "Inspection Operation Data Insertion Failures";
				return "redirect:inspOperationHome.mnt?addIOSFail="
						+ inspOperationSave + "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:inspOperationHome.mnt?addIOSsus=" + inspOperationSave
				+ "";

	}

	@RequestMapping(value = "/inspOperationSearch", method = RequestMethod.GET)
	public String searchinspOperation(
			@ModelAttribute("inspOperationCmd") InspOperationBean inspOperationBeanSearch,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");
		List<InspOperationBean> IOSList = new ArrayList<InspOperationBean>();
		try {

			String dbField = inspOperationBeanSearch.getXmlLabel();
			String operation = inspOperationBeanSearch.getOperations();
			String basicSearchId = inspOperationBeanSearch.getBasicSearchId();

			if (operation.equals("_%")) {
				operation = " like ";
				basicSearchId = basicSearchId + "%";

			} else if (operation.equals("%_")) {
				operation = " like ";
				basicSearchId = "%" + basicSearchId;

			} else if (operation.equals("%_%")) {
				operation = " like ";
				basicSearchId = "%" + basicSearchId + "%";
			}

			if (basicSearchId == "") {
				list = inspOperService.searchInspOperation();

			} else {

				list = inspOperService.basicSearchInspOperation(dbField,
						operation, basicSearchId);
			}
			itr = list.iterator();
			while (itr.hasNext()) {
				objects = (Object[]) itr.next();
				InspOperationBean dnb = new InspOperationBean();
				dnb.setInspOperationId((Integer) objects[0]);
				dnb.setOperationNo((String) objects[1]);
				InspectionType type = ((InspectionType) objects[2]);
				dnb.setInspTypeId(type.getInspectionType());
				Material mt = ((Material) objects[3]);
				dnb.setMaterialId(mt.getMaterialName());

				IOSList.add(dnb);
			}
			request.setAttribute("IOSList", IOSList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inspOperationHome";

	}

	@RequestMapping(value = "/inspOperationDelete", method = RequestMethod.GET)
	public String inspOperationDelete(
			@ModelAttribute("inspOperationCmd") InspOperationBean inspOperationBeanDelete,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");
		String IOSDelete = null;
		int dId = Integer.parseInt(request.getParameter("inspOperationId"));

		try {
			flag = inspOperService.deleteInspOperation(dId);

			if (flag == true) {
				session = request.getSession(false);
				Date date = new Date();
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
				auditLogService.setAuditLogSave(session.getAttribute("userId")
						.toString(),"D","Inspection Operation","ROW" ,String.valueOf(dId),"1",modifiedDate,session.getAttribute("userName").toString());
				IOSDelete = "Inspection Operation Deleted Successfully";

			} else {
				IOSDelete = "Inspection Operation Deletion Failed due to Conatraint Violation";
				return "redirect:inspOperationHome.mnt?DeleteIOSFail="
						+ IOSDelete + "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:inspOperationHome.mnt?DeleteIOSsus=" + IOSDelete + "";

	}

	@RequestMapping(value = "/inspOperationEdit", method = RequestMethod.GET)
	public String inspOperationEdit(
			@ModelAttribute("inspOperationCmd") InspOperationBean inspEdit,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("inspOperationId"));
		List<InspOperationBean> inspOperationBean = new ArrayList<InspOperationBean>();
		List<InspOperationStep> stepList = new ArrayList<InspOperationStep>();
		InspOperationBean isp = null;
		try {
			obj = inspOperService.searchInspOperationWithId(id);
			Iterator<Object> iterator = obj.iterator();
			while (iterator.hasNext()) {
				Object obj = iterator.next();
				isp = (InspOperationBean) obj;
				List<InspOperationStep> step = isp.getInspOprStep();
				Iterator<InspOperationStep> itr = step.iterator();
				while (itr.hasNext()) {
					Object ob = (Object) itr.next();
					InspOperationStep ios = (InspOperationStep) ob;
					stepList.add(ios);
				}

				isp.setInspOprStep(stepList);
				inspOperationBean.add(isp);
			}
			model.addAttribute("inspOperationCmd", isp);
			request.setAttribute("inspOperationEdit", inspOperationBean);
			request.setAttribute("stepList", stepList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			obj = null;
		}
		return "inspOperationHome";

	}

	@RequestMapping(value = "/inspOperationUpdate", method = RequestMethod.POST)
	public String inspOperationUpdate(
			@ModelAttribute("inspOperationCmd") InspOperationBean inspUpdate,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String inspUpadted = null;

		List<InspOperationStep> inspOperationStep = new ArrayList<InspOperationStep>();

		String stepNo[] = inspUpdate.getInspOperStepNo();
		String sam[] = inspUpdate.getNoOfSamples();
		int[] stepId = inspUpdate.getInspOperStepId();
		String childDelete = "", ss = "1";
		int id = 0;

		if (stepNo != null) {
			for (int m = 0; m < stepNo.length; m++) {
				InspOperationStep dnd = new InspOperationStep();
				dnd.setInspOperStepNo(stepNo[m]);
				dnd.setNoOfSamples(sam[m]);
				id = stepId[m];
				childDelete = request.getParameter("Check" + id);
				if (ss.equals(childDelete)) {
					flag = inspOperService.deleteInspOperStep(id);
				} else {
					inspOperationStep.add(dnd);
				}
			}
		}

		try {

			inspUpdate.setInspOprStep(inspOperationStep);
			flag = inspOperService.updateInspOperation(inspUpdate);

			if (flag == true) {
				inspUpadted = "Inspection  Operation Data Updated Successfully";

			} else {
				inspUpadted = "Inspection  Operation Data Updation Failed";
				return "redirect:inspOperationHome.mnt?updateInspFail="
						+ inspUpadted + "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:inspOperationHome.mnt?updateInspSus=" + inspUpadted
				+ "";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "inspOperation";

		Map<String, String> map = null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
