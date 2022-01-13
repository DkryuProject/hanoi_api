package io.vengine.hanoi.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.vengine.hanoi.common.entity.Code;

@Entity
@Table(name = "FINANCE_PRICE")
public class FinancePriceDetail implements Serializable {

	private static final long serialVersionUID = -7835823705044124649L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "SUB_CATEGORY")
	private String subCategoryCode;

	@Column(name = "SUB_CATEGORY_NAME")
	private String subCategoryName;

	@Column(name = "SUPPLIER")
	private String supplier;

	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "UNIT_MEASUREMENT")
	private String unitMeasurement;

	@Column(name = "QUANTITY", precision = 22, scale = 2)
	private BigDecimal quantity;

	@Column(name = "PRICE", precision = 22, scale = 2)
	private BigDecimal price;

	@Column(name = "AMOUNT", precision = 22, scale = 2)
	private BigDecimal amount;

	@Column(name = "INSERT_USER")
	private String insertUser;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE")
	private Date insertDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE")
	private Date modifyDate;

	@Column(name = "DEL_FLAG")
	private String delFlag;

	@Transient
	private Code subCategory;

	@PrePersist
	public void prePersist() {
		this.delFlag = this.delFlag == null ? "N" : this.delFlag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubCategoryCode() {
		return subCategoryCode;
	}

	public void setSubCategoryCode(String subCategoryCode) {
		this.subCategoryCode = subCategoryCode;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Code getSubCategory() {
		Code code = new Code();
		code.setCode(this.subCategoryCode);
		code.setCodeName(this.subCategoryName);
		return code;
	}

	public void setSubCategory(Code subCategory) {
		this.subCategoryCode = subCategory.getCode();
		this.subCategoryName = subCategory.getCodeName();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnitMeasurement() {
		return unitMeasurement;
	}

	public void setUnitMeasurement(String unitMeasurement) {
		this.unitMeasurement = unitMeasurement;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}
