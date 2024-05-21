package com.adempolat.livescoreapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adempolat.livescoreapp.model.response.LiveScoresResponse
import com.adempolat.livescoreapp.model.response.Match
import com.adempolat.livescoreapp.repository.LiveScoresRepository
import com.adempolat.livescoreapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class FilterType {
    ALL, SCHEDULE, SECOND_QUARTER, HALF_TIME, FINISHED
}

enum class SortOrder {
    FIRST_NEWEST, FIRST_OLDEST
}
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val liveScoresRepository: LiveScoresRepository
) : ViewModel() {

    private val _data = MutableLiveData<DataState<List<Match>>>()
    val data: LiveData<DataState<List<Match>>> = _data

    private val _favorites = MutableLiveData<MutableSet<Match>>(mutableSetOf())
    val favorites: LiveData<Set<Match>> = _favorites

    private var currentFilter = FilterType.ALL
    private var currentSortOrder = SortOrder.FIRST_NEWEST


    fun getScores() {
        viewModelScope.launch {
            _data.value = DataState.Loading
            try {
                val liveScoresResponse = liveScoresRepository.getLiveScores()
                val filteredMatches = filterMatches(liveScoresResponse.matches)
                val sortedMatches = sortMatches(filteredMatches)
                _data.value = DataState.Success(filteredMatches)
            } catch (e: Exception) {
                _data.value = DataState.Error(e)
            }
        }
    }

    private fun filterMatches(matches: List<Match>): List<Match> {
        return when (currentFilter) {
            FilterType.ALL -> matches
            FilterType.SCHEDULE -> matches.filter { it.score.status == 1 }
            FilterType.SECOND_QUARTER -> matches.filter { it.score.status == 2 }
            FilterType.HALF_TIME -> matches.filter { it.score.status == 3 }
            FilterType.FINISHED -> matches.filter { it.score.status == 5 }
        }
    }

    private fun sortMatches(matches: List<Match>): List<Match> {
        return when (currentSortOrder) {
            SortOrder.FIRST_NEWEST -> matches.sortedByDescending { it.score.status }
            SortOrder.FIRST_OLDEST -> matches.sortedBy { it.score.status }
        }
    }

    fun setFilter(filterType: FilterType) {
        currentFilter = filterType
        getScores()
    }

    fun setSortOrder(sortOrder: SortOrder) {
        currentSortOrder = sortOrder
        getScores()
    }

    fun toggleFavorite(match: Match) {
        val updatedFavorites = _favorites.value ?: mutableSetOf()
        if (updatedFavorites.contains(match)) {
            updatedFavorites.remove(match)
        } else {
            updatedFavorites.add(match)
        }
        _favorites.value = updatedFavorites
    }
}