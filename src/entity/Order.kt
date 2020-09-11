package com.alvin.entity

import me.liuwj.ktorm.entity.Entity
import java.math.BigDecimal
import java.time.LocalDateTime

interface Order : Entity<Order> {
    /**
     * 自增主键
     */
    val id: `Long`

    /**
     * 订单唯一编号，由时间戳（年月日时）+4位不重复的随机数字
     */
    val number: String

    /**
     * 订单类型: 线下核销订单 OFFLINE 线上年检订单 ANNUAL_INSPECTION
     */
    val type: String

    /**
     * 客户 id
     */
    val customerId: Long

    /**
     * 车辆 id
     */
    val vehicleId: Long

    /**
     * 商品 id
     */
    val goodsId: Long

    /**
     * 支付方式: 微信 JSAPI 支付 WX_JS_API
     */
    val payType: String

    /**
     * 支付流水号
     */
    val tradeId: String

    /**
     * 支付状态: CANCEL 用户取消付款,PAY_TIME_OUT 支付超时, PENDING 待付款, PAID 已付款, NO_PAYMENT 不需付款, PENDING_REFUND 等待退款, REFUNDED 已退款
     */
    val payStatus: String

    /**
     * 订单状态: CREATED 新建, PROCESSING 处理中, SUCCESS 成功, FAILURE 失败, CLOSED 超时关单, CANCEL 已取消
     */
    val status: String

    /**
     * 卡券 id
     */
    val couponId: Long

    /**
     * 第三方订单唯一标识
     */
    val thirdPartyOrderNo: String

    /**
     * 商品原价 单位为元
     */
    val originalPrice: BigDecimal

    /**
     * 销售价格 单位为元
     */
    val salesPrice: BigDecimal

    /**
     * 商品成本价 单位为元
     */
    val costPrice: BigDecimal

    /**
     * 优惠券价格 单位为元
     */
    val couponPrice: BigDecimal

    /**
     * 实付金额 单位为元
     */
    val actuallyPaid: BigDecimal

    /**
     * 创建时间
     */
    val createTime: LocalDateTime

    /**
     * 最后一次更新时间
     */
    val updateTime: LocalDateTime

    /**
     * 删除标识 1删除 0未删除
     */
    val isRemoved: Int

    /**
     * 商品分类 id
     */
    val goodsCategoryId: Long

    /**
     * 供应商 id
     */
    val supplierId: Long

    /**
     * 归属机构 id
     */
    val organizationId: Long

    /**
     * 支付时间
     */
    val payTime: LocalDateTime

    /**
     * 完成时间
     */
    val finishedTime: LocalDateTime
}