/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ryanwong.skycatnews.app.di.DispatcherModule
import uk.ryanwong.skycatnews.storydetail.data.repository.StoryDetailRepository
import uk.ryanwong.skycatnews.storydetail.domain.model.Story
import javax.inject.Inject

@HiltViewModel
class StoryDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val storyDetailRepository: StoryDetailRepository,
    @DispatcherModule.MainDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var storyId = 0

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _story: MutableStateFlow<Story?> = MutableStateFlow(null)
    val story = _story.asStateFlow()

    init {
        storyId = stateHandle.get<Int>("list_id") ?: 0
        refreshStory()
    }

    fun refreshStory() {
        _isRefreshing.value = true
        viewModelScope.launch(dispatcher) {

            storyDetailRepository.getStory(storyId = storyId).onSuccess { story ->
                _story.value = story
                _isRefreshing.value = false
            }.onFailure { ex ->
                // TODO: print error
                ex.printStackTrace()
                _isRefreshing.value = false
            }
        }
    }
}
