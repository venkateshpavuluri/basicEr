/**
 *@Copyright MNTSOFT 
 */
package com.mnt.erp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Naresh
 * @version 1.0 05-02-2014
 */
public class InspOperationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int inspOperationId;
	private String inspTypeId;
	private String operationNo;
	private String materialId;

	private String xmlLabel;
	private String operations;
	private String basicSearchId;
	private InspectionType inspType;
	private Material material;
	private List<InspOperationStep> inspOprStep;
	
	//Child Variables
	private int[] inspOperStepId;
	private String[] inspOperStepNo;
	private String[] noOfSamples;

	// Setter And Getter Methods
	

	public List<InspOperationStep> getInspOprStep() {
		return inspOprStep;
	}

	public int[] getInspOperStepId() {
		return inspOperStepId;
	}

	public String[] getInspOperStepNo() {
		return inspOperStepNo;
	}

	public String[] getNoOfSamples() {
		return noOfSamples;
	}

	public void setInspOperStepId(int[] inspOperStepId) {
		this.inspOperStepId = inspOperStepId;
	}

	public void setInspOperStepNo(String[] inspOperStepNo) {
		this.inspOperStepNo = inspOperStepNo;
	}

	public void setNoOfSamples(String[] noOfSamples) {
		this.noOfSamples = noOfSamples;
	}

	public void setInspOprStep(List<InspOperationStep> inspOprStep) {
		this.inspOprStep = inspOprStep;
	}

	public InspectionType getInspType() {
		return inspType;
	}

	public Material getMaterial() {
		return material;
	}

	public void setInspType(InspectionType inspType) {
		this.inspType = inspType;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getInspOperationId() {
		return inspOperationId;
	}

	public String getInspTypeId() {
		return inspTypeId;
	}

	public String getOperationNo() {
		return operationNo;
	}

	public String getMaterialId() {
		return materialId;
	}

	public String getXmlLabel() {
		return xmlLabel;
	}

	public String getOperations() {
		return operations;
	}

	public String getBasicSearchId() {
		return basicSearchId;
	}

	public void setInspOperationId(int inspOperationId) {
		this.inspOperationId = inspOperationId;
	}

	public void setInspTypeId(String inspTypeId) {
		this.inspTypeId = inspTypeId;
	}

	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public void setXmlLabel(String xmlLabel) {
		this.xmlLabel = xmlLabel;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

	public void setBasicSearchId(String basicSearchId) {
		this.basicSearchId = basicSearchId;
	}

}
