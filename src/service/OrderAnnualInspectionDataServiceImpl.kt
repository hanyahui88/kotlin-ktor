package com.alvin.service

import com.alvin.dao.OrderAnnualInspectionDataDao
import entity.OrderAnnualInspectionData


/**
 * 年检订单办理资料表
 *
 * @author yahui
 * @date 2020-07-07 14:54:46
 */
open class OrderAnnualInspectionDataServiceImpl {

    fun listByOrderId(orderId: String): List<OrderAnnualInspectionData> {
        return OrderAnnualInspectionDataDao().listByOrderId(orderId)
    }
}
