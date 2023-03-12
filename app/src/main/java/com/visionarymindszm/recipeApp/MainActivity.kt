package com.visionarymindszm.recipeApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.visionarymindszm.recipeApp.data.defaultRecipes
import com.visionarymindszm.recipeApp.remote.RecipeServiceSDK
import com.visionarymindszm.recipeApp.remote.data.Recipe
import com.visionarymindszm.recipeApp.ui.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column(modifier = Modifier.fillMaxSize()) {
                // 2
                TopAppBar(title = {
                    Text("Zed Recipes Hand Book")
                })

                RecipeList()
            }
//            CookBookComposeTheme {
//
//            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecipeAppTheme {
//        Recipe/List(recipes = defaultRecipes)
    }
}