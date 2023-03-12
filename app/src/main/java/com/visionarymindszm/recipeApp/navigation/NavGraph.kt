package com.visionarymindszm.recipeApp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.visionarymindszm.recipeApp.RecipeList
import com.visionarymindszm.recipeApp.screen.SingleRecipe

@Composable
fun NavGraph(
   startDestination: String = NavScreen.RecipeList.route
){
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavScreen.RecipeList.route) {
                RecipeList(navController) //{navController.navigate(NavScreen.PropertyDetails.route)}
            }
            composable("${NavScreen.SingleRecipe.route}/{recipeId}",
                arguments = listOf(navArgument("recipeId"){
                    type = NavType.IntType
                })) {

                val recipeId = it.arguments?.getInt("recipeId")!!
                SingleRecipe(recipeId = recipeId, navController= navController )

            }
        }
    }
}