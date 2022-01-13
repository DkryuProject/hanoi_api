package io.vengine.hanoi.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.vengine.hanoi.common.entity.User;
import org.hibernate.annotations.Where;

@Entity
@IdClass(FinanceDetailKey.class)
@Table(name = "FINANCE_DETAIL")
@Where(clause = "DEL_FLAG = 'N'")
public class FinanceDetail implements Serializable {

	private static final long serialVersionUID = -7835823705044124649L;

	@Column(name = "DATE")
	private String date;

	@Id
	@Column(name = "DOC_NO")
	private String docNo;

	@Id
	@Column(name = "LINE_NUM")
	private long lineNum;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "TYPE_NAME")
	private String typeName;

	@OneToOne
	@JoinColumn(name = "ACCOUNT_CODE", referencedColumnName = "ACCOUNT_CODE")
	private AccountInfo accountCode;

	@OneToOne
	@JoinColumn(name = "COMPANY_CODE", referencedColumnName = "COMPANY_CODE")
	private CompanyInfo companyCode;

	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "DEBIT", precision = 22, scale = 2)
	private BigDecimal debit;

	@Column(name = "CREDIT", precision = 22, scale = 2)
	private BigDecimal credit;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE_CURRENCY")
	private String priceCurrency;

	@Column(name = "PRICE", columnDefinition = "float DEFAULT 0")
	private float price;

	@Column(name = "QTY", columnDefinition = "float DEFAULT 0")
	private float qty;

	@Column(name = "AMOUNT", columnDefinition = "float DEFAULT 0")
	private float amount;

	@Column(name = "SUB_ROW_FLAG")
	private String subRowFlag;

	@Column(name = "INSERT_USER")
	private String insertUser;

	@Column(name = "INSERT_USER_NAME")
	private String insertUserName;

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

	@OneToMany(targetEntity = FinancePriceDetail.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "REF_DOC_NO", referencedColumnName = "DOC_NO"), @JoinColumn(name = "REF_LINE_NO", referencedColumnName = "LINE_NUM") })
	private Collection<FinancePriceDetail> financePriceDetails;

	@Transient
	private String accountNameCurrency;

	@PrePersist
	public void prePersist() {
		this.delFlag = this.delFlag == null ? "N" : this.delFlag;
	}

	public String getDate() {
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
	}

	public void setDate(String date) {
		this.date = date.replaceAll("-", "");
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public AccountInfo getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(AccountInfo accountCode) {
		this.accountCode = accountCode;
	}

	public CompanyInfo getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(CompanyInfo companyCode) {
		this.companyCode = companyCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriceCurrency() {
		return priceCurrency;
	}

	public void setPriceCurrency(String priceCurrency) {
		this.priceCurrency = priceCurrency;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getSubRowFlag() {
		return subRowFlag;
	}

	public void setSubRowFlag(String subRowFlag) {
		this.subRowFlag = subRowFlag;
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

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public long getLineNum() {
		return lineNum;
	}

	public void setLineNum(long lineNum) {
		this.lineNum = lineNum;
	}

	public String getAccountNameCurrency() {
		return this.accountCode.getSubject() + " " + this.currency;
	}

	public void setAccountNameCurrency(String accountNameCurrency) {
		this.accountNameCurrency = accountNameCurrency;
	}

	public String getInsertUserName() {
		return insertUserName;
	}

	public void setInsertUserName(String insertUserName) {
		this.insertUserName = insertUserName;
	}

	public Collection<FinancePriceDetail> getFinancePriceDetails() {
		return financePriceDetails;
	}

	public void setFinancePriceDetails(List<FinancePriceDetail> financePriceDetails) {
		this.financePriceDetails = financePriceDetails;
	}
}
