package app.loococo.data.model.network

import android.util.Log
import app.loococo.domain.model.Resource
import app.loococo.domain.model.ResourceException
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

data class ResponseData<T>(
    @field:SerializedName("data")
    val data: T? = null,

    @field:SerializedName("msgCode")
    val msgCode: String? = null,

    @field:SerializedName("msg")
    val msg: String? = null
)

suspend fun <T : Any> suspendResponseResult(
    execute: suspend () -> Response<ResponseData<T>>
): Flow<Resource<T>> = flow {
    emit(
        try {
            val response = execute()
            if (response.isSuccessful) {
                response.body()?.data?.let {
                    Resource.Success(it)
                } ?: Resource.Error(ResourceException.NoDataException)
            } else {
                Resource.Error(ResourceException.HttpException(response.code(), response.message()))
            }
        } catch (e: IOException) {
            Resource.Error(ResourceException.NetworkException(e))
        } catch (e: Exception) {
            Resource.Error(ResourceException.UnknownException(e))
        }
    )
}