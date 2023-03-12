package com.visionarymindszm.recipeApp.remote

import com.visionarymindszm.recipeApp.remote.data.Response
import com.visionarymindszm.recipeApp.remote.data.ResponseRecipeList
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface RecipeService {
    suspend fun fetchAllRecipes(): ResponseRecipeList

    suspend fun fetchRecipeById(recipeId: Int): Response

    companion object {
        fun create(): RecipeService {
            return RecipeServiceImp(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}