package com.alvin.entity


/**
 * 车辆年检状态判断.
 *
 * @author yahui
 */
class CxyCheckInfoResp {
    /**
     * 车牌号
     */
    var carNumber: String? = null

    /**
     * 车辆注册日期，日期格式：yyyy-MM-dd
     */
    var registerDate: String? = null

    /**
     * 年审检验有效期，日期格式：yyyy-MM-dd，
     * 日期中的天，必须为月份的最后一天，
     * 如：2016-03-31
     */
    var checkDate: String? = null

    /**
     * 车辆校验文案信息
     */
    var msgTip: String? = null

    /**
     * 年检状态；
     * -1:未知（当车辆注册日期和检验有效期未提供或者有误时）；
     * 0：未进入预约期；
     * 1：可预约；
     * 2：逾期但不足一年；
     * 3：逾期且超过一年；
     * 4：严重逾期（两次未年检）；
     * 5：报废（3次未年检）；
     * 13：6年内粤牌车逾期超过一年不可办理（需前往车管所）
     * 15：运营车辆暂未开通办理，请前往车管所自主办理
     */
    var state: Int? = null

    /**
     * 年检校验状态（目前只针对粤牌车）：
     * -1:无信息
     * 7：初登日期大于4年；
     * 8：车辆状态异常；
     * 9：使用性质不合要求；
     * 10：核定载客人数不合要求；
     * 11：车辆类型不合要求；
     */
    var checkState: Int? = null

    /**
     * 车行易能否办理：
     * 1=可代办
     * 2=不可代办
     */
    var canProcess: Int? = null

    /**
     * 年检类型：
     * 1=免检
     * 2=上线检
     */
    var inspectionType: Int? = null
    var transactTypeList: List<TransactType>? = null
    var processRegionList: List<ProcessRegion>? = null


    class TransactType {
        /**
         * 年检代办类型：
         * 1=免检(自主寄送资料)；
         * 2=免检(邮政上门取资料)；
         * 3=免检(顺丰上门)
         * 4=免检（无需寄送资料）
         * 7=上线检（代驾检测）
         * 6=上线检（自驾检测）
         */
        var transactType: Int? = null

        /**
         * 年检代办类型名称
         */
        var transactTypeName: String? = null

        /**
         * 年检代办类型描述
         */
        var transactTypeDesc: String? = null
    }


    class ProcessRegion {
        /**
         * 城市编码
         */
        var regionCode: String? = null

        /**
         * 城市名称
         */
        var regionName: String? = null
    }
}