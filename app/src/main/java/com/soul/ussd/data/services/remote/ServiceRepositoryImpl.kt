package com.soul.ussd.data.services.remote

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.soul.ussd.data.services.remote.api.ServiceApi
import com.soul.ussd.domain.base.BaseNetworkResult
import com.soul.ussd.domain.services.ServiceRepository
import com.soul.ussd.domain.services.models.ServicesModel
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(private val serviceApi: ServiceApi) :
    ServiceRepository {

    override suspend fun getServicesResponse(
        lang: String,
        operator: Int,
        type: Int
    ): Flow<BaseNetworkResult<List<ServicesModel>>> {
        return flow {
            try {
                emit(BaseNetworkResult.Loading(true))
                val response = serviceApi.getServices()
                emit(BaseNetworkResult.Loading(false))
                if (response.code() == 200) {
                    emit(BaseNetworkResult.Success(response.body()!!.map {
                        ServicesModel(
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
//                            it.type == type && it.lang == lang && it.operator == operator
//                        }
                } else {
                    emit(BaseNetworkResult.Error("Error"))
                }
            } catch (e: Exception) {
                Log.d("DDDD", "getServicesResponse: ccccccccc $e")
                emit(BaseNetworkResult.Error("Error"))
            }
        }
    }
}