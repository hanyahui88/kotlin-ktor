package com.alvin.dao

import entity.OrderAnnualInspectionData
import entity.Orders
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.dsl.*


class OrderAnnualInspectionDataDao {

    fun listByOrderId(num: String): List<OrderAnnualInspectionData> {
        val database =
            Database.connect("jdbc:mysql://localhost:3306/vosp?useUnicode=true&characterEncoding=UTF-8&useSSL=false&?user=root&password=root")
        return database
            .from(OrderAnnualInspectionData)
            .innerJoin(Orders, on = OrderAnnualInspectionData.orderId eq Orders.id)
            .select(OrderAnnualInspectionData.columns)
            .where { Orders.number eq num }
            .map { row -> OrderAnnualInspectionData.createEntity(row) }
    }
}
