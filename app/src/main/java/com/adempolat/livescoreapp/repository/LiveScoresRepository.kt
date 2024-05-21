package com.adempolat.livescoreapp.repository

import com.adempolat.livescoreapp.mapper.Mapper
import com.adempolat.livescoreapp.model.response.LiveScoresResponse
import com.adempolat.livescoreapp.service.LiveScoreApi
import javax.inject.Inject

class LiveScoresRepository @Inject constructor(
    private val api: LiveScoreApi,
) {

    suspend fun getLiveScores(): LiveScoresResponse {
        val responseDto = api.getLiveScores()
        return Mapper.mapToDomain(responseDto)
    }
}