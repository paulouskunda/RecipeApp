package com.visionarymindszm.recipeApp

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.visionarymindszm.recipeApp.remote.data.Recipe

@Composable
fun RecipeCard(recipe: Recipe, modifier: Modifier,navController: NavController?=null){
    Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(model = recipe.recipeImage, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(recipe.recipeName!!, style= MaterialTheme.typography.h4)
                Text(". ${recipe.recipeInstructions}")

            }
        }
    }
}



@Composable
@Preview
fun DefaultRecipeCard(){
//    RecipeCard(recipe = defaultRecipes[0],Modifier.padding(16.dp))
}