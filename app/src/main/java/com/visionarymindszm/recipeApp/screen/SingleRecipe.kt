package com.visionarymindszm.recipeApp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.visionarymindszm.recipeApp.remote.RecipeServiceSDK
import com.visionarymindszm.recipeApp.remote.data.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val recipeServiceSDK = RecipeServiceSDK()

@Composable
fun SingleRecipe(recipeId: Int,navController: NavController?=null){
    val coroutineScope = rememberCoroutineScope()
    var recipe: Recipe? = null
    LaunchedEffect(recipe){
        coroutineScope.launch(Dispatchers.IO) {
            recipe =  recipeServiceSDK.fetchAllRecipeById(recipeId)
        }
    }

    Column {
        AsyncImage(model = null, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp))
        Text(text = "Posted At:")
        Text(recipe?.recipeName!!, style= MaterialTheme.typography.h4)
        Text(". ${recipe?.recipeInstructions}")
        if (recipe?.ingredientsEntity?.size!! > 0){
            LazyColumn{items(recipe?.ingredientsEntity!!) {ingredients ->
                Text(text = "👉🏾 ${ingredients.ingredientsDetail}")
            }
            }
        }

    }
}