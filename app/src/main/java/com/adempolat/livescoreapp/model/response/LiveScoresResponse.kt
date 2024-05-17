package com.adempolat.livescoreapp.model.response

data class LiveScoresResponse(
    val `data`: List<Data>,
    val success: Boolean
)