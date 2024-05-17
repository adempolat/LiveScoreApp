package com.adempolat.livescoreapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adempolat.livescoreapp.model.response.LiveScoresResponse
import com.adempolat.livescoreapp.repository.LiveScoresRepository
import com.adempolat.livescoreapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val liveScoresRepository: LiveScoresRepository
) : ViewModel() {

    private val _data = MutableLiveData<DataState<LiveScoresResponse>>()
    val data: LiveData<DataState<LiveScoresResponse>> = _data

    fun getScores() {
        viewModelScope.launch {
            liveScoresRepository.getLiveScores()
                .onEach { dataState ->
                    _data.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }
}