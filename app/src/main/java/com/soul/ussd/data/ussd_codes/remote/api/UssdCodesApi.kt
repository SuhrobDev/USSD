package com.soul.ussd.data.ussd_codes.remote.api

import retrofit2.Response
import retrofit2.http.GET
import com.soul.ussd.data.ussd_codes.remote.dto.UssdCodesResponse


interface UssdCodesApi {

    @GET("index.php?r=ussd%2Fjson")
    suspend fun getUssdCodes(): Response<List<UssdCodesResponse>>

}