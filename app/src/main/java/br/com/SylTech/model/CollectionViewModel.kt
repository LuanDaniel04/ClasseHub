package br.com.SylTech.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CollectionViewModel : ViewModel() {
    var collections by mutableStateOf<Colecao?>(null)
        private set

    fun select(colecao: Colecao){
        this.collections = colecao
    }
}