package com.paul.recipeApp.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// we can not annotate this response with @Serializable because of the Any type
@Serializable
data class Response<T>(
    @SerialName("statusCode") val statusCode:  Int,
    @SerialName("message")  val message: T
): BaseResponse<T>

interface BaseResponse<T>
