package com.visionarymindszm.recipeApp.remote

import com.visionarymindszm.recipeApp.remote.data.Recipe


class RecipeServiceSDK {

    private val recipeService = RecipeService.create()

    suspend fun fetchAllRecipeList(): List<Recipe> {
       return recipeService.fetchAllRecipes().message
    }

    suspend fun fetchAllRecipeById(recipeId: Int): Recipe {
        val response = recipeService.fetchRecipeById(recipeId)
        return  if (response.message is String){
            Recipe(0,"","","", "", emptyList())
        }else {
            response.message as Recipe
        }
    }


}