package com.visionarymindszm.recipeApp.remote.data

import com.visionarymindszm.recipeApp.util.DateAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ResponseRecipeList(
    var statusCode: Int? = null,
    var message: List<Recipe>
)

@Serializable
data class Recipe (
    var recipeId: Int? = null,
    var recipeName: String? = null,
    var recipeInstructions: String? = null,
    var recipeImage: String? = null,
    var createdAt: String? = null,
    var ingredientsEntity: List<Ingredients>
)

