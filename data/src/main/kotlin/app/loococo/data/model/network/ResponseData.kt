package app.loococo.data.model.network

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

data class ResponseData<T>(
    @field:SerializedName("data")
    val data: T? = null,

    @field:SerializedName("msgCode")
    val msgCode: String? = null,

    @field:SerializedName("msg")
    val msg: String? = null
)

sealed class ResponseResult<out T> {
    class Success<out T>(val value: T) : ResponseResult<T>()
    data class Error(val code: Int, val message: String?) : ResponseResult<Nothing>()
    data class Exception(val exception: Throwable?) : ResponseResult<Nothing>()
}

suspend fun <T : Any> suspendResponseResult(
    execute: suspend () -> Response<ResponseData<T>>
): Flow<ResponseResult<T>> = flow {
    try {
        val response = execute()
        val body = response.body()
        val data = response.body()?.data
        if (response.isSuccessful && body != null && data != null) {
            emit(ResponseResult.Success(data))
        } else {
            emit(ResponseResult.Error(code = response.code(), message = response.message()))
        }
    } catch (e: HttpException) {
        emit(ResponseResult.Error(code = e.code(), message = e.message()))
    } catch (e: Throwable) {
        emit(ResponseResult.Exception(e))
    }
}