package com.alvin.entity
import me.liuwj.ktorm.entity.Entity
import java.time.LocalDateTime
import java.math.BigDecimal
/**
 *  年检订单办理资料表
 **/
interface OrderAnnualInspectionData : Entity<OrderAnnualInspectionData> {
	/**
	 *  自增主键
	 **/
	val id: Long
	/**
	 *  订单 id
	 **/
	val orderId: Long
	/**
	 *  订单办理资料类型 核销资料verification_dat 行驶证正面vehicle_license_fron 行驶证反面vehicle_license_contrar 交强险保单compulsory_insuranc 车船税发票vehicle_and_vessel_tax
	 **/
	val dataType: String
	/**
	 *  订单办理图片 url
	 **/
	val imgUrl: String
	/**
	 *  待审核:INIT,审核通过:PASS,未通过:NOT_PASS
	 **/
	val isReview: String
	/**
	 *  创建时间
	 **/
	val createTime: LocalDateTime
	/**
	 *  最后一次更新时间
	 **/
	val updateTime: LocalDateTime
	/**
	 *  删除标识 1删除 0未删除
	 **/
	val isRemoved: Int
	/**
	 *  订单类型: 线下核销订单 OFFLINE 线上年检订单 ANNUAL_INSPECTION
	 **/
	val type: String
}
