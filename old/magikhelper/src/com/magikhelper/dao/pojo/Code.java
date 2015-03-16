package com.magikhelper.dao.pojo;

// Generated Jan 7, 2015 9:17:15 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Code generated by hbm2java
 */
@Entity
@Table(name = "code", catalog = "magikhelper")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Code implements java.io.Serializable {

	private Integer codeId;
	private CodeType codeType;
	private String name;
	private String description;
	private Date updateTime;
	private Date createTime;

	public Code() {
	}

	public Code(CodeType codeType, String name) {
		this.codeType = codeType;
		this.name = name;
	}

	public Code(CodeType codeType, String name, String description,
			Date updateTime, Date createTime) {
		this.codeType = codeType;
		this.name = name;
		this.description = description;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "code_id", unique = true, nullable = false)
	public Integer getCodeId() {
		return this.codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_type_id", nullable = false)
	public CodeType getCodeType() {
		return this.codeType;
	}

	public void setCodeType(CodeType codeType) {
		this.codeType = codeType;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
