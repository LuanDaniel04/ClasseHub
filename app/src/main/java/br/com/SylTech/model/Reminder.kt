package br.com.SylTech.model

data class Reminder(
    var id: Long? = null,
    var titulo: String,
    var nota: String,
    var data: String,
    var hora: String,
)
