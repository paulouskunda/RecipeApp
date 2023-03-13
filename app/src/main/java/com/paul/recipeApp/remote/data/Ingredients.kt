package com.paul.recipeApp.remote.data

import kotlinx.serialization.Serializable

@Serializable
class Ingredients (
    var ingredientsId: Int? = null,
    var ingredientsDetail: String? = null,
    var createdAt: String? = null,
)