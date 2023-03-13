package com.paul.recipeApp.remote.data

import kotlinx.serialization.Serializable


@Serializable
data class Recipe (
    var recipeId: Int? = null,
    var recipeName: String? = null,
    var recipeInstructions: String? = null,
    var recipeImage: String? = null,
    var createdAt: String? = null,
    var ingredientsEntity: List<Ingredients>,
    var instructionsEntity: List<Instructions>
)

