package com.paul.recipeApp.remote

object HttpRoutes {
    // change the base url if you are not running the application on the emulator
    private const val BASE_URL = "http://10.0.2.2:8080"
    const val fetchAllRecipes = "$BASE_URL/api/recipe"
    const val fetchRecipeById = "$BASE_URL/api/recipe/fetchRecipeById"
}