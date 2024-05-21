package com.adempolat.livescoreapp.service

import com.adempolat.livescoreapp.model.response.dto.LiveScoresResponseDto
import retrofit2.http.GET

interface LiveScoreApi {
    @GET("api/mobile/v2/statistics/sport/SOCCER/matches")
    suspend fun getLiveScores(): LiveScoresResponseDto
}