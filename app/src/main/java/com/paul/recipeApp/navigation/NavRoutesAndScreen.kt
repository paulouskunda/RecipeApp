package com.paul.recipeApp.navigation

enum class NavRoutes{
    RecipeList,
    SingleRecipe
}

sealed class NavScreen(val route:String){
    object RecipeList: NavScreen(NavRoutes.RecipeList.name)
    object SingleRecipe: NavScreen(NavRoutes.SingleRecipe.name)
}