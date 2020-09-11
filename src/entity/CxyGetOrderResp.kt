package com.alvin.entity


import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 下单.
 *
 * @author yahui
 */

class CxyGetOrderResp {
    /**
     * 车行易订单号
     */
    var orderId: String? = null

    /**
     * 商家订单号
     */
    var exOrderId: String? = null

    /**
     * 下单时间,日期格式：yyyy-MM-dd hh:mm:ss
     */
    var createTime: LocalDateTime? = null

    /**
     * 下单时间,日期格式：yyyy-MM-dd hh:mm:ss
     */
    var updateTime: LocalDateTime? = null

    /**
     * 订单状态—7处理中  8已完结
     */
    var orderStatus: Int? = null

    /**
     * 订单金额
     */
    var orderAmount: BigDecimal? = null

    /**
     * 优惠金额
     */
    var discountAmount: BigDecimal? = null

    /**
     * 支付金额
     */
    var payAmount: BigDecimal? = null

    /**
     * 支付流水号
     */
    var payNo: String? = null

    /**
     * 订单确认状态：
     * 0=未确认；
     * 2=已确认，可派单
     */
    var confirmStatus: Int? = null

    /**
     * 快递单号
     */
    var postSheetId: String? = null

    /**
     * 快递日期日期格式：yyyy-MM-ddhh:mm:ss
     */
    var postDate: String? = null

    /**
     * 快递公司
     */
    var postCompany: String? = null

    /**
     * 取车时间日期格式：yyyy-MM-ddhh:mm:ss
     */
    var getCarDate: String? = null

    /**
     * 取车地点
     */
    var getCarAddress: String? = null

    /**
     * 检测站
     */
    var checkStation: String? = null

    /**
     * 退单的原因
     */
    var retrunReason: String? = null

    /**
     * 预约时间日期格式：yyyy-MM-ddhh:mm:ss
     */
    var communiteTime: String? = null

    /**
     * 订单处理轨迹
     */
    var tracks: List<Track>? = null
    var requirementList: List<Requirement>? = null

    /**
     * 资料寄送（车行易代办点）地址信息，免检inspectionType=1，年检类型transactType=1时有值
     * json
     */
    var mailAddress: String? = null

    /**
     * 订单退款处理信息
     * json
     */
    var refundInfo: String? = null


    class Track {
        /**
         * 轨迹生成时间,日期格式：yyyy-MM-ddhh:mm:ss
         */
        var createTime: LocalDateTime? = null

        /**
         * 轨迹状态
         */
        var status: String? = null

        /**
         * 轨迹说明
         */
        var description: String? = null
    }


    class Requirement {
        /**
         * 资料id
         */
        var id: String? = null

        /**
         * 资料类型（
         * JQX:交强险
         * CCS:车船税
         * XSZ:行驶证正副本
         * CLDJZ:车辆登记证
         * SFZ:身份证正反面
         * SCSFZ:车主手持身份证正面
         * OTHER：其他）
         */
        var requirementType: String? = null

        /**
         * 资料状态：
         * 0=待确认
         * 1=已确认
         * 2=待审核
         * 3=审核通过
         * 4=审核失败
         */
        var status: Int? = null

        /**
         * 资料图片确认类型：
         * 0=不需上传，不审核
         * 1=必须上传审核
         * 2=上传则需审核，不上传则不需审核
         */
        var confirmType: Int? = null

        /**
         * 审核结果备注
         */
        var remark: String? = null
    }


    class RefundInfo {
        /**
         * 退款金额
         */
        var refundAmount: BigDecimal? = null

        /**
         * 退款时间日期格式：yyyy-MM-ddhh:mm:ss
         */
        var refundTime: String? = null

        /**
         * 备注
         */
        var refundMark: String? = null

        /**
         * 退款状态---0已申请，1已同意并退款，2拒绝退款，3退款失败
         */
        var refundStatus: Int? = null
    }


    class MailAddress {
        /**
         * 下单用户邮寄姓名
         */
        var name: String? = null

        /**
         * 下单用户邮寄手机号
         */
        var phone: String? = null

        /**
         * 下单用户邮寄位置
         */
        var location: String? = null
    }
}