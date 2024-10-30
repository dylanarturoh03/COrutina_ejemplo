package com.example.corutinas_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.corutinas_app.ui.theme.CORutinas_appTheme
import com.example.corutinas_app.ui.theme.ItemsViewModel
import com.example.corutinas_app.ui.theme.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: ItemsViewModel by viewModels()
        setContent {
            CORutinas_appTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background)
                {
                    ItemsView(viewModel)
                }
            }
        }
    }
}

@Composable
fun Content(viewModel: MainViewModel){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BotonColor()
        Text(text=viewModel.resultState)
        
        if(viewModel.isLoading){
            CircularProgressIndicator()
        }else{
            Text(text = viewModel.resultState)
        }
        
        Button(onClick = { viewModel.fetchData() }) {
            Text(text =  "Llamar API")
        }
    }
}

@Composable
fun BotonColor(){
    var color by remember { mutableStateOf(false) }
    
    Button(onClick = { color = !color }, colors= ButtonDefaults.buttonColors(
        containerColor = if(color) Color.Blue else Color.Red
    )) {
        Text(text = "Cambiar Color")
    }
}

@Composable
fun ItemsView(viewModel: ItemsViewModel){
    val itemsList=viewModel.itemList
    Column {
        if(viewModel.isLoading){
            CircularProgressIndicator()
        }else{
            LazyColumn {
                items(itemsList){item->
                    Text(text = item.name)
                }
            }
        }
    }
}