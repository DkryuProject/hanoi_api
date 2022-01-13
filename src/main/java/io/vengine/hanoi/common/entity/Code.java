package io.vengine.hanoi.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@IdClass(CodeKey.class)
@Table(name = "CODE")
public class Code {

	@Id
	@Column(name = "TYPE")
	private String type;

	@Id
	@Column(name = "CODE")
	private String code;

	@Column(name = "CODE_NAME")
	private String codeName;

	@Column(name = "SEQ")
	private long seq;

	@Column(name = "TEXT1")
	private String text1;

	@Column(name = "TEXT2")
	private String text2;

	@Column(name = "TEXT3")
	private String text3;

	@Column(name = "TEXT4")
	private String text4;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE")
	private Date insertDate;

	public Code() {

	}

	public Code(String type, String code, String codeName, long seq, String text1, String text2, String text3, String text4) {
		this.type = type;
		this.code = code;
		this.codeName = codeName;
		this.seq = seq;
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		this.text4 = text4;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public String getText4() {
		return text4;
	}

	public void setText4(String text4) {
		this.text4 = text4;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

}
