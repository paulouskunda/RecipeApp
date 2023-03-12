package com.visionarymindszm.recipeApp.remote.data

import com.visionarymindszm.recipeApp.util.DateAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Ingredients (
    var ingredientsId: Int? = null,
    var ingredientsDetail: String? = null,
    var createdAt: String? = null,
)