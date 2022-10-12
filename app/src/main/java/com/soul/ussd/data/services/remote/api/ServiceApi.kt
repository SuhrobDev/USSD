package com.soul.ussd.data.services.remote.api

import retrofit2.Response
import retrofit2.http.GET
import com.soul.ussd.data.services.remote.dto.ServiceResponse


interface ServiceApi {

    @GET("index.php?r=ussd%2Fjson")
    suspend fun getServices(): Response<List<ServiceResponse>>

}