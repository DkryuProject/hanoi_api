package io.vengine.hanoi.report.controller;

import java.math.BigDecimal;

public class Report {
	private String title;
	private String categoryName;
	private BigDecimal preAmount;
	private BigDecimal currentAmount;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public BigDecimal getPreAmount() {
		return preAmount;
	}

	public void setPreAmount(BigDecimal preAmount) {
		this.preAmount = preAmount;
	}

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Report(String categoryName, BigDecimal preAmount, BigDecimal currentAmount) {
		this.categoryName = categoryName;
		this.preAmount = preAmount;
		this.currentAmount = currentAmount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
