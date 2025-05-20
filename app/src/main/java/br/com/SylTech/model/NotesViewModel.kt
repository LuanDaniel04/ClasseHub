package br.com.SylTech.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {
    var notes by mutableStateOf<Notes?>(null)
        private set

    fun select(notes: Notes){
        this.notes = notes
    }
}