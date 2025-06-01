package br.com.SylTech.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ReminderViewModel : ViewModel() {
    var reminder by mutableStateOf<Reminder?>(null)
        private set

    fun select(reminder: Reminder) {
        this.reminder = reminder
    }
}