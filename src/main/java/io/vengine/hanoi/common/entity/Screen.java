package io.vengine.hanoi.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@IdClass(ScreenKey.class)
@Table(name = "SCREEN")
public class Screen {

	@Id
	@Column(name = "SCREEN_ID")
	private String screenId;

	@Id
	@Column(name = "LANGUAGE")
	private String language;

	@Id
	@Column(name = "SEQ")
	private long seq;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "VALUE")
	private String value;

	@Column(name = "ALIGN")
	private String align;

	@Column(name = "WIDTH")
	private String width;

	@Column(name = "FIXED")
	private boolean fixed;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE")
	private Date insertDate;

	@Transient
	private String screenSelectKey;

	@Transient
	private String model;

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getScreenSelectKey() {
		return this.screenId + this.seq + this.language;
	}

	public String getModel() {
		return "item." + this.value;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean getFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

}
