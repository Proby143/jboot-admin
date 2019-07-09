package io.jboot.admin.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserComment<M extends BaseUserComment<M>> extends JbootModel<M> implements IBean {

	public void setUserCommentId(java.lang.String userCommentId) {
		set("user_comment_id", userCommentId);
	}
	
	public java.lang.String getUserCommentId() {
		return getStr("user_comment_id");
	}

	public void setCommentContent(java.lang.String commentContent) {
		set("comment_content", commentContent);
	}
	
	public java.lang.String getCommentContent() {
		return getStr("comment_content");
	}

	public void setCommentUrl(java.lang.String commentUrl) {
		set("comment_url", commentUrl);
	}
	
	public java.lang.String getCommentUrl() {
		return getStr("comment_url");
	}

	public void setCreated(java.util.Date created) {
		set("created", created);
	}
	
	public java.util.Date getCreated() {
		return get("created");
	}

	public void setUpdated(java.util.Date updated) {
		set("updated", updated);
	}
	
	public java.util.Date getUpdated() {
		return get("updated");
	}

	public void setState(java.lang.Integer state) {
		set("state", state);
	}
	
	public java.lang.Integer getState() {
		return getInt("state");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setUserId(java.lang.String userId) {
		set("user_id", userId);
	}
	
	public java.lang.String getUserId() {
		return getStr("user_id");
	}

	public void setShopId(java.lang.String shopId) {
		set("shop_id", shopId);
	}
	
	public java.lang.String getShopId() {
		return getStr("shop_id");
	}

	public void setVipCost(java.math.BigDecimal vipCost) {
		set("vip_cost", vipCost);
	}
	
	public java.math.BigDecimal getVipCost() {
		return get("vip_cost");
	}

	public void setAge(java.lang.Integer age) {
		set("age", age);
	}
	
	public java.lang.Integer getAge() {
		return getInt("age");
	}

	public void setReciveId(java.lang.String reciveId) {
		set("recive_id", reciveId);
	}
	
	public java.lang.String getReciveId() {
		return getStr("recive_id");
	}

}