package com.alvin.entity



/**
 * 车行易返回对象.
 *
 * @author yahui
 */

class CxyResult {
    var code: String = ""
    var msg: String = ""
    var data: Any = ""
    var exCode: String = ""


    class Result {
        /**
         * 请求结果标记
         * 0=请求失败
         * 1=更新成功
         */
        var resultFlag: Int = 0
    }

    companion object {
        fun success(data: Any): CxyResult {
            val result = CxyResult()
            result.code = "0"
            result.msg = "成功"
            result.data = data
            return result
        }

        fun success(): CxyResult {
            val result = CxyResult()
            result.code = "0"
            result.msg = "成功"
            return result
        }

        fun successAndTrue(): CxyResult {
            val result = CxyResult()
            result.code = "0"
            result.msg = "成功"
            val result1 = Result()
            result1.resultFlag = 1
            result.data = result1
            return result
        }
    }
}