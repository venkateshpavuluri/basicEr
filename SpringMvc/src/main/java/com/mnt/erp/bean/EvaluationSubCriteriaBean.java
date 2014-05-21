/**
 * 
 */
package com.mnt.erp.bean;

/**
 * @author yogi
 * 
 */
import com.mnt.erp.bean.EvaluationCriteria;

public class EvaluationSubCriteriaBean {

    /**
     * 
     */
    private int evaluationSubCriteriaId;
    private String evaluationSubCriteria;
    private String evaluationCriteriaId;
    private String score;
    private EvaluationCriteria evaluationCriteria;

    // edit variables
    private int evaluationSubCriteriaIdEdit;
    private String evaluationSubCriteriaEdit;
    private String evaluationCriteriaIdEdit;
    private String scoreEdit;

    // XML variables

    private String xmlLabel;
    private String operations;
    private String basicSearchId;
    private int aid;

    public int getEvaluationSubCriteriaId() {
	return evaluationSubCriteriaId;
    }

    public void setEvaluationSubCriteriaId(int evaluationSubCriteriaId) {
	this.evaluationSubCriteriaId = evaluationSubCriteriaId;
    }

    public String getEvaluationSubCriteria() {
	return evaluationSubCriteria;
    }

    public void setEvaluationSubCriteria(String evaluationSubCriteria) {
	this.evaluationSubCriteria = evaluationSubCriteria;
    }

    public String getEvaluationCriteriaId() {
	return evaluationCriteriaId;
    }

    public void setEvaluationCriteriaId(String evaluationCriteriaId) {
	this.evaluationCriteriaId = evaluationCriteriaId;
    }

    public String getScore() {
	return score;
    }

    public void setScore(String score) {
	this.score = score;
    }

    public int getEvaluationSubCriteriaIdEdit() {
	return evaluationSubCriteriaIdEdit;
    }

    public void setEvaluationSubCriteriaIdEdit(int evaluationSubCriteriaIdEdit) {
	this.evaluationSubCriteriaIdEdit = evaluationSubCriteriaIdEdit;
    }

    public String getEvaluationSubCriteriaEdit() {
	return evaluationSubCriteriaEdit;
    }

    public void setEvaluationSubCriteriaEdit(String evaluationSubCriteriaEdit) {
	this.evaluationSubCriteriaEdit = evaluationSubCriteriaEdit;
    }

    public String getEvaluationCriteriaIdEdit() {
	return evaluationCriteriaIdEdit;
    }

    public void setEvaluationCriteriaIdEdit(String evaluationCriteriaIdEdit) {
	this.evaluationCriteriaIdEdit = evaluationCriteriaIdEdit;
    }

    public String getScoreEdit() {
	return scoreEdit;
    }

    public void setScoreEdit(String scoreEdit) {
	this.scoreEdit = scoreEdit;
    }

    public String getXmlLabel() {
	return xmlLabel;
    }

    public void setXmlLabel(String xmlLabel) {
	this.xmlLabel = xmlLabel;
    }

    public String getOperations() {
	return operations;
    }

    public void setOperations(String operations) {
	this.operations = operations;
    }

    public String getBasicSearchId() {
	return basicSearchId;
    }

    public void setBasicSearchId(String basicSearchId) {
	this.basicSearchId = basicSearchId;
    }

    public int getAid() {
	return aid;
    }

    public void setAid(int aid) {
	this.aid = aid;
    }

    public EvaluationCriteria getEvaluationCriteria() {
	return evaluationCriteria;
    }

    public void setEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
	this.evaluationCriteria = evaluationCriteria;
    }

}
