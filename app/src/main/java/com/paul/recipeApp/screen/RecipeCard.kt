package com.paul.recipeApp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.AsyncImage
import com.paul.recipeApp.navigation.NavRoutes
import com.paul.recipeApp.remote.data.Recipe
import com.paul.recipeApp.ui.theme.Purple200

@Composable
fun RecipeCard(recipe: Recipe, modifier: Modifier,navController: NavController?=null){
    Surface(shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = modifier
            .clickable(onClick = {
                navController
                    ?.navigate("${NavRoutes.SingleRecipe.name}/${recipe.recipeId!!}"){
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
            })

    ) {
        Column(modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Purple200)) {
            AsyncImage(model = recipe.recipeImage, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp))
            Column(
                modifier = Modifier
                .padding(16.dp)
            ) {
                Text(recipe.recipeName!!,
                     modifier= Modifier,
                    style= MaterialTheme.typography.h4)
                Text("${recipe.recipeInstructions}")
            }
        }
    }
}