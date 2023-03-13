package com.paul.recipeApp.remote

import com.paul.recipeApp.remote.data.Recipe
import com.paul.recipeApp.remote.data.Response
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*


class RecipeServiceImp(
    private val client: HttpClient
) : RecipeService{
    override suspend fun fetchAllRecipes(): Response<List<Recipe>> {
        return try {
            client.get { url(HttpRoutes.fetchAllRecipes) }
        }catch (e: RedirectResponseException){
            println("Error Redirection : ${e.response.status.description}")
            Response(300, emptyList())
        }catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error Client : ${e.response.status.description}")
            Response(400, emptyList())

        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            Response(500, emptyList(), )

        } catch(e: Exception) {
            println("Error General: ${e.message}")
            Response(500, emptyList())

        }
    }

    override suspend fun fetchRecipeById(recipeId: Int): Response<Recipe> {
        return try {
            client.get { url("${HttpRoutes.fetchRecipeById}/$recipeId") }
        }catch (e: RedirectResponseException){
            println("Error Redirection : ${e.response.status.description}")
            Response(500, Recipe(-1, "","","","", emptyList(), emptyList()))
        }catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error Client : ${e.response.status.description}")
            Response(500, Recipe(-1, "","","","", emptyList(), emptyList()))

        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            Response(500, Recipe(-1, "","","","", emptyList(), emptyList()))

        } catch(e: Exception) {
            println("Error General: ${e.message}")
            Response(500, Recipe(-1, "","","","", emptyList(), emptyList()))

        }
    }


}