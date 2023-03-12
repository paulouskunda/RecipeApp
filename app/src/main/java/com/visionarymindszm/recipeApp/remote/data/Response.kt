package com.visionarymindszm.recipeApp.remote.data

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.*

// we can not annotate this response with @Serializable because of the Any type
//@Serializable(with = ResponseSerializer::class)
data class Response(
    @SerialName("statusCode") val statusCode:  Int,
    @SerialName("message")  val message: Any
)


object ResponseSerializer : KSerializer<Response> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Response") {
        element("statusCode", serialDescriptor<Int>())
        element("message", buildClassSerialDescriptor("Any"))
    }

    @Suppress("UNCHECKED_CAST")
    private val dataTypeSerializers: Map<Int, KSerializer<Any>> =
        mapOf(
            200 to serializer<Int>(),
            500 to serializer<Int>(),
            400 to serializer<Int>(),
            300 to serializer<Int>(),

            ).mapValues { (_, v) -> v as KSerializer<Any> }

    private fun getPayloadSerializer(statusCode: Int): KSerializer<Any> = dataTypeSerializers[statusCode]
        ?: throw SerializationException("Serializer for class $statusCode is not registered in Response")

    override fun serialize(encoder: Encoder, value: Response) {
        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, value.statusCode)
            encodeSerializableElement(descriptor, 1, getPayloadSerializer(value.statusCode), value.message)
        }
    }

    @ExperimentalSerializationApi
    override fun deserialize(decoder: Decoder): Response = decoder.decodeStructure(descriptor) {
        if (decodeSequentially()) {
            val statusCode = decodeIntElement(descriptor, 0)
            val payload = decodeSerializableElement(descriptor, 1, getPayloadSerializer(statusCode))
            Response (statusCode, payload)
        } else {
            require(decodeElementIndex(descriptor) == 0) { "dataType field should precede payload field" }
            val statusCode = decodeIntElement(descriptor, 0)
            val message = when (val index = decodeElementIndex(descriptor)) {
                1 -> decodeSerializableElement(descriptor, 1, getPayloadSerializer(statusCode))
                CompositeDecoder.DECODE_DONE -> throw SerializationException("message field is missing")
                else -> error("Unexpected index: $index")
            }
            Response(statusCode, message)
        }
    }
}
