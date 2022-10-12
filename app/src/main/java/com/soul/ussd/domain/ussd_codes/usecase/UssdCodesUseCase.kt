package com.soul.ussd.domain.ussd_codes.usecase

import kotlinx.coroutines.flow.Flow
import com.soul.ussd.domain.base.BaseNetworkResult
import com.soul.ussd.domain.ussd_codes.UssdCodesRepository
import com.soul.ussd.domain.ussd_codes.models.UssdCodesModel
import javax.inject.Inject

class UssdCodesUseCase @Inject constructor(private val ussdRepository: UssdCodesRepository) {

    suspend fun getServicesResponse(
        lang: String,
        operator: Int,
        type: Int
    ): Flow<BaseNetworkResult<List<UssdCodesModel>>> {
        return ussdRepository.getUssdCodesResponse(lang, operator, type)
    }
}