package io.jboot.admin.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysData<M extends BaseSysData<M>> extends JbootModel<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public void setCode(java.lang.String code) {
		set("code", code);
	}
	
	public java.lang.String getCode() {
		return getStr("code");
	}

	public void setCodeDesc(java.lang.String codeDesc) {
		set("codeDesc", codeDesc);
	}
	
	public java.lang.String getCodeDesc() {
		return getStr("codeDesc");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}
	
	public java.lang.String getType() {
		return getStr("type");
	}

	public void setTypeDesc(java.lang.String typeDesc) {
		set("typeDesc", typeDesc);
	}
	
	public java.lang.String getTypeDesc() {
		return getStr("typeDesc");
	}

	public void setStatus(java.lang.String status) {
		set("status", status);
	}
	
	public java.lang.String getStatus() {
		return getStr("status");
	}

	public void setOrderNo(java.lang.String orderNo) {
		set("orderNo", orderNo);
	}
	
	public java.lang.String getOrderNo() {
		return getStr("orderNo");
	}

	public void setCreateDate(java.util.Date createDate) {
		set("createDate", createDate);
	}
	
	public java.util.Date getCreateDate() {
		return get("createDate");
	}

	public void setLastUpdAcct(java.lang.String lastUpdAcct) {
		set("lastUpdAcct", lastUpdAcct);
	}
	
	public java.lang.String getLastUpdAcct() {
		return getStr("lastUpdAcct");
	}

	public void setLastUpdTime(java.util.Date lastUpdTime) {
		set("lastUpdTime", lastUpdTime);
	}
	
	public java.util.Date getLastUpdTime() {
		return get("lastUpdTime");
	}

	public void setNote(java.lang.String note) {
		set("note", note);
	}
	
	public java.lang.String getNote() {
		return getStr("note");
	}

}
