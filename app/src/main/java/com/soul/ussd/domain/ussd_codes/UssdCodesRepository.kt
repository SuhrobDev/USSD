package com.soul.ussd.domain.ussd_codes

import kotlinx.coroutines.flow.Flow
import com.soul.ussd.domain.base.BaseNetworkResult
import com.soul.ussd.domain.ussd_codes.models.UssdCodesModel

interface UssdCodesRepository {

    suspend fun getUssdCodesResponse(
        lang: String,
        operator: Int,
        type: Int
    ): Flow<BaseNetworkResult<List<UssdCodesModel>>>
}