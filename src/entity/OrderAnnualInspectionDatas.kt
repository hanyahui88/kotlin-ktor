package entity

import com.alvin.entity.OrderAnnualInspectionData
import me.liuwj.ktorm.schema.*

/**
 * 年检订单办理资料表
 *
 * @author yahui
 * @date 2020-07-07 14:54:46
 */
object OrderAnnualInspectionDatas : Table<OrderAnnualInspectionData>("order_annual_inspection_data") {
    /**
     * 自增主键
     */
    val id = long("id").primaryKey().bindTo { it.id }

    /**
     * 订单 id
     */
    val orderId = long("order_id").bindTo { it.orderId }

    /**
     * 订单办理资料类型 核销资料verification_dat 行驶证正面vehicle_license_fron 行驶证反面vehicle_license_contrar 交强险保单compulsory_insuranc 车船税发票vehicle_and_vessel_tax
     */
    val dataType = varchar("data_type").bindTo { it.dataType }

    /**
     * 订单办理图片 url
     */
    val imgUrl = varchar("img_url").bindTo { it.imgUrl }

    /**
     * 是否审核通过  0 待审核  1 通过  2未通过
     */
    val isReview = varchar("is_review").bindTo { it.isReview }

    /**
     * 创建时间
     */
    val createTime = datetime("create_time").bindTo { it.createTime }

    /**
     * 最后一次更新时间
     */
    val updateTime = datetime("update_time").bindTo { it.updateTime }

    /**
     * 删除标识 1删除 0未删除
     */
    val isRemoved = int("is_removed").bindTo { it.isRemoved }

    /**
     * 订单类型: 线下核销订单 OFFLINE 线上年检订单 ANNUAL_INSPECTION
     */
    val type = varchar("type").bindTo { it.type }
}
