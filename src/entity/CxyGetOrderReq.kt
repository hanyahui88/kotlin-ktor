package com.alvin.entity


/**
 * 查询订单.
 *
 * @author yahui
 */
class CxyGetOrderReq : CxyBaseReq() {
    /**
     * 车行易订单号
     */
    val orderId: String? = null

    /**
     * 商家订单号
     */
    val exOrderId:  String = ""
}