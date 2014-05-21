/**
 * @Copyright MNTSOFT
 * 
 */
package com.mnt.erp.controller;

/**
 * @author pvenkateswarlu
 *@version 1.0 15-09-2013
 * 
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mnt.erp.bean.Material;
import com.mnt.erp.bean.MaterialCategory;
import com.mnt.erp.bean.MaterialDisplay;
import com.mnt.erp.bean.MaterialType;
import com.mnt.erp.bean.TaxCategory;
import com.mnt.erp.bean.Uom;
import com.mnt.erp.service.AuditLogService;
import com.mnt.erp.service.MaterialCategoryService;
import com.mnt.erp.service.MaterialService;
import com.mnt.erp.service.MaterialTypeService;
import com.mnt.erp.service.MenuService;
import com.mnt.erp.service.PopulateService;
import com.mnt.erp.service.TaxCategoryService;
import com.mnt.erp.service.UomService;
import com.mnt.erp.service.XmlLabelsService;

@Controller
@Scope("request")
public class MaterialController {

	/*
	 * @Autowired MaterialValidator materialValidator;
	 */
	@Autowired
	PopulateService populateService;
	@Autowired
	MaterialService materialService;
	@Autowired
	MaterialCategoryService categoryService;
	@Autowired
	TaxCategoryService taxCategoryService;
	@Autowired
	MaterialTypeService materialTypeService;
	@Autowired
	UomService uomService;
	@Autowired
	XmlLabelsService xmlService;
	@Autowired
	MenuService menuService;

	@Autowired
	AuditLogService auditLogService;

	HttpSession session;

	@RequestMapping(value = "/materialHome", method = RequestMethod.GET)
	@RequestScoped
	public String getMaterial(@ModelAttribute Material material1,
			SessionStatus status, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			response.setCharacterEncoding("UTF-8");

			model.addAttribute("materialAdd", material1);

			HttpSession session = request.getSession(false);
			List<String> list = menuService.getPrivilige("materialHome.mnt",
					session.getAttribute("userId").toString());
			session.setAttribute("privilegeList", list);

			/*
			 * return new ModelAndView("materialHome","materialAdda",
			 * material1);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "materialHome";
	}

	@ModelAttribute("materialCategory")
	public Map<Integer, String> populateMaterialCategory() {
		List<Object[]> listvalues = null;
		Map<Integer, String> map = null;
		Iterator<Object[]> iterator = null;
		try {
			map = new HashMap<Integer, String>();
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			listvalues = populateService
					.poPulate("select m.materialCategoryId,m.materialCategory from MaterialCategory m");

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

	@ModelAttribute("taxCategory")
	public Map<Integer, String> populateTaxCategory() {

		Map<Integer, String> map = null;
		List<Object[]> listvalues = null;

		Iterator<Object[]> iterator = null;
		try {
			map = new HashMap<Integer, String>();
			listvalues = taxCategoryService.selectTaxCtegory();
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

	@ModelAttribute("materialType")
	public Map<Integer, String> populateMaterialType() {
		List<Object[]> listvalues = null;
		Iterator<Object[]> iterator = null;

		Map<Integer, String> map = null;
		try {

			listvalues = materialTypeService.selectMaterialType();
			map = new HashMap<Integer, String>();
			iterator = listvalues.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((Integer) objects[0], (String) objects[1]);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@ModelAttribute("uom")
	public Map<Integer, String> populateUom() {

		List<Object[]> listvalues = null;
		Iterator<Object[]> iterator = null;
		Map<Integer, String> map = null;
		try {

			listvalues = uomService.selectUomDetails();
			map = new HashMap<Integer, String>();
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

	@ModelAttribute("materialSearchIds")
	public Map<Integer, String> populatematerialSearchIds() {
		List<Object[]> listvalues = null;
		Iterator<Object[]> iterator = null;
		Map<Integer, String> map = null;
		try {
			listvalues = materialService.selectMaterialIds();
			map = new HashMap<Integer, String>();
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

	/*
	 * @InitBinder(value = "materialAdd") protected void
	 * initBinder(WebDataBinder binder) {
	 * 
	 * binder.setValidator(materialValidator); }
	 */

	@RequestMapping(value = "/materialAdd", method = RequestMethod.POST)
	@RequestScoped
	public String saveMaterial(
			@ModelAttribute("materialAdd") @Valid Material material,
			BindingResult result, DefaultSessionAttributeStore store,
			WebRequest request1, HttpServletRequest request,
			SessionStatus status, Model model, HttpServletResponse response) {

		String msa = null;
		String materialUpadte = null;
		List<String> list = null;
		Long duplicateId = 0l;
		String res = null;
		try {
			// here we set the CharacterEncoding to resonse becoz of
			// Localization Concept
			response.setCharacterEncoding("UTF-8");

			// this method is used to check the Duplicates
			duplicateId = materialService.duplicateCheck(material
					.getMaterialName());
			if (duplicateId == 0) {
				// here ther is no duplicates
				// saveMaterialDetails this method is used to save Material
				// Details
				session = request.getSession(false);

				if (material.getAvgWeight().equals(""))
					material.setAvgWeight("0");
				if (material.getAvgHeight().equals(""))
					material.setAvgHeight("0");
				if (material.getAvgVolume().equals(""))
					material.setAvgVolume("0");
				if (material.getDimension().equals(""))
					material.setDimension("0");
				if (material.getReorderLevel().equals(""))
					material.setReorderLevel("0");
				if (material.getMaxDeliveryTime().equals(""))
					material.setMaxDeliveryTime("0");
				if (material.getAvgLength().equals(""))
					material.setAvgLength("0");

				if (material.getTaxCatogery().equals(""))
					material.setTaxCatogery(null);

				msa = materialService.saveMaterialDetails(material, session
						.getAttribute("userId").toString(), session
						.getAttribute("userName").toString());
				model.addAttribute("materialAdd", new Material());

				if (msa.equals("S")) {
					list = new ArrayList<String>();
					list.add("2");
					// here we set the aid value for active the add tab
					material.setAid(0);
					materialUpadte = "Material Data is saved Successfully";
					res = "redirect:materialHome.mnt?success=" + materialUpadte
							+ "&list=" + list + "";
				} else {
					list = new ArrayList<String>();
					list.add("2");
					material.setAid(0);
					materialUpadte = "Material Data is not saved  properly";
					res = "redirect:materialHome.mnt?warning=" + materialUpadte
							+ "&listw=" + list + "";
				}
			} else {

				request.setAttribute("materialDuplicate",
						"Material Name  is already exists. Please try some other name");
				material.setAid(1);
				return "materialHome";
			}

		} catch (Exception e) {

			list = new ArrayList<String>();
			list.add("2");
			material.setAid(0);
			materialUpadte = "Material Data is not saved  properly";
			res = "redirect:materialHome.mnt?warning=" + materialUpadte
					+ "&listw=" + list + "";
			e.printStackTrace();
		}

		return res;
		// return "materialHome";
	}

	@RequestMapping(value = "/materialSearch", method = RequestMethod.GET)
	
	public String searchMaterial(@ModelAttribute Material material,
			BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		List<MaterialDisplay> list = null;
		List<Object[]> listl = null;

		MaterialType materialType = null;
		MaterialCategory materialCategory = null;
		Uom uomd = null;
		TaxCategory taxCategory = null;
		MaterialDisplay materialDisplay = null;
		try {
			list = new ArrayList<MaterialDisplay>();
			// int materialId=material.getMaterial_Id();

			int id = material.getMaterial_Id();
			String dbField = material.getXmlLabel();
			String operation = material.getOperations();
			String basicSearchId = material.getBasicSearchId();

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

				list = materialService.searchMaterial();
			} else {
				listl = materialService.basicSearchMaterial(dbField, operation,
						basicSearchId);
				Iterator<Object[]> iterator = listl.iterator();
				while (iterator.hasNext()) {

					Object[] object = (Object[]) iterator.next();
					materialDisplay = new MaterialDisplay();
					materialDisplay.setMaterial_Id((Integer) object[0]);
					materialDisplay.setMaterialCodeName((String) object[1]);
					materialDisplay.setMaterialName((String) object[2]);
					materialType = (MaterialType) object[3];
					materialDisplay.setMaterialTypeName(materialType
							.getMaterialTypeName());
					materialCategory = (MaterialCategory) object[6];
					materialDisplay.setMaterialCategoryName(materialCategory
							.getMaterialCategory());
					uomd = (Uom) object[4];
					materialDisplay.setUomName(uomd.getUom());

					taxCategory = (TaxCategory) object[5];

					materialDisplay.setTaxCatogeryName(taxCategory
							.getTaxCategory());
					list.add(materialDisplay);
				}
			}
			request.setCharacterEncoding("UTF-8");

			request.setAttribute("materialSearch", list);
			if (listl!=null) {
				request.setAttribute("materialSearchNoData",
						"Nothing found to display");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("materialAdd", material);

		/* return new ModelAndView("materialHome","commandAdd",new Material()); */
		return "materialHome";
	}

	@RequestMapping(value = "/materialEditHome", method = RequestMethod.GET)
	@Scope("request")
	@RequestScoped
	public String materialEdit(@ModelAttribute Material materialDisplay,
			HttpServletRequest request, Model model,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("materialCodeEdit"));
		response.setCharacterEncoding("UTF-8");
		List<Object[]> list = null;
		Object[] object = null;

		MaterialType materialType = null;
		MaterialCategory materialCategory = null;
		Uom uomd = null;
		TaxCategory taxCategory = null;
		List<Material> materialsList = new ArrayList<Material>();

		try {

			list = materialService.searchMaterialWithId(id);

			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				object = (Object[]) iterator.next();
				materialDisplay.setMaterialIdEdit((Integer) object[0]);
				materialDisplay.setMaterialCodeEdit((String) object[1]);
				materialDisplay.setMaterialNameEdit((String) object[2]);
				materialType = (MaterialType) object[3];
				materialDisplay.setMaterialTypeName(materialType
						.getMaterialTypeName());

				materialCategory = (MaterialCategory) object[6];
				materialDisplay.setMaterialCategoryName(materialCategory
						.getMaterialCategory());
				uomd = (Uom) object[4];
				materialDisplay.setUomName(uomd.getUomCode());

				/* taxCategory = (TaxCategory) object[5]; */

				/*
				 * materialDisplay
				 * .setTaxCategoryName(taxCategory.getTaxCategory());
				 */
				materialDisplay.setMaterialCategoryEdit(String
						.valueOf(materialCategory.getMaterialCategoryId()));
				materialDisplay.setMaterialTypeEdit(String.valueOf(materialType
						.getMaterialType()));
				materialDisplay.setTaxCatogeryEdit((String) object[5]);
				materialDisplay.setUomEdit(String.valueOf(uomd.getUom_Id()));
				// select
				// m.material_Id,m.materialCode,m.materialName,m.materialTypeValues,m.uomDetails,m.taxCategory,m.category,m.materialDescription,m.salesUOM,m.active,m.purchaseUOM,m.avgWeight,m.avgHeight,m.avgLength,m.avgVolume,m.weightUOM,m.heightUOM,m.lengthUOM,m.dimension,m.shelfLife,m.shelfLifeUOM,m.reorderLevel,m.maxDeliveryTime,m.maxDeliveryTimeUOM,m.alternateUOM
				// from Material m where m.material_Id="+id+"";
				materialDisplay.setMaterialDescriptionEdit((String) object[7]);
				materialDisplay.setSalesUOMEdit((String) object[8]);
				materialDisplay.setActiveEdit((String) object[9]);
				materialDisplay.setPurchaseUOMEdit((String) object[10]);
				materialDisplay.setAvgWeightEdit((String) object[11]);
				materialDisplay.setAvgHeightEdit((String) object[12]);
				materialDisplay.setAvgLengthEdit((String) object[13]);
				materialDisplay.setAvgVolumeEdit((String) object[14]);
				materialDisplay.setWeightUOMEdit((String) object[15]);
				materialDisplay.setHeightUOMEdit((String) object[16]);
				materialDisplay.setLengthUOMEdit((String) object[17]);
				materialDisplay.setDimensionEdit((String) object[18]);
				materialDisplay.setShelfLifeEdit((String) object[19]);
				materialDisplay.setShelfLifeUOMEdit((String) object[20]);
				materialDisplay.setReorderLevelEdit((String) object[21]);
				materialDisplay.setMaxDeliveryTimeEdit((String) object[22]);
				materialDisplay.setMaxDeliveryTimeUOMEdit((String) object[23]);
				materialDisplay.setAlternateUOMEdit((String) object[24]);

				materialsList.add(materialDisplay);

			}
			request.setAttribute("materialValues", materialsList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			list = null;
			object = null;
		}
		model.addAttribute("materialAdd", materialDisplay);
		return "materialHome";
		/*
		 * return new
		 * ModelAndView("materialHome","materialAdd",materialDisplay);
		 */
	}

	@RequestMapping(value = "/materialUpdate", method = RequestMethod.POST)
	@RequestScoped
	public String updateMaterial(
			@ModelAttribute("materialAdd") Material material,
			HttpServletRequest request, Model model,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Long duplicateId = 0l;
		try {
			duplicateId = materialService.updateDuplicateCheck(
					material.getMaterialNameEdit(),
					material.getMaterialIdEdit());

			if (duplicateId == 0) {
				material.setMaterial_Id(material.getMaterialIdEdit());
				material.setMaterialType(material.getMaterialTypeEdit());
				material.setMaterialCategory(material.getMaterialCategoryEdit());
				material.setUom(material.getUomEdit());
				material.setTaxCatogery(material.getTaxCatogeryEdit());
				material.setMaterialCode(material.getMaterialCodeEdit());
				material.setMaterialName(material.getMaterialNameEdit());
				material.setActive(material.getActiveEdit());
				material.setAlternateUOM(material.getAlternateUOMEdit());
				material.setAvgHeight(material.getAvgHeightEdit());
				material.setAvgLength(material.getAvgLengthEdit());
				material.setAvgVolume(material.getAvgVolumeEdit());
				material.setAvgWeight(material.getAvgWeightEdit());
				material.setDimension(material.getDimensionEdit());
				material.setMaterialDescription(material
						.getMaterialDescriptionEdit());
				material.setHeightUOM(material.getHeightUOMEdit());
				material.setLengthUOM(material.getLengthUOMEdit());
				material.setMaxDeliveryTime(material.getMaxDeliveryTimeEdit());
				material.setMaxDeliveryTimeUOM(material
						.getMaxDeliveryTimeUOMEdit());
				material.setPurchaseUOM(material.getPurchaseUOMEdit());
				material.setReorderLevel(material.getReorderLevelEdit());
				material.setSalesUOM(material.getSalesUOMEdit());
				material.setShelfLifeUOM(material.getShelfLifeUOMEdit());
				material.setShelfLife(material.getShelfLifeEdit());
				material.setWeightUOM(material.getWeightUOMEdit());
				material.setTaxCatogery(material.getTaxCatogeryEdit());

				if (material.getAvgWeight().equals(""))
					material.setAvgWeight("0");
				if (material.getAvgHeight().equals(""))
					material.setAvgHeight("0");
				if (material.getAvgVolume().equals(""))
					material.setAvgVolume("0");
				if (material.getDimension().equals(""))
					material.setDimension("0");
				if (material.getReorderLevel().equals(""))
					material.setReorderLevel("0");
				if (material.getMaxDeliveryTime().equals(""))
					material.setMaxDeliveryTime("0");
				if (material.getAvgLength().equals(""))
					material.setAvgLength("0");
				if (material.getTaxCatogery().equals(""))
					material.setTaxCatogery(null);
				String msg = materialService.updateMaterial(material);

				if (msg.equals("S")) {
					request.setAttribute("materialUpadte",
							"Material Data is updated successfully");
				} else {
					request.setAttribute("materialUpadteError",
							"Material Data is not updated properly");
				}
			} else {
				request.setAttribute("materialEditDuplicate",
						"MaterialName  is already exists . Please try some other name");
				request.setAttribute("materialValues", "hello");
				return "materialHome";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("materialAdd", new Material());
		/* return new ModelAndView("materialHome","materialAdd",new Material()); */
		return "materialHome";

	}

	@RequestMapping(value = "/materialDelete", method = RequestMethod.GET)
	@RequestScoped
	public ModelAndView materialDelete(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		int materialId = 0;
		try {
			materialId = Integer.parseInt(request
					.getParameter("materialCodeDelete"));
			String msg = materialService.materialDelete(materialId);

			if (msg.equals("S")) {
				request.setAttribute("materialDelete",
						"Material Data is deleted successfully");
				session = request.getSession(false);
				Date date = new Date();
				String modifiedDate = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(date);
				auditLogService.setAuditLogSave(session.getAttribute("userId")
						.toString(), "D", "Material", "ROW", String
						.valueOf(materialId), "1", modifiedDate, session
						.getAttribute("userName").toString());
			} else {
				request.setAttribute("materialDeleteError",
						"Material Data is not deleted properly");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("materialHome", "materialAdd", new Material());
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "material_Id";

		Map<String, String> map =null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
