package com.soul.ussd.data.ussd_codes

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.soul.ussd.data.ussd_codes.remote.api.UssdCodesApi
import com.soul.ussd.domain.base.BaseNetworkResult
import com.soul.ussd.domain.ussd_codes.UssdCodesRepository
import com.soul.ussd.domain.ussd_codes.models.UssdCodesModel
import javax.inject.Inject

class UssdCodesRepositoryImpl @Inject constructor(private val serviceApi: UssdCodesApi) :
    UssdCodesRepository {

    override suspend fun getUssdCodesResponse(
        lang: String,
        operator: Int,
        type: Int
    ): Flow<BaseNetworkResult<List<UssdCodesModel>>> {
        return flow {
            try {
                emit(BaseNetworkResult.Loading(true))
                val response = serviceApi.getUssdCodes()
                emit(BaseNetworkResult.Loading(false))
                if (response.code() == 200) {
                    emit(BaseNetworkResult.Success(response.body()!!.map {
                        UssdCodesModel(
                            it.id,
                            it.title,
                            it.content,
                            it.lang,
                            it.operator,
                            it.code,
                            it.type,
                            it.full_content
                        )
                    }))
//                        .takeWhile {
//                            it.type == type
//                        }
                } else {
                    emit(BaseNetworkResult.Error("Xatolik"))
                }
            } catch (e: Exception) {
                Log.d("DDDD", "getServicesResponse: ccccccccc $e")
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }
}