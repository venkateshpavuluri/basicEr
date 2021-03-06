package com.mnt.erp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mnt.erp.bean.Designation;
import com.mnt.erp.bean.Organization;
import com.mnt.erp.bean.OrganizationChart;

import com.mnt.erp.service.AuditLogService;
import com.mnt.erp.service.MenuService;
import com.mnt.erp.service.OrganizationChartService;
import com.mnt.erp.service.PopulateService;
import com.mnt.erp.service.XmlLabelsService;


@Controller
public class OrganizationChartController {
	
	OrganizationChartService orgChartService;
	@Autowired
	XmlLabelsService xmlService;
	   @Autowired
	    PopulateService populateService;

	@Autowired
	MenuService menuService;
	
	@Autowired
	AuditLogService auditLogService;
@RequestMapping(value="/OrganizationChart",method = RequestMethod.GET)
public ModelAndView getOrganizationChart(HttpServletRequest request,HttpServletResponse response){
	return new ModelAndView("OrganizationChartHome","OrganizationChartCommand",new OrganizationChart());
}
@RequestMapping(value = "/OrganizationChartadd", method = RequestMethod.POST)
public String saveOrganizationChart(
		@ModelAttribute("OrganizationChartCommand") OrganizationChart OrganizationChart,
		HttpServletRequest request, Model model,
		HttpServletResponse response) {

	List<String> list = new ArrayList<String>();
	String msg = null;
	String OrganizationChartAdd = null;

	String res = null;
	HttpSession session=null;
	int OrganizationChartList1 = 0;
	String agreementTyp = OrganizationChart.getOrgId();
	OrganizationChartList1 = orgChartService.OrganizationChartDuplicate(agreementTyp);
   
	if (OrganizationChartList1 == 0) {

		try {
			session=request.getSession(false);
			msg = orgChartService.saveOrganizationChart(OrganizationChart,session.getAttribute("userId").toString(),session.getAttribute("userName").toString());

			if (msg.equals("S")) {
				OrganizationChartAdd = "Agreement Type has been saved successfully";
				list.add("2");
				res = "redirect:OrganizationChart.mnt?success="
						+ OrganizationChartAdd + "&list=" + list + "";
			} else {
				OrganizationChartAdd = "Agreement Type is not saved properly";
				list.add("2");
				res = "redirect:OrganizationChart.mnt?warning="
						+ OrganizationChartAdd + "&listwar=" + list + "";
			}

		} catch (Exception e) {
			
			OrganizationChartAdd = "Agreement Type is not saved properly";
			list.add("2");
			res = "redirect:OrganizationChart.mnt?warning="
					+ OrganizationChartAdd + "&listwar=" + list + "";
			e.printStackTrace();
		}

	

	}

	else {

		OrganizationChart.setAid(1);

		request.setAttribute("addOrganizationChartDuplicate",
				"Agreement Type Already Exists Please try some other name");

		return "OrganizationChartHome";

	}
	return res;
}

@RequestMapping(value = "/OrganizationChartSearch", method = RequestMethod.GET)
public ModelAndView searchOrganizationChart(
		@ModelAttribute("OrganizationChartCommand") OrganizationChart OrganizationChart,
		BindingResult bindingResult, HttpServletRequest request,
		HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	List<OrganizationChart> agreementsType = null;
	try {
		int id = OrganizationChart.getOrganizationChartId();
		String dbField = OrganizationChart.getXmlLabel();
		String operation = OrganizationChart.getOperations();
		String basicSearchId = OrganizationChart.getBasicSearchId();

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
			List<Object[]> list = orgChartService.searchOrganizationChart(id);
			agreementsType = new ArrayList<OrganizationChart>();
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				OrganizationChart at = new OrganizationChart();
				at.setOrganizationChartId((Integer) objects[0]);
				
				Organization org=(Organization)objects[1];
				at.setOrgName(org.getOrgName());
			
				Designation des=(Designation)objects[2];
				at.setDesignationName(des.getDesignation());
				agreementsType.add(at);
			}

		} else {

			List<Object[]> list = orgChartService.basicSearchOrganizationChart(
					dbField, operation, basicSearchId);
			agreementsType = new ArrayList<OrganizationChart>();
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				OrganizationChart agt = new OrganizationChart();
				agt.setOrganizationChartId((Integer) objects[0]);
			
				Organization org=(Organization)objects[1];
				agt.setOrgName(org.getOrgName());
				Designation des=(Designation)objects[2];
				agt.setDesignationName(des.getDesignation());
				
				agreementsType.add(agt);

			}

		}
	}

	catch (Exception e) {
		e.printStackTrace();
	}
	request.setAttribute("agtvalues","agtvalues" );

	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("OrganizationChartHome");
	modelAndView.addObject("OrganizationChart", agreementsType);
	return modelAndView;
}

@RequestMapping(value = "/OrganizationChartEditHome", method = RequestMethod.GET)
@org.springframework.context.annotation.Scope("request")
public String editOrganizationChart(
		@ModelAttribute("OrganizationChartCommand") OrganizationChart OrganizationChart,
		BindingResult result, HttpServletRequest request,
		HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	int id = Integer.parseInt(request.getParameter("OrganizationChartEdit"));
	List<Object[]> list = null;
	Object[] objects = null;
	try {
		list = orgChartService.editOrganizationChartWithId(id);
		Iterator<Object[]> iterator = list.iterator();
		while (iterator.hasNext()) {
			objects = (Object[]) iterator.next();
			OrganizationChart.setOrganizationChartId((Integer) objects[0]);
			OrganizationChart.setOrgId((String) objects[1]);
			OrganizationChart.setDesignationId((String) objects[2]);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	request.setAttribute("OrganizationChartvalues", "OrganizationChartvalues");
	return "OrganizationChartHome";

}

@RequestMapping(value = "/OrganizationChartUpdate", method = RequestMethod.POST)
public String updateOrganizationChart(
		@ModelAttribute("OrganizationChartCommand") OrganizationChart OrganizationChart,
		HttpServletRequest request, HttpServletResponse response,
		Model model) {
	response.setCharacterEncoding("UTF-8");

	int OrganizationChartEditList = 0;

	String agreementTyp = OrganizationChart.getOrgId();
	int id = OrganizationChart.getOrganizationChartId();
	OrganizationChartEditList = orgChartService.OrganizationChartEditDuplicate(
			agreementTyp, id);
	if (OrganizationChartEditList == 0) {

		try {
			String msg = orgChartService.updateOrganizationChart(OrganizationChart);

			if (msg == "S") {

				request.setAttribute("OrganizationChartUpdate",
						"Agreement Type has been updated successfully");

			}

			else {

				request.setAttribute("OrganizationChartUpdateError",
						"Agreement Type has not been updated");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("OrganizationChartCommand", new OrganizationChart());

		return "OrganizationChartHome";

	} else {

		request.setAttribute("OrganizationChartvalues", "OrganizationChartvalues");
		request.setAttribute("editOrganizationChartDuplicate",
				"Agreement Type Already Exists Please try some other name");

		return "OrganizationChartHome";
	}

}

@RequestMapping(value = "/OrganizationChartDelete", method = RequestMethod.GET)
public ModelAndView deleteOrganizationChart(HttpServletRequest request,
		HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	HttpSession session=null;
	int id = 0;

	try {
		id = Integer.parseInt(request.getParameter("OrganizationChartDelete"));
		
		String msg = orgChartService.OrganizationChartDelete(id);
		
		if (msg.equals("S")) {
			session=request.getSession(false);
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			auditLogService.setAuditLogSave(session.getAttribute("userId").toString(),"D","Agreement Type","ROW" ,String.valueOf(id),"1",modifiedDate,session.getAttribute("userName").toString());
			request.setAttribute("OrganizationChartdelete",
					"Agreement Type has been deleted successfully");
		} else {
			request.setAttribute("OrganizationChartdeleteError",
					"Agreement Type has not been deleted");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new ModelAndView("OrganizationChartHome", "OrganizationChartCommand",
			new OrganizationChart());
}

@ModelAttribute("xmlItems")
public Map<String, String> populatLabelDetails() {
	String name = "OrganizationChart";

	Map<String, String> map =null;

	try {
		map = xmlService.populateXmlLabels(name);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return map;
}
@ModelAttribute("orgnization")
public Map<Integer, String> populateOrgnization() {
List<Object[]> listvalues = null;
Map<Integer, String> map = null;
Iterator<Object[]> iterator = null;
try {
    map = new HashMap<Integer, String>();
    listvalues = populateService
	    .poPulate("select o.orgId,o.orgName from Organization o");

    iterator = listvalues.iterator();
    while (iterator.hasNext()) {
	Object[] objects = (Object[]) iterator.next();
	// list.add((String)objects[1]);
	map.put((Integer) objects[0], (String) objects[1]);

    }

} catch (Exception e) {
    e.printStackTrace();
}

return map;
}
@ModelAttribute("designation")
public Map<Integer, String> populateDesignation() {
List<Object[]> listvalues = null;
Map<Integer, String> map = null;
Iterator<Object[]> iterator = null;
try {
    map = new HashMap<Integer, String>();
    listvalues = populateService
	    .poPulate("select d.designationId,d.designation from Designation d");

    iterator = listvalues.iterator();
    while (iterator.hasNext()) {
	Object[] objects = (Object[]) iterator.next();
	// list.add((String)objects[1]);
	map.put((Integer) objects[0], (String) objects[1]);

    }

} catch (Exception e) {
    e.printStackTrace();
}

return map;
}
}
