package com.soul.ussd.domain.services

import kotlinx.coroutines.flow.Flow
import com.soul.ussd.domain.base.BaseNetworkResult
import com.soul.ussd.domain.services.models.ServicesModel

interface ServiceRepository {

    suspend fun getServicesResponse(
        lang: String,
        operator: Int,
        type: Int
    ): Flow<BaseNetworkResult<List<ServicesModel>>>
}