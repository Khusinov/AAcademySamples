package com.terrabyte.ieltswritingassistant.model

data class Word(
    val id: Int,
    val word: String,
    val audio: String,
    val meaning: String,
    val sentenceUse: String
)
