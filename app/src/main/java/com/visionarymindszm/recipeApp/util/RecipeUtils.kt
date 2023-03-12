package com.visionarymindszm.recipeApp.util

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

object DateAsStringSerializer : KSerializer<Date> {

    override val descriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(decoder: Decoder): Date {
        val dateString = "2023-02-08T15:00:04.000+00:00"
        val dateFormat = SimpleDateFormat("yyyy-MM-d HH:mm", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        println("Date $date")
        return date!! // Date.from(dateTime.toInstant(ZoneOffset.UTC))
    }

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(value.toString())
    }
}