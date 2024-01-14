package demo.kotlin.crud.dto

data class BoardFormDto (
    var writer: String,
    var password: String,
    var title: String,
    var content: String
)