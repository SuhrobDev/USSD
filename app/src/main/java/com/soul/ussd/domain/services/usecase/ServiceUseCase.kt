package com.soul.ussd.domain.services.usecase

import kotlinx.coroutines.flow.Flow
import com.soul.ussd.domain.base.BaseNetworkResult
import com.soul.ussd.domain.services.ServiceRepository
import com.soul.ussd.domain.services.models.ServicesModel
import javax.inject.Inject

class ServiceUseCase @Inject constructor(private val ussdRepository: ServiceRepository) {

    suspend fun getServicesResponse(
        lang: String,
        operator: Int,
        type: Int
    ): Flow<BaseNetworkResult<List<ServicesModel>>> {
        return ussdRepository.getServicesResponse(lang, operator, type)
    }
}