package com.adempolat.livescoreapp.repository

import com.adempolat.livescoreapp.model.response.LiveScoresResponse
import com.adempolat.livescoreapp.service.LiveScoreApi
import com.adempolat.livescoreapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LiveScoresRepository @Inject constructor(
    private val api: LiveScoreApi,
) {

    suspend fun getLiveScores(): Flow<DataState<LiveScoresResponse>> = flow {
        emit(DataState.Loading)
        try {
            val response = api.getMatches()
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}