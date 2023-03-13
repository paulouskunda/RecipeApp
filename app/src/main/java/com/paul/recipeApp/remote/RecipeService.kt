package com.paul.recipeApp.remote

import com.paul.recipeApp.remote.data.Recipe
import com.paul.recipeApp.remote.data.Response
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface RecipeService {
    suspend fun fetchAllRecipes(): Response<List<Recipe>>

    suspend fun fetchRecipeById(recipeId: Int):  Response<Recipe>

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