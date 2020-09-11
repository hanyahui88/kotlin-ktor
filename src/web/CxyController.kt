package com.botpy.vosp.client.controller

import cn.hutool.core.collection.CollectionUtil
import cn.hutool.core.util.NumberUtil
import cn.hutool.json.JSONUtil
import com.alvin.entity.*
import com.botpy.vosp.client.service.impl.OrderAnnualInspectionDataServiceImpl
import com.botpy.vosp.common.constants.CommonConstant
import com.botpy.vosp.common.gateway.constants.CxyConstant
import com.botpy.vosp.common.gateway.enums.CxyEnum
import com.botpy.vosp.common.util.CxyUtils
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

/**
 * 订单主表
 * 年检流程图(https://confluence.botpy.com/pages/viewpage.action?pageId=13991957)
 *
 * @author yahui
 * @date 2020-07-07 14:54:46
 */
open class CxyController {

    lateinit var orderAnnualInspectionDataService: OrderAnnualInspectionDataServiceImpl

    @PostMapping(CxyConstant.GET_CHECK_LIST)
    fun checkDateList(cxyReq: CxyReq): String {
        val content = cxyReq.content
        val decrypt = CxyUtils.decryptByPassword("3B986530AA1F481097969BEDCA072ADC", content)
        val cxyGetCheckListReq = JSONUtil.toBean(decrypt, CxyGetCheckListReq::class.java)
        val list: MutableList<Map<*, *>> = mutableListOf()
        val map: MutableMap<String, Any> = mutableMapOf()
        val registerDate = cxyGetCheckListReq.registerDate
        val parse = LocalDate.parse(registerDate, DateTimeFormatter.ofPattern(CommonConstant.DATE_FORMAT_PATTERN))
        val localDate = parse.plusYears(2)
        map["default"] = 0
        map["checkDate"] = localDate.format(DateTimeFormatter.ofPattern(CommonConstant.DATE_FORMAT_PATTERN))
        val map1: MutableMap<String, Any> = mutableMapOf()
        val localDate1 = localDate.plusYears(2)
        map1["default"] = 0
        map1["checkDate"] = localDate1.format(DateTimeFormatter.ofPattern(CommonConstant.DATE_FORMAT_PATTERN))
        val map2: MutableMap<String, Any> = mutableMapOf()
        val localDate2 = localDate1.plusYears(2)
        map2["default"] = 0
        map2["checkDate"] = localDate2.format(DateTimeFormatter.ofPattern(CommonConstant.DATE_FORMAT_PATTERN))
        val mapMap = getIntegerMapMap(map, localDate, map1, localDate1, map2, localDate2)
        mapMap.forEach { (key: Int?, value: Map<String, Any>) -> list.add(value) }
        return CxyUtils.encryptByPassword(
            "3B986530AA1F481097969BEDCA072ADC",
            JSONUtil.toJsonStr(CxyResult.success(list))
        )
    }

    private fun getIntegerMapMap(
        map: MutableMap<String, Any>,
        localDate: LocalDate,
        map1: MutableMap<String, Any>,
        localDate1: LocalDate,
        map2: MutableMap<String, Any>,
        localDate2: LocalDate
    ): Map<Int, MutableMap<String, Any>> {
        val mapMap: MutableMap<Int, MutableMap<String, Any>> = mutableMapOf()
        val keys: MutableList<Int> = mutableListOf()
        val day = getDifferenceDayCount(localDate)
        keys.add(day)
        mapMap[day] = map
        val day1 = getDifferenceDayCount(localDate1)
        keys.add(day1)
        mapMap[day1] = map1
        val day2 = getDifferenceDayCount(localDate2)
        keys.add(day2)
        mapMap[day2] = map2
        val min = keys.stream().min { obj: Int, anotherInteger: Int? -> obj.compareTo(anotherInteger!!) }
        val integer = min.get()
        val stringObjectMap = mapMap[integer]!!
        stringObjectMap["default"] = 1
        return mapMap
    }

    /**
     * 获取两个时间的相差的天数
     *
     * @param date
     * @return
     */
    private fun getDifferenceDayCount(date: LocalDate): Int {
        val now = LocalDate.now()
        //取正数
        return Math.abs((date.toEpochDay() - now.toEpochDay()).toInt())
    }

    /**
     * 判断办理状态.
     *
     *
     * 1、客户端传入车牌号、检测日期和注册日期
     * 2、判断是否是6年内的车辆，不是的返回错误信息
     * 3、调用车行易的/inspectionService/checkInfo，返回 canprocess=1，inspectionType=1，transactType=4
     * 或者 canprocess=2，inspectionType=1，transactType=4 、state=1、2  可以办理
     * 4、如果可以办理，查询违章，如果违章都处理了，反正可以办理
     *
     * @return 是否可以办理
     */
    @PostMapping(CxyConstant.CHECK_INFO)
    fun handleStatus(cxyReq: CxyReq): String {
        val content = cxyReq.content
        val decrypt = CxyUtils.decryptByPassword("3B986530AA1F481097969BEDCA072ADC", content)
        val cxyGetCheckListReq = JSONUtil.toBean(decrypt, CxyCheckInfoReq::class.java)
        val carNumber = cxyGetCheckListReq.carNumber
        val cha = carNumber[carNumber.length - 1].toString() + ""
        val number = NumberUtil.isNumber(cha + "")
        val cxyCheckInfoResp = CxyCheckInfoResp()
        cxyCheckInfoResp.canProcess = CxyEnum.CanProcessEnum.CAN_HANDLER.code
        cxyCheckInfoResp.state = CxyEnum.AnnualInspectionStateEnum.RESERVATION.code
        if (number) {
            val anInt = cha.toInt()
            val state = anInt % 5
            if (state == 0) {
                cxyCheckInfoResp.canProcess = CxyEnum.CanProcessEnum.NOT_HANDLER.code
                cxyCheckInfoResp.state = CxyEnum.AnnualInspectionStateEnum.NOT_RESERVATION.code
            }
            if (state == 3) {
                cxyCheckInfoResp.canProcess = CxyEnum.CanProcessEnum.NOT_HANDLER.code
                cxyCheckInfoResp.state = CxyEnum.AnnualInspectionStateEnum.OVER_MORE_ONE_YEAR.code
            }
            if (state == 4) {
                cxyCheckInfoResp.canProcess = CxyEnum.CanProcessEnum.NOT_HANDLER.code
                cxyCheckInfoResp.state = CxyEnum.AnnualInspectionStateEnum.SERIOUS_OVER.code
            }
            if (state == 5) {
                cxyCheckInfoResp.canProcess = CxyEnum.CanProcessEnum.NOT_HANDLER.code
                cxyCheckInfoResp.state = CxyEnum.AnnualInspectionStateEnum.SCRAPPED.code
            }
        }
        cxyCheckInfoResp.inspectionType = CxyEnum.InspectionTypeEnum.NOT_INSPECTION.code
        val list: MutableList<CxyCheckInfoResp.TransactType> = mutableListOf()
        val transactType = CxyCheckInfoResp.TransactType()
        transactType.transactType = CxyEnum.TransactTypeEnum.NOT_INSPECTION_NOT.code
        list.add(transactType)
        cxyCheckInfoResp.transactTypeList = list
        return CxyUtils.encryptByPassword(
            "3B986530AA1F481097969BEDCA072ADC",
            JSONUtil.toJsonStr(CxyResult.success(cxyCheckInfoResp))
        )
    }

    /**
     * 年检的金额和用户相应最大优惠券金额.
     *
     *
     * 1、客户端传入商品编号、车辆id
     * 2、从redis获取当前用户的id
     * 3、根据用户id获取他相应的最大的优惠券
     *
     * @return 是否可以办理
     */
    @PostMapping(CxyConstant.PAY)
    fun goodsAndCouponMoney(cxyReq: CxyReq?): String {
        val random = Random(2).nextInt(0, 20)
        val cxyPayResp = CxyPayResp()
        cxyPayResp.orderId = RandomStringUtils.random(10, true, false)
        if (random / 2 == 0) {
            return CxyUtils.encryptByPassword(
                "3B986530AA1F481097969BEDCA072ADC",
                JSONUtil.toJsonStr(CxyResult.success(cxyPayResp))
            )
        } else {
            return "<html>503</html>";
        }
    }

    /**
     * 下单.
     *
     *
     * 1、客户端传入年检日期、商品id、地址id、优惠券id
     * 2、获取当前登陆用户信息、查询商品信息、优惠券信息、车辆信息、地址信息
     * 3、生成订单编号
     * 4、如果是支付0元，那么直接调用第三方生成订单，不是0元的话，在微信的回调成功中调用第三方下单
     * 5、发送短信和微信
     *
     * @return 是否可以办理
     */
    @PostMapping(CxyConstant.GET_ORDER)
    fun getOrder(cxyReq: CxyReq): String {
        val content = cxyReq.content
        val decrypt = CxyUtils.decryptByPassword("3B986530AA1F481097969BEDCA072ADC", content)
        val cxyGetCheckListReq = JSONUtil.toBean(decrypt, CxyGetOrderReq::class.java)
        val cxyGetOrderResp = CxyGetOrderResp()
        val exOrderId = cxyGetCheckListReq.exOrderId
        cxyGetOrderResp.exOrderId = exOrderId
        val cha = exOrderId[exOrderId.length - 1].toString() + ""
        val number = NumberUtil.isNumber(cha + "")
        cxyGetOrderResp.orderStatus = CxyEnum.OrderStatusEnum.PAID.code
        if (number) {
            val anInt = cha.toInt()
            val num = anInt % 3
            val list = OrderAnnualInspectionDataServiceImpl().listByOrderId(exOrderId)
            if (num == 0) {
                if (CollectionUtil.isNotEmpty(list)) {
                    //审核不通过
                    val requirement = CxyGetOrderResp.Requirement()
                    requirement.status = CxyEnum.RequirementStatusEnum.AUDIT_FAIL.code
                    requirement.requirementType = CxyEnum.RequirementTypeEnum.JQX.name
                    val list1: MutableList<CxyGetOrderResp.Requirement> = mutableListOf()
                    list1.add(requirement)
                    cxyGetOrderResp.requirementList = list1
                }
            }
            if (num == 1) {
                if (CollectionUtil.isNotEmpty(list)) {
                    //审核通过
                    val list1: MutableList<CxyGetOrderResp.Requirement> = mutableListOf()
                    val requirement = CxyGetOrderResp.Requirement()
                    requirement.status = CxyEnum.RequirementStatusEnum.AUDIT_SUCCESS.code
                    requirement.requirementType = CxyEnum.RequirementTypeEnum.JQX.name
                    list1.add(requirement)
                    val requirement1 = CxyGetOrderResp.Requirement()
                    requirement1.status = CxyEnum.RequirementStatusEnum.AUDIT_SUCCESS.code
                    requirement1.requirementType = CxyEnum.RequirementTypeEnum.XSZ.name
                    list1.add(requirement1)
                    val requirement2 = CxyGetOrderResp.Requirement()
                    requirement2.status = CxyEnum.RequirementStatusEnum.AUDIT_SUCCESS.code
                    requirement2.requirementType = CxyEnum.RequirementTypeEnum.CCS.name
                    list1.add(requirement2)
                    cxyGetOrderResp.requirementList = list1
                }
            }
            if (num == 2) {
                cxyGetOrderResp.orderStatus = CxyEnum.OrderStatusEnum.FINISHED.code
            }
        } else {
            cxyGetOrderResp.orderStatus = CxyEnum.OrderStatusEnum.REFUNDED.code
        }
        cxyGetOrderResp.updateTime = LocalDateTime.now()
        return CxyUtils.encryptByPassword(
            "3B986530AA1F481097969BEDCA072ADC",
            JSONUtil.toJsonStr(CxyResult.success(cxyGetOrderResp))
        )
    }

    /**
     * 付款.
     * 1、调用微信的预支付.
     * 2、在回调处理订单状态
     * 现在没有付款二期再做
     *
     * @return 是否成功
     */
    @PostMapping(CxyConstant.UPDATE_ORDER_REQUIREMENT)
    fun updateOrderRequirement(cxyReq: CxyReq?): String {
        return CxyUtils.encryptByPassword(
            "3B986530AA1F481097969BEDCA072ADC",
            JSONUtil.toJsonStr(CxyResult.successAndTrue())
        )
    }

    /**
     * 保存资料并且上传到车行易.
     *
     *
     * 1、传入上传后的图片url和订单id
     *
     * @return 是否可以办理
     */
    @PostMapping(CxyConstant.UPDATE_ORDER_REQUIREMENT_STATUS)
    fun updateOrderRequirementStatus(cxyReq: CxyReq?): String {
        return CxyUtils.encryptByPassword(
            "3B986530AA1F481097969BEDCA072ADC",
            JSONUtil.toJsonStr(CxyResult.successAndTrue())
        )
    }

    /**
     * 修改资料并且上传到车行易.
     *
     *
     * 1、传入上传后的图片url和订单id
     *
     * @return 是否可以办理
     */
    @PostMapping(CxyConstant.UPDATE_ORDER_CONFIRM_STATUS)
    fun updateOrderConfirmStatus(cxyReq: CxyReq?): String {
        return CxyUtils.encryptByPassword(
            "3B986530AA1F481097969BEDCA072ADC",
            JSONUtil.toJsonStr(CxyResult.successAndTrue())
        )
    }

}