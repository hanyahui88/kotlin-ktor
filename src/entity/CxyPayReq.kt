package com.alvin.entity


/**
 * 下单.
 *
 * @author yahui
 */

class CxyPayReq : CxyBaseReq() {
    /**
     * 商家订单号（必须唯一）
     * 下单支付后不能用旧exOrderId重复下单
     */
    val exOrderId: String? = null

    /**
     * 车牌号码
     */
    val carNumber: String? = null

    /**
     * 车辆注册日期，日期格式：yyyy-MM-dd
     */
    val registerDate: String? = null

    /**
     * 车辆检验有效期，日期格式：yyyy-MM-dd
     */
    val checkDate: String? = null

    /**
     * 下单用户邮寄姓名
     */
    val name: String? = null

    /**
     * 下单用户邮寄手机号
     */
    val phone: String? = null

    /**
     * 下单用户邮寄地址
     */
    val address: String? = null

    /**
     * 下单用户邮寄省
     */
    val provinceName: String? = null

    /**
     * 下单用户邮寄城市
     */
    val cityName: String? = null

    /**
     * 下单用户邮寄地区
     */
    val areaName: String? = null

    /**
     * 下单用户邮寄位置
     */
    val location: String? = null

    /**
     * 订单信息回调通知URL
     * 需要结单或者退单才会发起回调的
     */
    val callbackUrl: String? = null

    /**
     * 年检代办类型：
     * 1=免检(自主寄送资料)；
     * 2=免检(邮政上门取资料)；
     * 3=免检(顺丰上门)
     * 4=免检（无需寄送资料）
     * 7=上线检（代驾检测）
     * 6=上线检（自驾检测）
     */
    val transactType: Int? = null

    /**
     * 上线检办理地区编码（上线检必填）
     */
    val regionCode: String? = null

    /**
     * 上线检办理地区（上线检必填）
     */
    val regionName: String? = null

    /**
     * 支付方式0预付款1 收银台
     */
    val payType: Int? = null

    /**
     * 上线检(代驾)取车地址,如广东省广州市
     */
    val getCarAddress: String? = null

    /**
     * 上线检(代驾)取车详细地址,街道地址
     */
    val getCarAddressDetail: String? = null
}