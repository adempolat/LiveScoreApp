package com.adempolat.livescoreapp.service

import com.adempolat.livescoreapp.model.response.LiveScoresResponse
import retrofit2.http.GET

interface LiveScoreApi {
    @GET("statistics/sport/SOCCER/matches")
    suspend fun getMatches() : LiveScoresResponse
}