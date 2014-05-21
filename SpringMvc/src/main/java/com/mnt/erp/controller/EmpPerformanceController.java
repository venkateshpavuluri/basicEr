package com.mnt.erp.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mnt.erp.bean.EmpPerformance;
import com.mnt.erp.bean.EmpPerformanceKPI;
import com.mnt.erp.bean.Employee;
import com.mnt.erp.bean.KPIBean;
import com.mnt.erp.bean.Status;
import com.mnt.erp.service.EmpPerformanceService;
import com.mnt.erp.service.XmlLabelsService;

@Controller
public class EmpPerformanceController {

	@Autowired EmpPerformanceService empPerformanceService;
	@Autowired
	XmlLabelsService xmlService;
	@RequestMapping(value = "/EmpPerformance", method = RequestMethod.GET)
	public ModelAndView getEmpPerformance(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		return new ModelAndView("EmpPerformanceHome", "EmpPerformance",
				new EmpPerformance());
	}
	/* To Get Equipment Id Values */
	@ModelAttribute("employee")
	public Map<Integer, String> equipmentIdGet() {
		List<Object[]> listvalues = null;
		Iterator<Object[]> iterator = null;
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			listvalues = empPerformanceService.selectEmployee();
			iterator = listvalues.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((Integer) objects[0], (String) objects[1]);
			}

		} catch (Exception e) {

		}
		return map;
	}
	
	/* To Get Equipment Id Values */
	@ModelAttribute("status")
	public Map<Integer, String> maintenanceCategoryIdGet() {
		List<Object[]> listvalues = null;
		Iterator<Object[]> iterator = null;
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			listvalues = empPerformanceService.selectStatus();
			iterator = listvalues.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((Integer) objects[0], (String) objects[1]);
			}

		} catch (Exception e) {

		}
		return map;
	}
	
	/* To Get Equipment Id Values */
	@ModelAttribute("kpi")
	public Map<Integer, String> maintenanceTypeIdGet() {
		List<Object[]> listvalues = null;
		Iterator<Object[]> iterator = null;
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			listvalues = empPerformanceService.selectKpi();
			iterator = listvalues.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((Integer) objects[0], (String) objects[1]);
			}

		} catch (Exception e) {

		}
		return map;
	}
	
	
	@RequestMapping(value = "/empPerformanceAdd", method = RequestMethod.POST)
	public String saveEmpPerformanceAdd(
			@ModelAttribute("EmpPerformance") EmpPerformance EmpPerformanceAdd,
			HttpServletRequest request, SessionStatus status,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String msg = null;
		String res=null;
		List<EmpPerformanceKPI> EmpPerformanceKPI = null;
		EmpPerformanceKPI EmpPerformanceSchCa=null;
		/*String id=EmpPerformanceAdd.getEquipmentId();
		Long s=empPerformanceService.checkEmpPerformance(id);
		
		if(s==0){*/
			try { 
				
				String rating = EmpPerformanceAdd.getRating();
				if(rating!=null){
				
				List<String> ratinglist = Arrays.asList(rating.split(","));
				Object[] ratings = ratinglist.toArray();
				
				
				String kpi = EmpPerformanceAdd.getkPIId();
				List<String> kpiList = Arrays.asList(kpi.split(","));
				Object[] kpiListids = kpiList.toArray();
				String comments = EmpPerformanceAdd.getComment();
				List<String> commentslist = Arrays.asList(comments.split(","));
				Object[] commet = commentslist.toArray();
				String eval = EmpPerformanceAdd.getEvaluationBy();
				List<String> evalList = Arrays.asList(eval.split(","));
				Object[] evalListids = evalList.toArray();
			
				
				EmpPerformanceKPI = new ArrayList<EmpPerformanceKPI>();
				for (int i = 0; i < ratings.length; i++) {
				
					EmpPerformanceSchCa = new EmpPerformanceKPI();
					EmpPerformanceSchCa.setkPIId(kpiListids[i].toString());
					EmpPerformanceSchCa.setRating(ratings[i].toString());
					EmpPerformanceSchCa.setComment(commet[i].toString());
					EmpPerformanceSchCa.setEvaluationBy(evalListids[i].toString());
					EmpPerformanceKPI.add(EmpPerformanceSchCa);
			
		
				}
				EmpPerformanceAdd.setEmpPerformanceKPI(EmpPerformanceKPI);
		
				} 
			
				msg=empPerformanceService.saveEmpPerformanceDetails(EmpPerformanceAdd);
				
				if(msg=="S"){
					res = "redirect:EmpPerformance.mnt?list=" + "success" + "";
				}
				else{
					res = "redirect:EmpPerformance.mnt?listwar=" + "fail" + "";
				}
				
				}
				
			 catch (Exception e) {
				//res = "redirect:EmpPerformance.mnt?listwar=" + "fail" + "";
				e.printStackTrace();
				
			}
			return res;
		
			
	/*}
	else{
		EmpPerformanceAdd.setAid(1);
		
		request.setAttribute("addPrivMainDuplicate",
				"Equipment Already Exists Please try some other Equipment");
		return "EmpPerformanceHome";
	}
*/
	}
	
	
	@RequestMapping(value = "/EmpPerformanceSearch", method = RequestMethod.GET)
	public ModelAndView EmpPerformanceSearch(
			@ModelAttribute("EmpPerformance") EmpPerformance EmpPerformanceSearch,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		List<Object[]> list = null;
		List<EmpPerformance> empPerSearch = null;

		try {
		
			String dbField = EmpPerformanceSearch.getXmlLabel();
			String operation = EmpPerformanceSearch.getOperations();
			String basicSearchId = EmpPerformanceSearch.getBasicSearchId();

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
				list = empPerformanceService.searchEmpPerformance();
				System.out.println("the list:"+list.size());

			} else {
				list = empPerformanceService.basicSearchEmpPerformance(dbField, operation, basicSearchId);
						
			}

			Iterator<Object[]> iterator = list.iterator();
			empPerSearch=new ArrayList<EmpPerformance>();
			while (iterator.hasNext()) {
				EmpPerformance empPer = new EmpPerformance();
				Object[] obj = (Object[]) iterator.next();
				empPer.setPerformanceReviewId((Integer) obj[0]);
				Employee emp=(Employee)obj[1];
				empPer.setEmpName(emp.getfName());
				empPer.setPeriodFrom((String) obj[2]);
				empPer.setPeriodTo((String) obj[3]);
				Status status=(Status)obj[4];
				empPer.setStatusName(status.getStatus());
				empPerSearch.add(empPer);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("empPerValues", "empPerValues");


		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("EmpPerformanceHome");
	 modelAndView.addObject("EmpPerf",empPerSearch);
		return modelAndView;

	}
	@RequestMapping(value = "/EmpPerformanceEdit", method = RequestMethod.GET)
	public String editPrevMain(
			@ModelAttribute("EmpPerformance") EmpPerformance EmpPerformanceEdit,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		List<EmpPerformance> preMainEdit = new ArrayList<EmpPerformance>();
		
		List<EmpPerformanceKPI> resourceReqDetailList = new ArrayList<EmpPerformanceKPI>();
		int id = Integer.parseInt(request.getParameter("EmpPerformanceId"));

		try {
			List<EmpPerformance> list = empPerformanceService.searchEmpPerformanceWithId(id);
			Iterator<EmpPerformance> iter = list.iterator();
			while (iter.hasNext()) {
				 Object pobject = iter.next();
				 EmpPerformance resReq = (EmpPerformance) pobject;
				
			
				 EmpPerformanceEdit.setPerformanceReviewIdEdit(resReq.getPerformanceReviewId());
				 EmpPerformanceEdit.setEmployeeId(resReq.getEmployeeId());
				 EmpPerformanceEdit.setPeriodFrom(resReq.getPeriodFrom());
				 EmpPerformanceEdit.setPeriodTo(resReq.getPeriodTo());
				 EmpPerformanceEdit.setStatusId(resReq.getStatusId());
				 
				
				List<EmpPerformanceKPI> listEdit = resReq.getEmpPerformanceKPI();
				Iterator<EmpPerformanceKPI> iterate = listEdit.iterator();
				while (iterate.hasNext()) {
					Object object2 = iterate.next();
					EmpPerformanceKPI resRdit = (EmpPerformanceKPI) object2;
					EmpPerformanceKPI resReEdit = new EmpPerformanceKPI();
					resReEdit.setPerformanceReviewKPIIdEdit(resRdit.getPerformanceReviewKPIId());
					resReEdit.setkPIIdEdit(resRdit.getkPIId());
					KPIBean kpibean=resRdit.getKpibean();
					resReEdit.setKpiName(kpibean.getKPI());
					resReEdit.setRatingEdit(resRdit.getRating());
					resReEdit.setCommentEdit(resRdit.getComment());
					resReEdit.setEvaluationByEdit(resRdit.getEvaluationBy());
					Employee emp=resRdit.getEmployee();
					resReEdit.setEmpName(emp.getfName());
					
					resourceReqDetailList.add(resReEdit);
				}
				EmpPerformanceEdit.setEmpPerformanceKPI(resourceReqDetailList);
				preMainEdit.add(EmpPerformanceEdit);

			}
		
			
			request.setAttribute("preMainEdit", preMainEdit);
			
			request.setAttribute("preMainCatList", resourceReqDetailList);
			
			}
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		return "EmpPerformanceHome";

	}
	@RequestMapping(value = "/EmpPerformanceUpdate", method = RequestMethod.POST)
	public String EmpPerformanceUpdate(
			@ModelAttribute("EmpPerformance") EmpPerformance EmpPerformanceUpdate,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		List<EmpPerformanceKPI> resourceReqDetail = null;
		EmpPerformanceKPI resoReqDetail=null;
		String msg=null;
		/*EmpPerformanceUpdate.setEmpPerformanceSchId(EmpPerformanceUpdate.getEmpPerformanceSchIdEdit());
		EmpPerformanceUpdate.setEmpPerformanceSchNo(EmpPerformanceUpdate.getEmpPerformanceSchNoEdit());
		EmpPerformanceUpdate.setEquipmentId(EmpPerformanceUpdate.getEquipmentIdEdit());
		int id=EmpPerformanceUpdate.getEmpPerformanceSchId();
	String equipmentId=EmpPerformanceUpdate.getEquipmentIdEdit();
	Long s=empPerformanceService.updateCheckEmpPerformance(equipmentId, id);

		if(s==0){*/
		EmpPerformanceUpdate.setPerformanceReviewId(EmpPerformanceUpdate.getPerformanceReviewIdEdit());
				try{
		
		String rating = EmpPerformanceUpdate.getRatingEdit();

		if(rating!=null){
		
		
	
		List<String> ratinglist = Arrays.asList(rating.split(","));
		Object[] ratings = ratinglist.toArray();
		
					
		String comment = EmpPerformanceUpdate.getCommentEdit();
		
		List<String>commentlist = Arrays.asList(comment.split(","));
		Object[] comments = commentlist.toArray();
		String kpis = EmpPerformanceUpdate.getkPIIdEdit();
		
		List<String> kpiList = Arrays.asList(kpis.split(","));
		Object[] kpiIds = kpiList.toArray();
	
String evalBys = EmpPerformanceUpdate.getEvaluationByEdit();
		
		List<String> evalBysList = Arrays.asList(evalBys.split(","));
		Object[] evalBysListIds = evalBysList.toArray();
		int[] empPerUpdate = EmpPerformanceUpdate.getEmpPerEditt();
		System.out.println("the empperediit:"+empPerUpdate.length);
	
		resourceReqDetail = new ArrayList<EmpPerformanceKPI>();
		for (int i = 0; i < empPerUpdate.length; i++) {
		
			resoReqDetail = new EmpPerformanceKPI();
			
			resoReqDetail.setPerformanceReviewKPIId(empPerUpdate[i]);
			System.out.println("the child id is:"+resoReqDetail.getPerformanceReviewKPIId());
			resoReqDetail.setRating(ratings[i].toString());
			
			resoReqDetail.setkPIId(kpiIds[i].toString());
			
			resoReqDetail.setComment(comments[i].toString());
			
			resoReqDetail.setEvaluationBy(evalBysListIds[i].toString());
			int resDelId = empPerUpdate[i];
		
			String check = "1", check1 = "0";
			String egCheck = request.getParameter(resDelId + "Check");
			
		if (check.equals(egCheck)) {
				System.out.println("the child id is:"+resDelId);
				empPerformanceService.deleteEmpPerformanceDetail(resDelId);
				
			}

			if (check1.equals(egCheck) || egCheck == null) {
				
				resourceReqDetail.add(resoReqDetail);

			}

		}
			
		
		//resourceReqDetail.add(resoReqDetail);

		EmpPerformanceUpdate.setEmpPerformanceKPI(resourceReqDetail);


		msg=empPerformanceService.updateEmpPerformance(EmpPerformanceUpdate);
		
		if(msg=="S"){
			request.setAttribute("empPerformanceUpdate", "EmpPerformance has been updated");
		}
		else{
			request.setAttribute("empPerformanceUpdateError", "EmpPerformance has not been updated");
		}
		}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		/*else{
		
		EmpPerformanceUpdate.setEmpPerformanceSchIdEdit(1);
		request.setAttribute("addPreMainEditDuplicate","Equipment Already Exists Please try some other Equipment");
		request.setAttribute("preMainEdit", "preMainEdit");
		return "EmpPerformanceHome";
		}*/
		return "EmpPerformanceHome";
	}

	@RequestMapping(value = "/EmpPerformanceDelete", method = RequestMethod.GET)
	public ModelAndView EmpPerformanceDelete(
			@ModelAttribute("EmpPerformance") EmpPerformance EmpPerformanceDelete,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("EmpPerformanceId"));
		try {

			String msg = empPerformanceService.deleteEmpPerformance(id);
			if (msg == "S") {

				request.setAttribute("empPerformanceDelete","EmpPerformance has been deleted");
				
				
			} else {

				request.setAttribute("empPerformanceError", "EmpPerformance has not been deleted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("EmpPerformanceHome", "EmpPerformance",
				new EmpPerformance());
	}
	
	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "EmpPerformanceId";

		Map<String, String> map =null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	 
}
