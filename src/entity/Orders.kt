package entity

import com.alvin.entity.Order
import com.baomidou.mybatisplus.annotation.TableId
import me.liuwj.ktorm.schema.*
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

/**
 * 订单主表
 *
 * @author yahui
 * @date 2020-07-07 14:54:46
 */
object Orders : Table<Order>("order") {
    /**
     * 自增主键
     */
    val id = long("id").primaryKey().bindTo { it.id }

    /**
     * 订单唯一编号，由时间戳（年月日时）+4位不重复的随机数字
     */
    val number = varchar("number").bindTo { it.number }

    /**
     * 订单类型: 线下核销订单 OFFLINE 线上年检订单 ANNUAL_INSPECTION
     */
    val type = varchar("number").bindTo { it.number }

    /**
     * 客户 id
     */
    val customerId = long("id").bindTo { it.id }

    /**
     * 车辆 id
     */
    val vehicleId = long("id").bindTo { it.id }

    /**
     * 商品 id
     */
    val goodsId = long("id").bindTo { it.id }

    /**
     * 支付方式: 微信 JSAPI 支付 WX_JS_API
     */
    val payType = varchar("number").bindTo { it.number }

    /**
     * 支付流水号
     */
    val tradeId = varchar("number").bindTo { it.number }

    /**
     * 支付状态: CANCEL 用户取消付款,PAY_TIME_OUT 支付超时, PENDING 待付款, PAID 已付款, NO_PAYMENT 不需付款, PENDING_REFUND 等待退款, REFUNDED 已退款
     */
    val payStatus = varchar("number").bindTo { it.number }

    /**
     * 订单状态: CREATED 新建, PROCESSING 处理中, SUCCESS 成功, FAILURE 失败, CLOSED 超时关单, CANCEL 已取消
     */
    val status = varchar("number").bindTo { it.number }

    /**
     * 卡券 id
     */
    val couponId = long("id").bindTo { it.id }

    /**
     * 第三方订单唯一标识
     */
    val thirdPartyOrderNo = varchar("number").bindTo { it.number }

    /**
     * 商品原价 单位为元
     */
    val originalPrice = decimal("id").bindTo { it.originalPrice }

    /**
     * 销售价格 单位为元
     */
    val salesPrice = decimal("id").bindTo { it.salesPrice }

    /**
     * 商品成本价 单位为元
     */
    val costPrice = decimal("id").bindTo { it.costPrice }

    /**
     * 优惠券价格 单位为元
     */
    val couponPrice = decimal("id").bindTo { it.couponPrice }

    /**
     * 实付金额 单位为元
     */
    val actuallyPaid = decimal("id").bindTo { it.actuallyPaid }

    /**
     * 创建时间
     */
    val createTime = datetime("updateTime").bindTo { it.updateTime }

    /**
     * 最后一次更新时间
     */
    val updateTime = datetime("updateTime").bindTo { it.updateTime }

    /**
     * 删除标识 1删除 0未删除
     */
    val isRemoved = int("is_removed").bindTo { it.isRemoved }

    /**
     * 商品分类 id
     */
    val goodsCategoryId = long("id").bindTo { it.id }

    /**
     * 供应商 id
     */
    val supplierId = long("id").bindTo { it.id }

    /**
     * 归属机构 id
     */
    val organizationId = long("id").bindTo { it.id }

    /**
     * 支付时间
     */
    val payTime = datetime("updateTime").bindTo { it.updateTime }

    /**
     * 完成时间
     */
    val finishedTime = datetime("updateTime").bindTo { it.updateTime }

}