package com.paul.recipeApp.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paul.recipeApp.remote.RecipeServiceSDK
import com.paul.recipeApp.remote.data.Recipe
import com.paul.recipeApp.util.Indicator

private val recipeServiceSDK = RecipeServiceSDK()

/**
 * produceState allows us to observe elements from observable sources that can be suspended or not suspended
 * Jetpack recomposes once the recipe list moves from empty to a list with elements.
 * The produceState only handles the initialValue provided, hence we can check using the produce
 * if the list is empty or not
 */

@Composable
fun RecipeList( navController: NavController?=null) {
    val recipe = produceState<List<Recipe>>(
        initialValue = emptyList(),
        producer = {
            value = recipeServiceSDK.fetchAllRecipeList()
        })

    if(recipe.value.isEmpty()) {
        Indicator()
    }else {
        LazyColumn {
            items(recipe.value) { recipe ->
                RecipeCard(recipe = recipe, Modifier.padding(16.dp), navController)
            }
        }
    }
}