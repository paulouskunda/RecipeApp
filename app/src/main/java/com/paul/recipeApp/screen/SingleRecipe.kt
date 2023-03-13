package com.paul.recipeApp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.paul.recipeApp.remote.RecipeServiceSDK
import com.paul.recipeApp.remote.data.Ingredients
import com.paul.recipeApp.remote.data.Instructions
import com.paul.recipeApp.remote.data.Recipe
import com.paul.recipeApp.util.Indicator
import com.paul.recipeApp.util.RecipeUtils

private val recipeServiceSDK = RecipeServiceSDK()

@Composable
fun SingleRecipe(recipeId: Int,navController: NavController?=null){
    val scrollState = rememberScrollState()

    val recipes = produceState(
        initialValue = Recipe(0,"","","","", emptyList(), emptyList()),
        producer = {
            value = recipeServiceSDK.fetchAllRecipeById(recipeId)
        })

    if (recipes.value.recipeId == 0){
        Indicator()
    }else{
        val recipe = recipes.value

        Column(
            modifier = Modifier
                .fillMaxWidth()
               ,
            ) {
            AsyncImage(model = recipe.recipeImage, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp))
            Column(modifier = Modifier.padding(8.dp)
            ){
                Text(text = "Posted At: ${RecipeUtils.formatDate(recipe.createdAt!!)}")
                Text(recipe.recipeName!!, style= MaterialTheme.typography.h4)
                Text("${recipe.recipeInstructions}")
                IngredientsList(recipe = recipe)
                InstructionsList(recipe = recipe)
            }


        }
    }

}

@Composable
fun IngredientsList(recipe: Recipe){
    if (recipe.ingredientsEntity.isNotEmpty()){
        Text(text = "Ingredients",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            style = MaterialTheme.typography.subtitle1)
        LazyColumn{items(recipe.ingredientsEntity) { ingredients ->
            Text(text = "👉🏾 ${ingredients.ingredientsDetail}")
        }
        }
    }
}

@Composable
fun InstructionsList(recipe: Recipe){
    if(recipe.instructionsEntity.isNotEmpty()){
        Text(text = "Instructions",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            style = MaterialTheme.typography.subtitle1)
        LazyColumn{items(recipe.instructionsEntity) { instructions ->
            Text(text = "👉🏾 ${instructions.instructionDetails}")
        }
        }
    }

}

