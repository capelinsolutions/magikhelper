package com.magikhelper.dao.pojo;

// Generated Jan 7, 2015 9:17:15 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * CodeType generated by hbm2java
 */
@Entity
@Table(name = "code_type", catalog = "magikhelper")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CodeType implements java.io.Serializable {

	private Integer codeTypeId;
	private String name;
	private String description;
	private Date updateTime;
	private Date createTime;
	@XmlTransient
	private Set<Code> codes = new HashSet<Code>(0);

	public CodeType() {
	}

	public CodeType(String name, String description, Date updateTime,
			Date createTime, Set<Code> codes) {
		this.name = name;
		this.description = description;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.codes = codes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "code_type_id", unique = true, nullable = false)
	public Integer getCodeTypeId() {
		return this.codeTypeId;
	}

	public void setCodeTypeId(Integer codeTypeId) {
		this.codeTypeId = codeTypeId;
	}

	@Column(name = "name", length = 45)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "codeType")
	public Set<Code> getCodes() {
		return this.codes;
	}

	public void setCodes(Set<Code> codes) {
		this.codes = codes;
	}

}
