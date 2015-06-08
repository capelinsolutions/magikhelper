package com.magikhelper.entities;

import com.magikhelper.utils.DateUtils;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

    protected Timestamp createTime;
    protected String createBy;
    protected Timestamp updateTime;
    protected String updateBy;
    protected boolean active;

    @Column(name = "is_active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "create_time")
    public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_by")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "update_by")
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public void populatedAuditFieldsOnCreate(String user) {
        setCreateBy(user);
//        setUpdateBy(user);
        setCreateTime(DateUtils.currentTimeStamp());
//        setUpdateTime(DateUtils.currentTimeStamp());
        setActive(true);
    }

    public void populatedAuditFieldsOnUpdate(String user) {
        setUpdateBy(user);
        setUpdateTime(DateUtils.currentTimeStamp());
    }
}
