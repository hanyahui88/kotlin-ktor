package com.alvin.entity



/**
 * 查询支持年审办理城市列表
 *
 * @author yahui
 */

class ValiableListResp {
     val prefix: String? = null
     val provinceName: String? = null
     val provinceId: String? = null
     val isMerchant: Int? = null
     val city: List<CityBean>? = null


    class CityBean {
        /**
         * cityName : 北京
         * prefix : 京
         * cityId : 11
         * transactTypeList : [{"transactType":"1","transactName":"普通代办"}]
         */
         val prefix: String? = null
         val cityId: String? = null
         val isMerchant: Int? = null
         val transactTypeList: List<TransactTypeListBean>? = null
         val cityName: String? = null
    }


    class TransactTypeListBean {
        /**
         * transactType : 1
         * transactName : 普通代办
         */
         val isMerchant: Int? = null
         val transactType: String? = null
         val transactName: String? = null
    }
}