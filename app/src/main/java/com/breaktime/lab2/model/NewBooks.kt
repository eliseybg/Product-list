package com.breaktime.lab2.model

data class NewBooks(
    val books: List<Book>,
    val error: String,
    val total: String
)