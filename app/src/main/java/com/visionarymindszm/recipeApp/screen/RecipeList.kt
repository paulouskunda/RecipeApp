package com.visionarymindszm.recipeApp

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.visionarymindszm.recipeApp.remote.RecipeServiceSDK
import com.visionarymindszm.recipeApp.remote.data.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val recipeServiceSDK = RecipeServiceSDK()


@Composable
fun RecipeList( navController: NavController?=null) {
    val coroutineScope = rememberCoroutineScope()
    var recipeList: List<Recipe>? = null
    LaunchedEffect(recipeList){
            recipeList =  recipeServiceSDK.fetchAllRecipeList()
    }
    if(recipeList != null)
    LazyColumn{items(recipeList!!) {recipe ->
            RecipeCard(recipe = recipe, Modifier.padding(16.dp), navController)
        }
    }
}