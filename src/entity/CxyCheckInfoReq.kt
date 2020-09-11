package com.alvin.entity


/**
 * 车辆年检状态判断.
 *
 * @author yahui
 */
class CxyCheckInfoReq : CxyBaseReq() {
    /**
     * 车牌号
     */
    val carNumber:  String = ""

    /**
     * 车辆注册日期，日期格式：yyyy-MM-dd
     */
    val registerDate:  String? = null

    /**
     * 年审检验有效期，日期格式：yyyy-MM-dd，
     * 日期中的天，必须为月份的最后一天，
     * 如：2016-03-31
     */
    val checkDate:  String? = null

    /**
     * 座位数
     */
    val seatNumber: Int? = null

    /**
     * 车辆性质（0非运营，1运营）
     */
    val carOperation: Int? = null
}