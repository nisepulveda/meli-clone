package com.nicolas.clonmeli.data.remote

import com.nicolas.clonmeli.domain.MeliResult
import retrofit2.Response
import java.net.UnknownHostException

inline fun <T : Any> executeRetrofitRequest(block: () -> Response<T>): MeliResult<T> {
    return try {
        val request = block.invoke()
        return if (request.isSuccessful && request.body() != null) {
            val body = request.body()
            if (body != null) {
                MeliResult.Success(body)
            } else {
                MeliResult.Error(request.message(), request.code())
            }
        } else {
            val errorBody = request.errorBody()
            val errorText = errorBody?.string() ?: "Error body null"
            MeliResult.Error(errorText, request.code())
        }
    } catch (exception: UnknownHostException) {
        MeliResult.Error(exception.toString())
    }
}

fun <Api: Any, Data: Any> handleResultRetrofit(
    result: MeliResult<Api>,
    onSuccess: (Api) -> Data
): MeliResult<Data> = when (result) {
    is MeliResult.Success -> MeliResult.Success(onSuccess.invoke(result.data!!))
    is MeliResult.Error -> result
}