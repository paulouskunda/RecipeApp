package com.visionarymindszm.recipeApp.remote

import com.visionarymindszm.recipeApp.remote.data.Response
import com.visionarymindszm.recipeApp.remote.data.ResponseRecipeList
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*


class RecipeServiceImp(
    private val client: HttpClient
) : RecipeService{
    override suspend fun fetchAllRecipes(): ResponseRecipeList {
        return try {
            client.get { url(HttpRoutes.fetchAllRecipes) }
        }catch (e: RedirectResponseException){
            println("Error Redirection : ${e.response.status.description}")
            ResponseRecipeList(300, emptyList())
        }catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error Client : ${e.response.status.description}")
            ResponseRecipeList(400, emptyList())

        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            ResponseRecipeList(500, emptyList())

        } catch(e: Exception) {
            println("Error General: ${e.message}")
            ResponseRecipeList(500, emptyList())

        }
    }

    override suspend fun fetchRecipeById(recipeId: Int): Response {
        TODO("Not yet implemented")
    }

}