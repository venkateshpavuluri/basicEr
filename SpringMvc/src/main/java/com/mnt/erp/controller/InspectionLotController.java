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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mnt.erp.bean.GoodsReceiptLine;
import com.mnt.erp.bean.InspectionLotBean;
import com.mnt.erp.bean.InspectionType;
import com.mnt.erp.bean.InsplotOrigin;
import com.mnt.erp.bean.Material;
import com.mnt.erp.bean.Plant;
import com.mnt.erp.bean.Status;
import com.mnt.erp.bean.StockTransferBean;
import com.mnt.erp.bean.Uom;
import com.mnt.erp.service.AuditLogService;
import com.mnt.erp.service.DateConversionService;
import com.mnt.erp.service.InspectionLotService;
import com.mnt.erp.service.MenuService;
import com.mnt.erp.service.PopulateService;
import com.mnt.erp.service.XmlLabelsService;

/**
 * @author Naresh
 * @version 1.0 07-01-2014
 */

@Controller
public class InspectionLotController {
	private static final Logger log = Logger
			.getLogger(InspectionLotController.class);
	@Autowired
	XmlLabelsService xmlService;
	@Autowired
	InspectionLotService inspLotService;
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

	@RequestMapping(value = "/inspLotHome", method = RequestMethod.GET)
	public ModelAndView inspLotHome(
			@ModelAttribute("inspLotCmd") InspectionLotBean inspLotBean,
			SessionStatus status, HttpServletResponse response,
			HttpServletRequest request) {
		response.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		List<String> list = menuService.getPrivilige("inspLotHome.mnt", session
				.getAttribute("userId").toString());
		session.setAttribute("privilegeList", list);
		return new ModelAndView("inspLotHome", "inspLotCmd", inspLotBean);
	}

	@RequestMapping(value = "/getMaterialIds", method = RequestMethod.POST)
	public @ResponseBody
	Map<Integer, String> populatMaterialIds(HttpServletRequest request) {
		String refNo = request.getParameter("refNo");
		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select m.material_Id,m.materialName from GoodsReceiptLine grl,Material m where grl.goodsReceipt_Id='"
							+ refNo + "' and m.material_Id=grl.material_Id");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("materialSelect")
	public Map<Integer, String> populatMatIds() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select m.material_Id,m.materialName from Material m");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("uomSelect")
	public Map<Integer, String> populatUomIds() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select m.uom_Id,m.uom from Uom m");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("PlantSelect")
	public Map<Integer, String> populatPlantIds() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select p.plantId,p.plantName from Plant p");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("inspTypeSelect")
	public Map<Integer, String> populatinspTypeIds() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select p.inspectionTypeId,p.inspectionType from InspectionType p");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("InsplotOriginSelect")
	public Map<Integer, String> populatInsplotOriginIds() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select p.insplotoriginId,p.insplotorigin from InsplotOrigin p");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("statusSelect")
	public Map<Integer, String> populatStatusIds() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select s.statusId,s.status from Status s");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/getRefNos", method = RequestMethod.POST)
	public @ResponseBody Map<Integer, String> populateRefNo(HttpServletRequest request) {
		String lotOriginId = request.getParameter("lotOriginId");
		String origin = null;
		if (lotOriginId.equals("1")) {
			origin = "Purchase Order";

		} else if (lotOriginId.equals("2")) {
			origin = "GRWOPO";

		} else if (lotOriginId.equals("3")) {
			origin = "PRD";
		} else {
			origin = "ST";
		}
		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select distinct gr.goodsReceipt_Id,gr.goodsReceiptTypeNum from GoodsReceipt gr,GoodsReceiptType grt where grt.goodsReceiptTypeId=gr.goodsReceiptType_Id and grt.goodsReceiptType='"
							+ origin + "'");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("refNos")
	public Map<Integer, String> populateRefNos() {

		Map<Integer, String> map = null;
		try {
			map = populateService
					.populateSelectBox("select distinct gr.goodsReceipt_Id,gr.goodsReceiptTypeNum from GoodsReceipt gr  ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/getBatchNo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> populatBatchNos(HttpServletRequest request) {
		String matId = request.getParameter("materialId");
		Map<String, String> map = null;
		try {
			map = populateService
					.populatePopUp("select distinct grl.batchNo,grl.batchNo from GoodsReceiptLine grl where grl.material_Id='"
							+ matId + "'");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@ModelAttribute("selectBatchNos")
	public Map<String, String> populatBNos() {

		Map<String, String> map = null;
		try {
			map = populateService
					.populatePopUp("select distinct grl.batchNo,grl.batchNo from GoodsReceiptLine grl");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/getGRLQty", method = RequestMethod.POST)
	public @ResponseBody
	float forAvalQty(HttpServletRequest request, HttpServletResponse response,
			StockTransferBean stBean) {
		response.setCharacterEncoding("UTF-8");
		int matId=0;
		if(!request.getParameter("materialId").equals("")){
		 matId = Integer.parseInt(request.getParameter("materialId"));
		}
		String bId = request.getParameter("batchNo");
		float avalQty = 0;
		List<GoodsReceiptLine> grlBean = null;
		try {
			grlBean = inspLotService.getAvlQty(matId, bId);
			if (grlBean != null) {
				for (GoodsReceiptLine ms : grlBean) {
					avalQty = ms.getQtyAccepted();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return avalQty;

	}

	@RequestMapping(value = "/checkLotAddDuplicate", method = RequestMethod.POST)
	public @ResponseBody
	String checkLotAddDuplicate(HttpServletRequest request,
			HttpServletResponse response, InspectionLotBean sqdupBean) {
		response.setCharacterEncoding("UTF-8");
		String inspNo = request.getParameter("inspLotNo");
		Long checkCustName = inspLotService.checkInspectionLotCout(inspNo);
		if (checkCustName != 0) {

			message = "Warning ! Inspection Lot No is Already exists. Please try some other name";
		} else {
			message = "";
		}
		return message;
	}

	@RequestMapping(value = "/checkLotUpdateDuplicate", method = RequestMethod.POST)
	public @ResponseBody
	String checkLotUpdateDuplicate(HttpServletRequest request,
			HttpServletResponse response, InspectionLotBean dupBean) {
		response.setCharacterEncoding("UTF-8");
		String message = null;
		String inspNo = request.getParameter("inspLotNo");
		int inspId = Integer.parseInt(request.getParameter("inspLotNoId"));
		long checkCustName = inspLotService.updateCheckInspectionLot(inspNo,
				inspId);

		if (checkCustName != 0) {

			message = "Warning ! Inspection Lot No is Already exists. Please try some other name";
		} else {
			message = "";
		}

		return message;
	}

	@RequestMapping(value = "/inspLotAdd", method = RequestMethod.POST)
	public String saveInspLot(
			@ModelAttribute("inspLotCmd") InspectionLotBean inspBean,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String inspSave = null;
		try {
			InspectionLotBean addBean = (InspectionLotBean) inspBean;
			flag = inspLotService.saveInspectionLotDetails(addBean);

			if (flag == true) {
				session = request.getSession(false);
				Date date = new Date();
				String modifiedDate = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(date);
				auditLogService.setAuditLogSave(session.getAttribute("userId")
						.toString(), "A", "Inspection Lot", "ROW", String
						.valueOf(addBean.getInspLotNoId()), "1", modifiedDate,
						session.getAttribute("userName").toString());
				inspSave = "Inspection Lot Data Saved Successfully";

			} else {
				inspSave = "Inspection Lot Data Insertion Failures";
				return "redirect:inspLotHome.mnt?addLotFail=" + inspSave + "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:inspLotHome.mnt?addLotsus=" + inspSave + "";

	}

	@RequestMapping(value = "/inspLotSearch", method = RequestMethod.GET)
	public String searchInspLot(
			@ModelAttribute("inspLotCmd") InspectionLotBean inspLotBeanSearch,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");
		List<InspectionLotBean> LOTList = new ArrayList<InspectionLotBean>();
		try {

			String dbField = inspLotBeanSearch.getXmlLabel();
			String operation = inspLotBeanSearch.getOperations();
			String basicSearchId = inspLotBeanSearch.getBasicSearchId();

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
				list = inspLotService.searchInspectionLot();

			} else {

				list = inspLotService.basicSearchInspectionLot(dbField,
						operation, basicSearchId);
			}
			itr = list.iterator();
			while (itr.hasNext()) {
				objects = (Object[]) itr.next();
				InspectionLotBean dnb = new InspectionLotBean();
				dnb.setInspLotNoId((Integer) objects[0]);
				dnb.setInspLotNo((String) objects[1]);
				dnb.setRefNo((String) objects[2]);
				dnb.setQuantity((String) objects[3]);
				dnb.setDesc((String) objects[4]);
				Plant p = ((Plant) objects[5]);
				dnb.setPlantId(p.getPlantName());
				Material m = ((Material) objects[6]);
				dnb.setMaterialId(m.getMaterialName());
				Uom u = ((Uom) objects[7]);
				dnb.setUomId(u.getUom());
				InspectionType insp = ((InspectionType) objects[8]);
				dnb.setInspTypeId(insp.getInspectionType());
				InsplotOrigin inlot = ((InsplotOrigin) objects[9]);
				dnb.setInspLotOriginId(inlot.getInsplotorigin());
				Status st = ((Status) objects[10]);
				dnb.setStatusId(st.getStatus());
				dnb.setBatchNo((String) objects[11]);

				LOTList.add(dnb);
			}
			request.setAttribute("LOTList", LOTList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inspLotHome";

	}

	@RequestMapping(value = "/inspLotDelete", method = RequestMethod.GET)
	public String inspLotDelete(
			@ModelAttribute("inspLotCmd") InspectionLotBean inspLotBeanDelete,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String inspLotDelete = null;
		int inspId = Integer.parseInt(request.getParameter("inspLotNoId"));
		try {
			flag = inspLotService.deleteInspectionLot(inspId);
			if (flag == true) {
				Date date = new Date();
				session = request.getSession(false);
				String modifiedDate = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(date);
				auditLogService.setAuditLogSave(session.getAttribute("userId")
						.toString(), "D", "Inspection Lot", "ROW", String
						.valueOf(inspId), "1", modifiedDate, session
						.getAttribute("userName").toString());
				inspLotDelete = "Inspection Lot Deleted Successfully";

			} else {
				inspLotDelete = "Inspection Lot Deletion Failed due to Conatraint Violation";
				return "redirect:inspLotHome.mnt?DeleteLotFail="
						+ inspLotDelete + "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:inspLotHome.mnt?DeleteLotsus=" + inspLotDelete + "";

	}

	@RequestMapping(value = "/inspLotEdit", method = RequestMethod.GET)
	public String inspLotEdit(
			@ModelAttribute("inspLotCmd") InspectionLotBean salesEdit,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("inspLotNoId"));
		List<InspectionLotBean> inspLotBean = new ArrayList<InspectionLotBean>();
		InspectionLotBean isp = null;
		try {
			obj = inspLotService.searchInspectionLotWithId(id);
			Iterator<Object> iterator = obj.iterator();
			while (iterator.hasNext()) {
				Object obj = iterator.next();
				isp = (InspectionLotBean) obj;
				inspLotBean.add(isp);
			}
			model.addAttribute("inspLotCmd", isp);
			request.setAttribute("inspLotEdit", inspLotBean);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			obj = null;
		}
		return "inspLotHome";

	}

	@RequestMapping(value = "/inspLotUpdate", method = RequestMethod.POST)
	public String inspLotUpdate(
			@ModelAttribute("inspLotCmd") InspectionLotBean inspUpdate,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String inspUpadted = null;

		try {
			InspectionLotBean upBean = (InspectionLotBean) inspUpdate;
			flag = inspLotService.updateInspectionLot(upBean);

			if (flag == true) {
				inspUpadted = "Inspection Lot Data Updated Successfully";

			} else {
				inspUpadted = "Inspection Lot Data Updation Failed";
				return "redirect:inspLotHome.mnt?updateLotFail=" + inspUpadted
						+ "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:inspLotHome.mnt?updateLotssus=" + inspUpadted + "";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "inspLotId";

		Map<String, String> map = null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
