package com.alvin.entity


import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 下单.
 *
 * @author yahui
 */

class CxyPayResp {
    /**
     * 商家订单号
     */
    var exOrderId: String? = null

    /**
     * 车行易订单号
     */
    var orderId: String? = null

    /**
     * 优惠金额
     */
    var discountAmount: BigDecimal? = null

    /**
     * 订单状态—7处理中  8已完结
     */
    var orderStatus: Int? = null

    /**
     * 支付流水号
     */
    var payNo: String? = null

    /**
     * 订单金额
     */
    var orderAmount: BigDecimal? = null

    /**
     * 支付金额
     */
    var payAmount: BigDecimal? = null

    /**
     * 下单时间,日期格式：yyyy-MM-dd hh:mm:ss
     */
    var createTime: LocalDateTime? = null
    var confirmStatus: Int? = null
    var payChannel: Int? = null
    var orderTitle: String? = null
    var payStatus: Int? = null
}