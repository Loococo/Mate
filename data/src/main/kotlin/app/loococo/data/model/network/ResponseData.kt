package app.loococo.data.model.network

import app.loococo.domain.model.network.Resource
import app.loococo.domain.model.network.ResourceException
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

data class ResponseData<T>(
    @field:SerializedName("data")
    val data: T? = null,

    @field:SerializedName("msgCode")
    val msgCode: String? = null,

    @field:SerializedName("msg")
    val msg: String? = null
)

data class ErrorHandler(
    @field:SerializedName("code")
    val code: String,
    @field:SerializedName("message")
    val message: String
)

suspend fun <T : Any> suspendResponseResult(
    execute: suspend () -> Response<ResponseData<T>>
): Flow<Resource<T>> = flow {
    val gson = GsonBuilder().create()

    try {
        val response = execute()
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.data != null) {
                emit(Resource.Success(body.data))
            } else {
                emit(Resource.Error(ResourceException.UnknownException))
            }
        } else {
            try {
                val wrapper: ErrorHandler = gson.fromJson(
                    response.errorBody()!!.charStream(),
                    ErrorHandler::class.java
                )
                emit(Resource.Error(ResourceException.HttpException(wrapper.code, wrapper.message)))
            } catch (e: Exception) {
                emit(Resource.Error(ResourceException.UnknownException))
            }
        }
    } catch (e: Exception) {
        emit(Resource.Error(ResourceException.NetworkException))
    }
}