package com.alvin.dao

import entity.OrderAnnualInspectionData
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.dsl.*


class OrderAnnualInspectionDataDao {
    fun listByOrderId(orderId: Long): List<OrderAnnualInspectionData> {
        val database =
            Database.connect("jdbc:mysql://localhost:3306/vosp?useUnicode=true&characterEncoding=UTF-8&useSSL=false&?user=root&password=root")
        return database
            .from(OrderAnnualInspectionData)
            .select()
            .where { OrderAnnualInspectionData.orderId eq orderId }
            .map { row -> OrderAnnualInspectionData.createEntity(row) }
    }
}