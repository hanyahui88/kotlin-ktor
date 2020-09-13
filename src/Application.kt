package com.alvin

import cn.hutool.json.JSONUtil
import com.alvin.entity.CxyReq
import com.alvin.web.CxyController
import com.botpy.vosp.common.gateway.constants.CxyConstant
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.webjars.*
import java.time.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.request.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(Webjars) {
        path = "/webjars" //defaults to /webjars
        zone = ZoneId.systemDefault() //defaults to ZoneId.systemDefault()
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    val cxyController: CxyController = CxyController()

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/webjars") {
            call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
        post(CxyConstant.GET_CHECK_LIST) {
            val po = call.receive<CxyReq>()
            log.info(JSONUtil.toJsonStr(po))
            call.respond(cxyController.checkDateList(po))
        }
        post(CxyConstant.CHECK_INFO) {
            val po = call.receive<CxyReq>()
            call.respond(cxyController.handleStatus(po))
        }
        post(CxyConstant.PAY) {
            val po = call.receive<CxyReq>()
            call.respond(cxyController.goodsAndCouponMoney(po))
        }
        post(CxyConstant.GET_ORDER) {
            val po = call.receive<CxyReq>()
            call.respond(cxyController.getOrder(po))
        }
        post(CxyConstant.UPDATE_ORDER_REQUIREMENT) {
            val po = call.receive<CxyReq>()
            call.respond(cxyController.updateOrderRequirement(po))
        }
        post(CxyConstant.UPDATE_ORDER_REQUIREMENT_STATUS) {
            val po = call.receive<CxyReq>()
            call.respond(cxyController.updateOrderRequirementStatus(po))
        }
        post(CxyConstant.UPDATE_ORDER_CONFIRM_STATUS) {
            val po = call.receive<CxyReq>()
            call.respond(cxyController.updateOrderConfirmStatus(po))
        }

    }
}

