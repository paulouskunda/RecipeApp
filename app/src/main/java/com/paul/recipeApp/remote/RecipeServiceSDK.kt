package com.paul.recipeApp.remote

import com.paul.recipeApp.remote.data.Recipe


/**
 * The RecipeServiceSDK file is a Facade for the API,which exposes the
 * API endpoint.
 */

class RecipeServiceSDK {

    private val recipeService = RecipeService.create()

    suspend fun fetchAllRecipeList(): List<Recipe> {
        return recipeService.fetchAllRecipes().message
    }

    suspend fun fetchAllRecipeById(recipeId: Int): Recipe {
        return recipeService.fetchRecipeById(recipeId).message

    }


}