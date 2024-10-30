package com.example.corutinas_app.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    var resultState by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun BloqueoApp(){
        Thread.sleep(5000)
        resultState = "Bloqueo terminado."
    }

    fun fetchData(){
        viewModelScope.launch {

            try{
                isLoading = true
                llamarAPI()
            }
            catch (e:Exception){
                println("Error: ${e.message}")
            }
            finally {
                isLoading = false
            }
        }

    }

    private suspend fun llamarAPI(){

        val result= withContext(Dispatchers.IO){
            delay(5000)
            "Respuesta de la API"
        }
        resultState = result
    }

}