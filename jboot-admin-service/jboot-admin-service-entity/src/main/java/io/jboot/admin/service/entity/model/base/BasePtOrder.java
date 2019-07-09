package io.jboot.admin.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePtOrder<M extends BasePtOrder<M>> extends JbootModel<M> implements IBean {

	public void setOrderId(java.lang.String orderId) {
		set("order_id", orderId);
	}
	
	public java.lang.String getOrderId() {
		return getStr("order_id");
	}

	public void setPayId(java.lang.String payId) {
		set("pay_id", payId);
	}
	
	public java.lang.String getPayId() {
		return getStr("pay_id");
	}

	public void setAdressId(java.lang.String adressId) {
		set("adress_id", adressId);
	}
	
	public java.lang.String getAdressId() {
		return getStr("adress_id");
	}

	public void setOrderStatus(java.lang.Integer orderStatus) {
		set("order_status", orderStatus);
	}
	
	public java.lang.Integer getOrderStatus() {
		return getInt("order_status");
	}

	public void setUserId(java.lang.Long userId) {
		set("user_id", userId);
	}
	
	public java.lang.Long getUserId() {
		return getLong("user_id");
	}

	public void setOrderContent(java.lang.String orderContent) {
		set("order_content", orderContent);
	}
	
	public java.lang.String getOrderContent() {
		return getStr("order_content");
	}

	public void setOrderReserver(java.lang.String orderReserver) {
		set("order_reserver", orderReserver);
	}
	
	public java.lang.String getOrderReserver() {
		return getStr("order_reserver");
	}

	public void setOrderDate(java.util.Date orderDate) {
		set("order_date", orderDate);
	}
	
	public java.util.Date getOrderDate() {
		return get("order_date");
	}

	public void setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
	}
	
	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

	public void setOrderName(java.lang.String orderName) {
		set("order_name", orderName);
	}
	
	public java.lang.String getOrderName() {
		return getStr("order_name");
	}

	public void setPayType(java.lang.String payType) {
		set("pay_type", payType);
	}
	
	public java.lang.String getPayType() {
		return getStr("pay_type");
	}

	public void setOrderPrice(java.lang.Double orderPrice) {
		set("order_price", orderPrice);
	}
	
	public java.lang.Double getOrderPrice() {
		return getDouble("order_price");
	}

	public void setOrderPayPrice(java.lang.Double orderPayPrice) {
		set("order_pay_price", orderPayPrice);
	}
	
	public java.lang.Double getOrderPayPrice() {
		return getDouble("order_pay_price");
	}

}