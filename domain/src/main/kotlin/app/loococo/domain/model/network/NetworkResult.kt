package app.loococo.domain.model.network

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: ResourceException) : Resource<Nothing>()
    data class Message(val message: String) : Resource<Nothing>()
}

sealed class ResourceException {
    data class HttpException(val code: String, val message: String) : ResourceException()
    data object UnknownException : ResourceException()
    data object NetworkException : ResourceException()
}